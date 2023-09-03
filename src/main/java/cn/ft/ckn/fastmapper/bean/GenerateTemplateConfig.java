package cn.ft.ckn.fastmapper.bean;

import cn.hutool.core.collection.CollUtil;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author kangnan.chang
 */
public class GenerateTemplateConfig {
    /**
     * 需要生成模型的表名
     */
    private Set<String> createTables = CollUtil.newHashSet("all");
    /**
     * 是否生成在测试类中
     */
    private Boolean isTest = Boolean.FALSE;
    /**
     * 是否覆盖
     */
    private Boolean isReplace = Boolean.TRUE;
    /**
     * 子模块路径 xxx.xxx.xxx
     */
    private String childModuleName = "";

    /**
     * 模板文件生成的包路径 xxx.xxx.xxx
     */
    private String basePackage;

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBasePackage() {
        return basePackage;
    }

    /**
     * 是否忽略前缀
     */
    private Boolean ignorePrefix = Boolean.FALSE;

    public Boolean getIgnorePrefix() {
        return ignorePrefix;
    }

    public void setIgnorePrefix(Boolean ignorePrefix) {
        this.ignorePrefix = ignorePrefix;
    }

    public Boolean getTest() {
        return isTest;
    }

    public String getChildModuleName() {
        return childModuleName;
    }

    public Set<String> getCreateTables() {
        return createTables;
    }

    public void setCreateTables(Set<String> createTables) {
        this.createTables = createTables;
    }

    /**
     * 数据库连写信息
     */
    private String driverClass;
    private String url;
    private String user;
    private String password;

    public void setTest(Boolean test) {
        isTest = test;
    }

    public Boolean getReplace() {
        return isReplace;
    }

    public void setReplace(Boolean replace) {
        isReplace = replace;
    }

    /**
     * 需要生成的表名称
     *
     * @param tables 多个表用逗号隔开,如果需要生成数据库中所有的表,参数为all
     */
    public void setCreateTables(String... tables) {
        createTables = new HashSet<>();
        Collections.addAll(createTables, tables);
    }

    /**
     * 如果是多模块项目,需要使用此项
     *
     * @param childModuleName 指定在哪个模块下创建模板文件
     */
    public void setChildModuleName(String childModuleName) {
        this.childModuleName = childModuleName;
    }

    /**
     * 生成模板的包路径
     *
     * @param basePackage 包路径地址 xxx.xxx.xxx
     */
    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    /**
     * 设置数据库连接信息
     *
     * @param url         数据库连接
     * @param user        用户名
     * @param password    密码
     * @param driverClass 数据库驱动
     */
    public void setDBInfo(String url, String user, String password, String driverClass) {
        this.url = url + "&useInformationSchema=true";
        this.user = user;
        this.password = password;
        this.driverClass = driverClass;
    }
}
