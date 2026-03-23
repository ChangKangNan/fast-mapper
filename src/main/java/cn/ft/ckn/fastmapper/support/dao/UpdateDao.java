package cn.ft.ckn.fastmapper.support.dao;

import cn.ft.ckn.fastmapper.bean.FastMapperColumn;
import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.bean.FastTableMapper;
import cn.ft.ckn.fastmapper.bean.em.Expression;
import cn.ft.ckn.fastmapper.support.dao.jdbc.DataSourceConnection;
import cn.ft.ckn.fastmapper.support.dao.set.UpdateValue;
import cn.ft.ckn.fastmapper.util.db.ValueUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;

import javax.persistence.Column;
import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author ckn
 */
public class UpdateDao<T, R> extends BaseDao<R> {
    private final Class<T> classObj;
    private final Class<R> r;
    private final DaoActuator<T> daoActuator;

    public UpdateDao(Class<R> r, Class<T> classObj) {
        FastTableMapper.init(classObj);
        this.classObj = classObj;
        this.r = r;
        this.daoActuator = DataSourceConnection.getDaoActuator();
    }

    public UpdateDao(Class<T> obj) {
        classObj = obj;
        this.r = null;
        this.daoActuator = DataSourceConnection.getDaoActuator();
    }

    public UpdateValue<T, R> value() {
        return new UpdateValue<>((R) this, classObj, daoActuator);
    }

    public Integer updateByPrimaryKey(T t) {
        convertObject(t);
        boolean exist = addPkCondition(t);
        if (!exist) {
            return 0;
        }
        return daoActuator.update();
    }

    public Integer updateOverride(T t) {
        addPkCondition(t);
        List<FastMapperColumn> valueParams = ValueUtil.getColumns(t, classObj);
        if (CollUtil.isNotEmpty(valueParams)) {
            for (FastMapperColumn valueParam : valueParams) {
                FastMapperParam.get().getUpdateValueList().add(new FastMapperParam.Value(valueParam.getColumnName(), valueParam.getVal()));
            }
        }
        return daoActuator.update();
    }

    public Integer update(T t) {
        convertObject(t);
        return daoActuator.update();
    }

    public R or() {
        FastMapperParam.get().isAnd = false;
        return (R) this;
    }

    public void convertObject(T t) {
        List<FastMapperColumn> valueParams = ValueUtil.getColumns(t, classObj);
        List<FastMapperColumn> columnParams = valueParams.stream().filter(FastMapperColumn::getHaveValue).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(columnParams)) {
            for (FastMapperColumn valueParam : columnParams) {
                FastMapperParam.get().getUpdateValueList().add(new FastMapperParam.Value(valueParam.getColumnName(), valueParam.getVal()));
            }
        }
    }

    public boolean addPkCondition(T t){
        Field[] fields = classObj.getDeclaredFields();
        boolean exist = false;
        for (Field field : fields) {
            Id id = field.getAnnotation(Id.class);
            if (id != null) {
                Column fieldAnnotation = field.getAnnotation(Column.class);
                exist = true;
                String pk = fieldAnnotation != null ? fieldAnnotation.name() : field.getName();
                Object value = ReflectUtil.getFieldValue(t, field.getName());
                FastMapperParam.get().getWhereCondition().add(new FastMapperParam.WhereCondition(pk, value, Expression.Equal.expression, FastMapperParam.get().isAnd));
                break;
            }
        }
        return exist;
    }


    private R bracketPrefix() {
        FastMapperParam.get().setBracket(FastMapperParam.Bracket.builder().leftIndex(FastMapperParam.get().getWhereCondition().size()).build());
        return (R)this;
    }

    private R bracketSuffix() {
        List<FastMapperParam.Bracket> brackets = FastMapperParam.get().getBrackets();
        for (int i = brackets.size() - 1; i >= 0; i--) {
            if (brackets.get(i).getRightIndex() != null) {
                continue;
            }
            FastMapperParam.Bracket bracket = brackets.get(i);
            bracket.setRightIndex(FastMapperParam.get().getWhereCondition().size()-1);
            FastMapperParam.get().setBracket(bracket, i);
        }
        return (R)this;
    }

    public R sql(Consumer<R> consumer) {
        bracketPrefix();
        consumer.accept((R) this);
        bracketSuffix();
        return (R)this;
    }
}
