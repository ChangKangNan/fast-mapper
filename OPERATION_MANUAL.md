# Fast Mapper 操作手册（内部版）

> 面向项目维护者、开发者、测试与排障人员。  
> 对外精简说明请查看 `OPERATION_MANUAL_PUBLIC.md`。

## 1. 文档目标

本文档用于统一 `fast-mapper` 的接入、使用、发布与排障流程，避免因版本差异、配置遗漏或使用方式不一致导致的问题。

## 2. 环境与依赖

- JDK：1.8+
- 数据库：MySQL 5.7/8.x
- 推荐连接池：Druid
- 构建工具：Maven

核心依赖示例：

```xml
<dependency>
    <groupId>cn.ft.ckn</groupId>
    <artifactId>fast-mapper</artifactId>
    <version>${FASTMAPPER.VERSION}</version>
</dependency>
```

## 3. 接入流程（标准步骤）

### 步骤 1：引入依赖

在业务服务的 `pom.xml` 添加 `fast-mapper`（或 starter 版本）。
```xml
<dependency>
    <groupId>cn.ft.ckn</groupId>
    <artifactId>fast-mapper-spring-boot-starter</artifactId>
    <version>2.7.18</version>
</dependency>
```
### 步骤 2：准备数据源

保持项目已有数据源配置即可（如 Druid + Spring Boot 自动装配）。

```java
@Configuration
public class DruidConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return new DruidDataSource();
    }
}
```

### 步骤 3：启用框架配置

`application.yml` 示例：

```yaml
fast:
  mapper:
    open-sql-print: true
    supports:
      - custom
      - sql
      - transaction
    dao-actuator: jdbc
    open-logic-deleted-auto: true
    logic-deleted-column: deleted
    logic-deleted-column-default-value: 0
    logic-deleted-column-deleted-value: 1
    fields:
      - path: com.example.config.CreateTime
      - path: com.example.config.UpdateTime
```

## 4. 核心能力使用

### 4.1 查询

```java
StudentMapper.lambdaQuery().name().equal("tony").one();
StudentMapper.lambdaQuery().name().equal("tony").list();
```

### 4.2 更新

```java
Student s = new Student();
s.setHobby("music");
StudentMapper.lambdaUpdate().id().equal(1).update(s);

StudentMapper.lambdaUpdate()
    .id().equal(1)
    .value().set(Student::getHobby, "music")
    .execute();
```

### 4.3 删除（逻辑删除与物理删除）

```java
StudentMapper.lambdaDelete().id().notIn(ids).delete(); // 逻辑删除
StudentMapper.lambdaDelete().id().notIn(ids).closeDeletedProtect().delete(); // 物理删除
```

### 4.4 多表 Join

```java
List<Map<String, Object>> maps = new JoinCustomer(Student.class, "s")
    .select(Student::getId)
    .leftJoin(Age.class, "a", Student::getId, Age::getStuId)
    .leftJoin(Hobby.class, "h", Student::getId, Hobby::getStuId)
    .where(Student::getName, "ming")
    .lastWhere("h.level = ${level}", new HashMap<String, Object>() {{
        put("level", 3);
    }})
    .find();
```

说明：

- `leftJoin/rightJoin/innerJoin` 均可链式调用
- `lastWhere` 已支持避免重复 `WHERE`，已有条件时自动拼接 `AND`
- 推荐在多表场景统一使用 alias，减少字段歧义

### 4.5 自定义 SQL（SqlExecutor）

```java
List<Stu> list = SqlExecutor.build().select(sql, Stu.class);
List<Map<String, Object>> rows = SqlExecutor.build().select(sql, new HashMap<String, Object>());
int updated = SqlExecutor.build().execute(updateSql);
SqlExecutor.build().executeBatch(sqlBatch);
```

### 4.6 跨数据源

```java
SqlExecutor.build()
    .setSalveDataSource(slaveDataSource)
    .execute("update student set name='A' where id=1");
```

## 5. 扩展点

### 5.1 扩展字段（AbstractField）

典型场景：创建时间、更新时间、逻辑删除字段自动补充。

实现要求：

- 继承 `AbstractField`
- 实现 `fieldName/check/defaultVal/strategy`
- 通过配置注册到 `fast.mapper.fields`

### 5.2 业务扩展（MapperExpander）

典型场景：审计日志、统一链路记录、前后置处理。

实现要求：

- 继承 `MapperExpander`
- 在配置中调用 `FastMapperConfig.addMapperExpander(...)`

## 6. 代码生成（可选）

```java
GenerateConfig config = GenerateConfig.builder()
    .basePackage("com.example")
    .db("jdbc:mysql://127.0.0.1:3306/dev", "root", "123456", "com.mysql.cj.jdbc.Driver")
    .createTables("student,age")
    .build();
GenerateUtil.generate(config);
```

## 7. 已完成的关键优化点（当前分支）

- 修复 `JoinTb.rightJoin/innerJoin` 分派错误
- 修复 `JoinManager` where/alias 拼接问题
- 修复 `in()` 条件误用 `NOT IN` 的语义错误
- 优化 `lastWhere` 重复 `WHERE` 问题
- 强化 `DataSourceRunner` 异常场景 `finally` 解绑
- 优化从库模板 key 生成与缓存稳定性
- 清理部分 raw type 并补齐泛型链路
- 增加回归测试覆盖（Join、Criteria、SQL、DataSource）

## 8. 常见问题与排障

### 8.1 泛型报错（`setObjClass`/`List<?>`）

原因：调用链未声明泛型，或返回值被推断为通配符。  
处理：优先声明类型变量，必要时在单点增加受控 `@SuppressWarnings("unchecked")`。

### 8.2 Join 结果不符合预期

- 检查表 alias 与 where 字段引用是否一致
- 检查 `lastWhere` 片段与参数 key 是否一致
- 检查 join key 对应字段是否映射正确

### 8.3 多数据源串库/缓存错用

- 检查调用链是否正确设置从库
- 检查线程上下文是否被清理
- 检查 dataSource key 生成是否稳定

### 8.4 SQL 不打印或日志不全

- 检查 `open-sql-print` 配置
- 检查日志级别与日志实现（slf4j provider）

## 9. 验证与发布清单

### 9.1 本地验证命令

```bash
mvn -q -DskipTests compile
mvn -q test
```

### 9.2 发布前检查

- 代码编译通过
- 单元测试通过
- README 文档入口已更新
- 对外文档与内部文档已同步
- 无新增高优先级 lint 报错

## 10. 文档索引

- 对外版：`OPERATION_MANUAL_PUBLIC.md`
- 内部版：`OPERATION_MANUAL.md`
- 快速入口：`README.md`

