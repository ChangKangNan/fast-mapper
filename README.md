# 软件定位
简化操作 MySQL数据库的JAVA ROM框架

# 使用方式(maven方式)
# 非自动化方式
```
     <dependency>
            <groupId>cn.ft.ckn</groupId>
            <artifactId>fast-mapper</artifactId>
            <version>4.0.0</version>
     </dependency>
```
# 添加springboot支持,该版本默认集成fast-mapper版本4.0(自动化方式)
```
        <dependency>
            <groupId>cn.ft.ckn</groupId>
            <artifactId>fast-mapper-spring-boot-starter</artifactId>
            <version>1.0</version>
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
## 配置文件方式
```
@Component
public class DbConfig {
    static {
        //sql执行情况打印
        FastMapperConfig.isOpenSQLPrint = true;
        //自定义逻辑删除，插入更新时间定义
        FastMapperConfig.setDeleted(true,"deleted",false,true);
        FastMapperConfig.setTimeAuto(true,true);
        FastMapperConfig.setTimeColumn("create_time","update_time");
        //添加自定义切面拦截扩展支持
        FastMapperConfig.addMapperExpander(CustomActuatorAspect.class);
        //添加默认事务切面支持
        FastMapperConfig.addMapperExpander(TransactionActuatorAspect.class);
        //添加默认sql打印切面支持
        FastMapperConfig.addMapperExpander(SqlActuatorAspect.class);
    }
}
```
## yml方式
```
fast:
  mapper:
    open-sql-print: true
    supports: sql,transaction
    dao-actuator: jdbc
    open-logic-deleted-auto: true
    logic-deleted-column: deleted
    logic-deleted-column-default-value: 0
    logic-deleted-column-deleted-value: 1
    open-create-time-auto: true
    create-time: create_time
    open-update-time-auto: true
    update-time: update_time
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
1.本地多数据源事务支持需要配置yml support支持transaction
2.在对应方法上新增注解@LocalTransactional即可


## 本地sql调用执行 
```

//所有调用均支持跨数据源操作
List<Stu> select = SqlExecutor.build().select(sql, Stu.class);//查询集合
SqlExecutor.build().execute(sql);//执行sql
SqlExecutor.build().executeBatch(sqls);//在同一连接内执行sql集合

```


