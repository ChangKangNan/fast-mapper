# Fast Mapper 对外使用说明

> 面向 **集成方、业务开发、技术评审** 的精简版说明。深度技术细节见仓库内 `OPERATION_MANUAL.md`。

---

## 一、产品是什么

**Fast Mapper** 是一套面向 MySQL 的 Java 数据访问增强组件，帮助业务在少写 SQL 的前提下完成：

- 单表查询、更新、删除（含逻辑删除）
- 多表关联查询
- 自定义 SQL 执行
- 多数据源切换与本地事务（按项目配置启用）

**运行环境**：JDK 8+，通常与 Spring / Spring Boot 配合使用。

---

## 二、能做什么（能力清单）

| 能力 | 说明 |
|------|------|
| 条件查询 | 链式条件，支持等于、范围、模糊、空值、IN 等 |
| 更新 | 按条件更新整对象或指定字段 |
| 删除 | 支持逻辑删除与物理删除（可关闭删除保护） |
| 多表 Join | 主表 + 多表左/右/内连接，可选别名与附加条件 |
| 自定义 SQL | 通过门面执行查询、更新、批量语句 |
| 多数据源 | 可在调用链上指定从库等数据源 |
| 扩展字段 | 如创建时间、更新时间、逻辑删除等可按策略自动参与 |

---

## 三、如何接入（三步）

### 1. 引入依赖

在业务工程的 `pom.xml` 中加入（版本以贵司制品库为准）：

```xml
<dependency>
    <groupId>cn.ft.ckn</groupId>
    <artifactId>fast-mapper</artifactId>
    <version>请填写实际版本号</version>
</dependency>
```

若使用 **Spring Boot Starter**，请按贵司发布的 Starter 坐标与版本引入（参见主 `README.md`）。

### 2. 配置数据源

使用项目现有的 **DataSource**（如 Druid）即可，无需替换为其他中间件。

### 3. 打开功能开关（按需）

在 `application.yml` 中配置 `fast.mapper.*`，例如：

- 是否打印 SQL
- 是否启用逻辑删除及字段名、默认值
- 是否启用事务扩展、自定义执行器等

具体键名与示例以贵司环境模板为准，完整列表可参考 `README.md` 或内部 `OPERATION_MANUAL.md`。

---

## 四、典型用法（示例级）

以下仅为 **示意**，类名、方法名以代码生成或项目实际为准。

**查询一条 / 列表**

```java
XxxMapper.lambdaQuery().字段().equal(值).one();
XxxMapper.lambdaQuery().字段().equal(值).list();
```

**按条件更新**

```java
XxxMapper.lambdaUpdate().id().equal(1).update(实体);
```

**多表查询（示意）**

```java
new JoinCustomer(主表.class, "别名")
    .select(主表::某字段)
    .leftJoin(从表.class, "别名", 主表::外键, 从表::外键)
    .where(主表::某字段, 值)
    .find();
```

**执行自定义 SQL**

```java
SqlExecutor.build().select(sql, 返回类型.class);
SqlExecutor.build().execute(updateSql);
```

---

## 五、使用注意（对外必读）

1. **版本一致**：业务工程与公共库中的 Fast Mapper 版本需对齐，避免行为差异。  
2. **逻辑删除**：开启后，“删除”多为更新删除标记；需要物理删除时需按框架约定关闭保护。  
3. **Join 与别名**：多表场景建议统一表别名，附加片段条件与占位符参数需一致。  
4. **多数据源**：在指定从库等场景下，注意线程/上下文生命周期，避免串库。  
5. **安全**：自定义 SQL 仍须做好参数绑定，避免拼接用户输入导致注入风险。

---

## 六、问题与支持

- **编译/集成问题**：核对 JDK、Spring、MySQL 驱动与依赖版本。  
- **行为不符合预期**：提供复现步骤、相关配置片段（脱敏）与最小 SQL/代码示例。  
- **深度技术说明**：请参阅仓库内 `OPERATION_MANUAL.md` 与 `README.md`。

---

## 七、文档索引

| 文档 | 读者 |
|------|------|
| `OPERATION_MANUAL_PUBLIC.md`（本文） | 对外集成、业务开发、评审 |
| `OPERATION_MANUAL.md` | 内部开发与排障 |
| `README.md` | 快速上手与配置片段 |

---

*文档版本随仓库迭代更新，以当前分支内容为准。*
