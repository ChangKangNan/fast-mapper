# 软件定位
用于辅助后端处理数据库数据即基础的CRUD操作
# 使用方式(maven方式)
```
     <dependency>
            <groupId>cn.ft.ckn</groupId>
            <artifactId>fast-mapper</artifactId>
            <version>1.0.0-SNAPSHOT</version>
     </dependency>
```
# 使用环境
JDK1.8+
# 简要使用说明
基础目录结构如下

![img.png](image/img.png)

生成操作文件如下:

![img_1.png](image/img_1.png)

其中dbInfo为基本的数据库连接信息
basePackage:基础包路径,根据自己项目中实际的修改即可
生成文件总共三个:
## 基础bean

![img_2.png](image/img_2.png)

## action文件

![img_3.png](image/img_3.png)

## dao文件

![img_4.png](image/img_4.png)

基础配置信息
## 控制数据库操作日志输出
```
FastMapperConfig.isOpenSQLPrint=true;
```
## 设置逻辑删除字段
```
FastMapperConfig.setDeleted(true,"deleted",false,true);
```
## 设置更新时间字段
```
FastMapperConfig.setTimeColumn("create_time","update_time");
FastMapperConfig.setTimeAuto(true,true);
```
## 查询

## 查询单条数据

![img_5.png](image/img_5.png)

## 查询多条数据

## ![img_6.png](image/img_6.png)

## 多条件查询and

## ![img_7.png](image/img_7.png)

## 多条件or关系

## ![img_8.png](image/img_8.png)

## 查询分页

## ![img_9.png](image/img_9.png)

## 更新--插入

![img_10.png](image/img_10.png)

## 更新--删除

![img_11.png](image/img_11.png)

## 更新

![img_12.png](image/img_12.png)

## 单独某一值更新

![img_13.png](image/img_13.png)

## 跨数据源查询

![img_14.png](image/img_14.png)

setSalveDataSource在多种操作模式lambdaQuery,lambdaDelete,lambdaInsert,
lambdaUpdate都可执行
## 跨数据源全局事务--基于本操作框架的数据库操作

![img_15.png](image/img_15.png)

只需方法头添加@GlobalTransactionalLocal注解即可保证事务一致性