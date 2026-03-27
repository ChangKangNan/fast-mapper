# Fast Mapper 对外发布版操作手册

> 面向客户方、集成方、外部评审。  
> 本文聚焦“怎么接入、怎么用、遇到问题怎么处理”。

## 1. 产品简介

`Fast Mapper` 是一套面向 MySQL 的 Java 数据访问组件，用于提升数据层开发效率，降低手写 SQL 成本。

支持能力：

- 条件查询、更新、删除
- 逻辑删除
- 多表关联查询（Join）
- 自定义 SQL 执行
- 多数据源切换（按配置启用）

适用环境：JDK 8+，Spring / Spring Boot 项目。

## 2. 快速接入

### 2.1 引入依赖

```xml
<dependency>
    <groupId>cn.ft.ckn</groupId>
    <artifactId>fast-mapper</artifactId>
    <version>请使用发布版本号</version>
</dependency>
```

### 2.2 配置数据源

使用项目已有 DataSource 即可（推荐 Druid）。

### 2.3 打开功能开关

在 `application.yml` 中配置 `fast.mapper.*`（如 SQL 打印、逻辑删除、事务支持等）。

## 3. 常见使用方式（示意）

### 3.1 查询

```java
XxxMapper.lambdaQuery().字段().equal(值).one();
XxxMapper.lambdaQuery().字段().equal(值).list();
```

### 3.2 更新

```java
XxxMapper.lambdaUpdate().id().equal(1).update(实体);
```

### 3.3 删除

```java
XxxMapper.lambdaDelete().id().equal(1).delete();
```

### 3.4 Join 查询

```java
new JoinCustomer(主表.class, "m")
    .leftJoin(从表.class, "s", 主表::主键, 从表::外键)
    .where(主表::字段, 值)
    .find();
```

### 3.5 执行自定义 SQL

```java
SqlExecutor.build().select(sql, 返回类型.class);
SqlExecutor.build().execute(updateSql);
```

## 4. 对外使用建议

- 统一版本：业务项目与发布包版本保持一致
- 多表查询建议强制使用别名，避免字段歧义
- 自定义 SQL 必须参数化，避免注入风险
- 上线前至少执行一次完整回归测试

## 5. 常见问题

### 5.1 集成报错

请优先检查：

- JDK 版本
- Spring 版本
- MySQL 驱动版本
- 依赖冲突（maven tree）

### 5.2 查询结果不符合预期

请确认：

- 条件构造是否正确
- 逻辑删除开关是否启用
- Join 条件与别名是否一致

## 6. 支持方式

提交问题时请附带：

- 版本号
- 配置片段（脱敏）
- 最小复现代码或 SQL
- 预期结果与实际结果

## 7. 文档导航

- 对外发布版（本文）：`OPERATION_MANUAL_PUBLIC.md`
- 内部完整版：`OPERATION_MANUAL.md`
- 快速说明：`README.md`
