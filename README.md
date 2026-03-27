# 软件定位
简化操作 MySQL数据库的JAVA ROM框架

**文档**：[对外使用说明](OPERATION_MANUAL_PUBLIC.md) · [内部操作手册](OPERATION_MANUAL.md) · [文档变更记录](CHANGELOG_DOC.md)
# 添加springboot支持
```
        <dependency>
            <groupId>cn.ft.ckn</groupId>
            <artifactId>fast-mapper-spring-boot-starter</artifactId>
            <version>4.0.0</version>
        </dependency>
```
# 使用环境
JDK1.8+
# 简要使用说明
基础目录结构如下
```
project-root/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── fm/
│   │   │       └── action/
│   │   │       └── bean/
|   |   |       └── dao/      
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
├── docs/
│   └── README.md
├── pom.xml
└── .gitignore
```
生成操作文件如下:
```
        GenerateConfig config = GenerateConfig.builder()
                .childModuleName("仅在多模块中填写当前项目名称,单一项目则不需要填")
                .basePackage("要生成在的包目录位置")
                .db("jdbc:mysql://127.0.0.1/dev?useSSL=false&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&serverTimezone=UTC",
                        "root", "123456", "com.mysql.jdbc.Driver")
                .createTables("表名(多个则英文逗号隔开)")
                .build();
        //开始生成
        GenerateUtil.generate(config);
```
基础配置信息
## yml方式
```
fast:
  mapper:
    open-sql-print: true # 开启sql日志打印
    supports: # 开启扩展字段,sql打印,事务支持
      - custom
      - sql
      - transaction
    dao-actuator: jdbc # 当前操作数据库方式有两种(JDBC,MYBATIS)
    open-logic-deleted-auto: true # 开启逻辑删除支持
    logic-deleted-column: deleted # 逻辑删除字段名
    logic-deleted-column-default-value: 0 # 默认值
    logic-deleted-column-deleted-value: 1 # 删除值
    fields: # 扩展字段配置
      - path: com.example.config.CreateTime
      - path: com.example.config.UpdateTime            
```
## 扩展字段书写方式
```
/**
 * 1.需要继承AbstractMapperField类
 * 2.defaultVal为每次填充的默认值
 * 3.columnName数据库字段名
 * 4.conditionLink 连接方式 默认Equal
 * 5.columnConditionFormatterName 格式化后的查询条件名称
 * 6.当为拼接条件Occasion.CONDITION时,默认生成mapper的字段查询条件优先级最高，会覆盖全局的默认拼接配置
 * 7.strategy设置填充的地方比如是过滤条件(搜索或者更新时进行额外数据过滤)、实体填充(插入或更新,删除额外更新一些值)
 */
public class StockTime extends AbstractMapperField {
    /**
     * 定义策略
     */
    @Override
    public Map<FastMapperParam.OperationType, Occasion> strategy() {
        return new EnumMap<FastMapperParam.OperationType, Occasion>(FastMapperParam.OperationType.class)
        {{
            put(FastMapperParam.OperationType.SELECT, Occasion.CONDITION);
            put(FastMapperParam.OperationType.SELECTLIST, Occasion.CONDITION);
            put(FastMapperParam.OperationType.UPDATE, Occasion.CONDITION);
        }};
    }

    @Override
    public String columnName() {
        return "stock_time";
    }

    @Override
    public Expression conditionLink(){
        return Expression.Equal;
    }

    @Override
    public String columnConditionFormatterName(){
        return "year(stock_time)";
    }

    @Override
    public Object defaultVal() {
        return DateUtil.year(DateUtil.date());
    }
}
```
## 业务扩展
```
1.需要继承MapperExpander,实现其业务操作(例如在before操作中额外记录一些系统日志等.)
2.书写一个config文件,调用FastMapperConfig.addMapperExpander(自定义实现类.class);
```
## 查询
```
StudentConfigMapper.lambdaQuery().name().equal("tony").one();//单个
StudentConfigMapper.lambdaQuery().name().equal("tony").list();//集合
```
## 更新
```
Student s = new Student();
s.setHobby("music");
StudentMapper.lambdaUpdate().id().equal(1).update(s);//更新对象

StudentMapper.lambdaUpdate().id().equal(1).value().set(Student::getHobby,"music").execute();//更新单独的值

```
## 删除
```
StudentMapper.lambdaDelete().id().notIn(sum.toArray()).closeDeletedProtect().delete();//物理删除
StudentMapper.lambdaDelete().id().notIn(sum.toArray()).delete();//开启逻辑删除后的逻辑删除操作

```
## 跨数据源操作
```
StudentMapper.lambdaUpdate().setSalveDataSource(datasource).id().equal(1).update(s);//根据指定数据源更新对象
```
## 多表关联查询
```
//查询一个表中的关联多列的集合
List<SFunction<Student, ?>> functions = new ArrayList<SFunction<Student, ?>>() {{
            add(Student::getId);
            add(Student::getName);
        }};
List<Map<String, Object>> maps = new JoinCustomer(Student.class, "s")
                .select(functions)
                .leftJoin(Age.class, "a", Student::getId, Age::getStuId)
                .leftJoin(Fit.class, Student::getId, Fit::getStuId)
                .leftJoin(Hobby.class, Student::getId, Hobby::getStuId)
                .lastWhere("s.name = #{name}", new HashMap<String, Object>() {{put("name", "taomi");}})
                .find();

//查询单独结果                
Fit one = new JoinCustomer(Student.class, "s")
                .select(Fit::getStuId)
                .leftJoin(Age.class, "a", Student::getId, Age::getStuId)
                .leftJoin(Fit.class, Student::getId, Fit::getStuId)
                .leftJoin(Hobby.class, Student::getId, Hobby::getStuId)
                .where(Student::getName, "ming")
                //.lastWhere("s.name = #{name}", new HashMap<String, Object>() {{put("name", "ckn");}})
                .findOne(Fit.class);                
                
```

## 本地多数据源事务
1.本地多数据源事务支持需要配置yml support支持transaction(兼容spring事务)
2.在对应方法上新增注解@LocalTransactional即可


## 本地sql调用执行 
```

//所有调用均支持跨数据源操作
List<Stu> select = SqlExecutor.build().select(sql, Stu.class);//查询集合
SqlExecutor.build().execute(sql);//执行sql
SqlExecutor.build().executeBatch(sqls);//在同一连接内执行sql集合

```


