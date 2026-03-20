package cn.ft.ckn.fastmapper.support.criteria;

import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.bean.FastTableMapper;
import cn.ft.ckn.fastmapper.bean.em.Expression;
import org.junit.Assert;
import org.junit.Test;

public class CriteriaSemanticsTest {

    public static class DemoEntity {
        public Long id;
    }

    @Test
    public void updateCriteriaInUsesInExpression() {
        FastTableMapper.init(DemoEntity.class);
        UpdateCriteria<DemoEntity, Object> criteria = new UpdateCriteria<>(new Object(), "id", DemoEntity.class);
        criteria.in(1L, 2L, 2L);

        FastMapperParam.WhereCondition condition = FastMapperParam.get().getWhereCondition().get(0);
        Assert.assertEquals(Expression.In.expression, condition.expression);
    }

    @Test
    public void deletedCriteriaInUsesInExpression() {
        FastTableMapper.init(DemoEntity.class);
        DeletedCriteria<DemoEntity, Object> criteria = new DeletedCriteria<>(new Object(), "id", DemoEntity.class);
        criteria.in(1L, 2L);

        FastMapperParam.WhereCondition condition = FastMapperParam.get().getWhereCondition().get(0);
        Assert.assertEquals(Expression.In.expression, condition.expression);
    }
}
