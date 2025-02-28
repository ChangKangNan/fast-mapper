package cn.ft.ckn.fastmapper.bean.config;

import cn.hutool.core.collection.CollUtil;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author kangnan.chang
 */
public class GenerateConfig {
    private GenerateConfig(){}
    /**
     * 需要生成模型的表名,若不填默认生成所有表
     */
    private Set<String> createTables;
    /**
     * 是否生成在测试类下
     */
    private Boolean genOnTest;
    /**
     * 是否覆盖原先的文件
     */
    private Boolean replaceBefore;

    /**
     * 子模块的名称(仅在多模块项目下指定当前的需要生成项目的名字)
     */
    private String childModuleName;

    /**
     * 生成项目的包路径
     */
    private String basePackage;

    /**
     * 是否忽略数据库表名的前缀
     */
    private Boolean ignoreTablePrefix;

    /**
     * 是否对数据库表名转换的类文件名称采用驼峰命名法
     */
    private Boolean toCamelCase;

    /**
     * 数据库链接信息
     */
    private String driverClass;
    private String url;
    private String user;
    private String password;

    public Set<String> getCreateTables() {
        return createTables;
    }

    public Boolean getGenOnTest() {
        return genOnTest;
    }

    public Boolean getReplaceBefore() {
        return replaceBefore;
    }

    public String getChildModuleName() {
        return childModuleName;
    }

    public Boolean getIgnoreTablePrefix() {
        return ignoreTablePrefix;
    }

    public Boolean getToCamelCase() {
        return toCamelCase;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }


    public String getBasePackage() {
        return basePackage;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder{
        private Set<String> createTables = CollUtil.newHashSet("all");
        private Boolean genOnTest = Boolean.FALSE;
        private Boolean replaceBefore = Boolean.TRUE;
        private String childModuleName = "";
        private String basePackage;
        private Boolean ignoreTablePrefix = Boolean.FALSE;
        private Boolean toCamelCase = Boolean.TRUE;
        private String driverClass;
        private String url;
        private String user;
        private String password;

        public Builder db(String url, String user, String password, String driverClass) {
            this.url = url + "&useInformationSchema=true";
            this.user = user;
            this.password = password;
            this.driverClass = driverClass;
            return this;
        }

        public Builder createTables(String... tables) {
            createTables = new HashSet<>();
            Collections.addAll(createTables, tables);
            return this;
        }

        public Builder createTables(Set<String> createTables) {
            this.createTables = createTables;
            return this;
        }

        public Builder genOnTest(Boolean genOnTest) {
            this.genOnTest = genOnTest;
            return this;
        }

        public Builder replaceBefore(Boolean replaceBefore) {
            this.replaceBefore = replaceBefore;
            return this;
        }

        public Builder childModuleName(String childModuleName) {
            this.childModuleName = childModuleName;
            return this;
        }

        public Builder ignoreTablePrefix(Boolean ignoreTablePrefix) {
            this.ignoreTablePrefix = ignoreTablePrefix;
            return this;
        }

        public Builder basePackage(String basePackage) {
            this.basePackage = basePackage;
            return this;
        }

        public Builder toCamelCase(Boolean toCamelCase) {
            this.toCamelCase = toCamelCase;
            return this;
        }

        public GenerateConfig build() {
            GenerateConfig generateConfig = new GenerateConfig();
            generateConfig.childModuleName = this.childModuleName;
            generateConfig.basePackage = this.basePackage;
            generateConfig.createTables = this.createTables;
            generateConfig.genOnTest = this.genOnTest;
            generateConfig.replaceBefore = this.replaceBefore;
            generateConfig.ignoreTablePrefix = this.ignoreTablePrefix;
            generateConfig.toCamelCase = this.toCamelCase;
            generateConfig.url = this.url;
            generateConfig.user = this.user;
            generateConfig.password = this.password;
            generateConfig.driverClass = this.driverClass;
            return generateConfig;
        }
    }

}
