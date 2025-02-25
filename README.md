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
public class GenerateTest {
    public static void main(String[] args) {
        GenerateConfig config = new GenerateConfig();
        //基础目录
        config.setBasePackage("pers.ckn.sp");
        //无子模块项目则不无需填写
        config.setChildModuleName("");
        //数据库信息
        config.setDBInfo("jdbc:mysql://localhost:3306/user?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useInformationSchema=true",
                "root","123456","com.mysql.jdbc.Driver");
        //生成的表集合
        config.setCreateTables("user_info");
        //是否生成在test目录下
        config.setTest(false);
        //开始生成
        GenerateUtil.generate(config);
    }
}
```
基础配置信息
## 配置文件方式
```
@Component
public class SearchConfig {
    static {
        //sql执行情况打印
        FastMapperConfig.isOpenSQLPrint = true;
        FastMapperConfig.addMapperExpander(SqlActuatorAspect.class);
        //自定义逻辑删除，插入更新时间定义
        FastMapperConfig.setDeleted(true,"deleted",false,true);
        FastMapperConfig.setTimeAuto(true,true);
        FastMapperConfig.setTimeColumn("create_time","update_time");
        FastMapperConfig.addMapperExpander(CustomActuatorAspect.class);
        //全局事务支持
        FastMapperConfig.addMapperExpander(TransactionActuatorAspect.class);
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

StudentMapper.lambdaUpdate().id().equal(1).value().set().execute(Student::getHobby,"music");;//更新单独的值

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
List<Stu> select = DbUtil.build().select(sql, Stu.class);//查询集合
DbUtil.build().execute(sql);//执行sql

```

