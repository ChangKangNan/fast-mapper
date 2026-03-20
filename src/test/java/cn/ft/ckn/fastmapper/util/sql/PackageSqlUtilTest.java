package cn.ft.ckn.fastmapper.util.sql;

import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.bean.FastTableMapper;
import cn.ft.ckn.fastmapper.bean.em.Expression;
import cn.ft.ckn.fastmapper.config.FastMapperConfig;
import cn.hutool.core.text.StrBuilder;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Arrays;

public class PackageSqlUtilTest {

    public static class DemoEntity {
        @Id
        public Long id;

        @Column(name = "nick_name")
        public String nickName;

        public Integer deleted;
    }

    @Test
    public void hasFieldSupportsJavaFieldAndColumnName() {
        Assert.assertTrue(PackageSqlUtil.hasField(DemoEntity.class, "id"));
        Assert.assertTrue(PackageSqlUtil.hasField(DemoEntity.class, "nick_name"));
        Assert.assertFalse(PackageSqlUtil.hasField(DemoEntity.class, "not_exist"));
    }

    @Test
    public void whereSqlAddsLogicDeleteProtectWhenPkConditionExists() {
        FastTableMapper.init(DemoEntity.class);
        FastMapperParam<?> param = FastMapperParam.get();
        FastMapperConfig.logicDeletedColumn = "deleted";
        FastMapperConfig.logicDeletedColumnDefaultValue = 0;
        param.setCloseDeleteProtect(false);
        param.getWhereCondition().add(new FastMapperParam.WhereCondition("id", 1L, Expression.Equal.expression, true));

        StrBuilder sql = PackageSqlUtil.whereSql(new StrBuilder("SELECT * FROM demo"), param);
        String builtSql = sql.toString();

        Assert.assertTrue(builtSql.contains("deleted"));
    }

    @Test
    public void whereSqlSupportsBracketAndOrComposition() {
        FastTableMapper.init(DemoEntity.class);
        FastMapperParam<?> param = FastMapperParam.get();
        param.setCloseDeleteProtect(true);
        param.getWhereCondition().add(new FastMapperParam.WhereCondition("id", 1L, Expression.Equal.expression, true));
        param.getWhereCondition().add(new FastMapperParam.WhereCondition("nickName", "tom", Expression.Equal.expression, false));
        param.setBrackets(Arrays.asList(
                FastMapperParam.Bracket.builder().leftIndex(0).rightIndex(1).build()
        ));

        String sql = PackageSqlUtil.whereSql(new StrBuilder("SELECT * FROM demo"), param).toString();
        Assert.assertTrue(sql.contains("("));
        Assert.assertTrue(sql.contains(")"));
        Assert.assertTrue(sql.toUpperCase().contains("OR"));
    }
}
