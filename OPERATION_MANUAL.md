# Fast Mapper 操作手册

## 1. 适用范围

本手册用于指导 `fast-mapper` 的日常接入、开发与排障，适用于：

- 使用 JDK 1.8+ 的 Java 项目
- 基于 Spring/Spring Boot 的 MySQL 访问场景
- 需要快速完成单表 CRUD、条件构造、多表 Join、跨数据源与本地事务支持的项目

---

## 2. 快速开始

### 2.1 Maven 依赖

```xml
<dependency>
    <groupId>cn.ft.ckn</groupId>
    <artifactId>fast-mapper</artifactId>
    <version>${FASTMAPPER.VERSION}</version>
</dependency>
```

### 2.2 基础环境

- JDK 1.8+
- MySQL 5.7/8.x
- 推荐连接池：Druid

### 2.3 启用数据源（示例）

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

---

## 3. 配置说明

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

关键配置项：

- `open-sql-print`：是否打印 SQL
- `dao-actuator`：底层执行器，常用 `jdbc`
- `open-logic-deleted-auto`：逻辑删除开关
- `logic-deleted-*`：逻辑删除字段及默认/删除值
- `supports`：扩展能力开关（如事务、SQL 扩展）

---

## 4. 常用操作

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

### 4.3 删除

```java
// 物理删除（关闭删除保护）
StudentMapper.lambdaDelete().id().notIn(ids).closeDeletedProtect().delete();

// 逻辑删除（开启逻辑删除配置时）
StudentMapper.lambdaDelete().id().notIn(ids).delete();
```

### 4.4 多表关联查询

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

> 说明：`lastWhere` 会自动识别是否已有 `WHERE`，避免重复拼接。

### 4.5 本地 SQL 执行

```java
List<Stu> list = SqlExecutor.build().select(sql, Stu.class);
int rows = SqlExecutor.build().execute(updateSql);
SqlExecutor.build().executeBatch(sqlList);
```

---

## 5. 跨数据源与事务

### 5.1 跨数据源

```java
SqlExecutor.build()
    .setSalveDataSource(slaveDataSource)
    .execute("update student set name='A' where id=1");
```

### 5.2 本地事务

- 在配置中启用 `transaction` 支持
- 在方法上增加 `@LocalTransactional`

---

## 6. 代码生成（可选）

```java
GenerateConfig config = GenerateConfig.builder()
    .basePackage("com.example")
    .db("jdbc:mysql://127.0.0.1:3306/dev", "root", "123456", "com.mysql.cj.jdbc.Driver")
    .createTables("student,age")
    .build();
GenerateUtil.generate(config);
```

---

## 7. 常见问题与排障

### 7.1 泛型/类型报错（如 `setObjClass`）

- 原因：`FastMapperParam`/`FastTableMapper` 泛型更严格后，调用方使用原始类型
- 处理：优先声明泛型变量，必要时在单点使用受控 `@SuppressWarnings("unchecked")`

### 7.2 Join SQL 条件异常

- 检查 `alias` 与 `where` 是否对应
- 检查 `lastWhere` 参数名是否与 SQL 中占位符一致

### 7.3 从库模板缓存错用

- 确保不同 `DataSource` 能生成不同 key
- 已内置 fallback，避免空 key 导致缓存冲突

### 7.4 线程上下文污染

- 使用跨数据源操作后建议调用对应清理逻辑
- 事务/执行器内部应保证异常场景 `finally` 清理

---

## 8. 验证命令

```bash
mvn -q -DskipTests compile
mvn -q test
```

---

## 9. 推荐团队规范

- 新增能力必须补充测试（至少覆盖成功路径 + 边界路径）
- 所有 SQL 构建修复都应增加回归用例
- 尽量避免 raw type，统一使用泛型接口（`Map`、`List<?>`）

