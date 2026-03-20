package cn.ft.ckn.fastmapper.join;

import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.Table;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;

public class JoinFlowTest {

    @Table(name = "tb_main")
    public static class MainEntity {
        private Long id;

        public Long getId() {
            return id;
        }
    }

    @Table(name = "tb_right")
    public static class RightEntity {
        private Long id;

        public Long getId() {
            return id;
        }
    }

    @Table(name = "tb_inner")
    public static class InnerEntity {
        private Long id;

        public Long getId() {
            return id;
        }
    }

    @Test
    public void joinTbDispatchesRightAndInnerJoinCorrectly() throws Exception {
        JoinTb joinTb = new JoinCustomer(MainEntity.class).leftJoin(RightEntity.class, MainEntity::getId, RightEntity::getId);
        joinTb.rightJoin(InnerEntity.class, MainEntity::getId, InnerEntity::getId);

        Field paramsField = JoinTb.class.getDeclaredField("params");
        paramsField.setAccessible(true);
        JoinParams params = (JoinParams) paramsField.get(joinTb);
        Assert.assertEquals("RIGHT JOIN", params.relation.get("tb_inner"));

        joinTb.innerJoin(InnerEntity.class, MainEntity::getId, InnerEntity::getId);
        Assert.assertEquals("INNER JOIN", params.relation.get("tb_inner"));
    }

    @Test
    public void prepareFindAppendsLastWhereWithAndWhenWhereAlreadyExists() throws Exception {
        JoinParams params = new JoinParams();
        params.mainTable = "tb_main";
        params.aliasMap.put("tb_main", "m");
        params.where.put(" tb_main.id", 1L);
        params.lastSQL = "tb_main.id = ${id}";
        params.lastWhereParameters = Collections.<String, Object>singletonMap("id", 1L);
        JoinManager manager = new JoinManager(params);

        Method prepareFind = JoinManager.class.getDeclaredMethod("prepareFind");
        prepareFind.setAccessible(true);
        prepareFind.invoke(manager);

        String sql = FastMapperParam.get().getExecuteSql();
        Assert.assertNotNull(sql);
        Assert.assertEquals(1, countIgnoreCase(sql, "WHERE"));
    }

    @Test
    public void getSqlUsesAliasForWhereClauseWhenAliasExists() throws Exception {
        JoinParams params = new JoinParams();
        params.mainTable = "tb_main";
        params.aliasMap.put("tb_main", "m");
        params.where.put(" tb_main.id", 2L);
        JoinManager manager = new JoinManager(params);

        Method getSql = JoinManager.class.getDeclaredMethod("getSQL");
        getSql.setAccessible(true);
        String sql = getSql.invoke(manager).toString();

        Assert.assertTrue(sql.contains("m.id"));
    }

    @Test
    public void getSqlBuildsJoinAndWhereWithAliases() throws Exception {
        JoinParams params = new JoinParams();
        params.mainTable = "tb_main";
        params.aliasMap.put("tb_main", "m");
        params.aliasMap.put("tb_right", "r");
        params.deeps.put("tb_right", 1);
        params.relation.put("tb_right", "LEFT JOIN");
        params.joins.put("tb_right", Collections.singletonMap("m.id", "r.id"));
        params.where.put(" tb_right.id", 3L);
        JoinManager manager = new JoinManager(params);

        Method getSql = JoinManager.class.getDeclaredMethod("getSQL");
        getSql.setAccessible(true);
        String sql = getSql.invoke(manager).toString();

        Assert.assertTrue(sql.contains("tb_main AS m"));
        Assert.assertTrue(sql.contains("LEFT JOIN tb_right AS r ON"));
        Assert.assertTrue(sql.contains("m.id"));
        Assert.assertTrue(sql.contains("r.id"));
    }

    private int countIgnoreCase(String source, String target) {
        String src = source.toUpperCase();
        String tar = target.toUpperCase();
        int count = 0;
        int index = 0;
        while ((index = src.indexOf(tar, index)) >= 0) {
            count++;
            index += tar.length();
        }
        return count;
    }
}
