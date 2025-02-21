package cn.ft.ckn.fastmapper.support.dao.mybatis;

import cn.ft.ckn.fastmapper.support.dao.DaoActuator;
import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.support.provider.MapperDeleteProvider;
import cn.ft.ckn.fastmapper.support.provider.MapperInsertProvider;
import cn.ft.ckn.fastmapper.support.provider.MapperSelectProvider;
import cn.ft.ckn.fastmapper.support.provider.MapperUpdateProvider;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ckn
 * @date 2023/6/19
 */
public class MybatisDaoActuator<T> implements DaoActuator<T> {
    @Override
    public List<T> insert() {
        FastMapperParam<T> param = FastMapperParam.get();
        MapperInsertProvider.insert(param);
        List<T> insertList = param.getInsertList();
        if (CollUtil.isEmpty(insertList)) {
            return new ArrayList<>();
        }
        if (insertList.size() == 1) {
            MybatisConnection.getMapper().insertPrimaryKeyAuto(param);
            Object returnVal = param.getReturnVal();
            BeanUtil.setFieldValue(param.getInsertList().get(0), param.getTableMapper().getPrimaryKey(), returnVal);
        } else {
            MybatisConnection.getMapper().insert(param);
        }
        return insertList;
    }

    @Override
    public List<T> select() {
        FastMapperParam<T> param = FastMapperParam.get();
        MapperSelectProvider.findAll(param);
        List<Map<String, Object>> select = MybatisConnection.getMapper().select(param);
        if(CollUtil.isEmpty(select)){
            return new ArrayList<>();
        }
        return JSONObject.parseArray(JSONObject.toJSONString(select), param.getTableMapper().getObjClass());
    }

    @Override
    public Integer count() {
        FastMapperParam<T> param = FastMapperParam.get();
        MapperSelectProvider.findCount(param);
        return MybatisConnection.getMapper().count(param);
    }

    @Override
    public Integer update() {
        FastMapperParam<T> param = FastMapperParam.get();
        MapperUpdateProvider.update(param);
        return MybatisConnection.getMapper().update(param);
    }

    @Override
    public Integer delete() {
        FastMapperParam<T> param = FastMapperParam.get();
        MapperDeleteProvider.delete(param);
        return MybatisConnection.getMapper().delete(param);
    }
}
