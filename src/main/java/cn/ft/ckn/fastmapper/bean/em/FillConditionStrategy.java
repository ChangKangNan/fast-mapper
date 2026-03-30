package cn.ft.ckn.fastmapper.bean.em;

public enum FillConditionStrategy {
    SELECT_CONDITION,//查询操作
    UPDATE_CONDITION,//更新操作
    DELETE_CONDITION,//删除操作
    SELECT_UPDATE_CONDITION,//查询操作 以及 更新操作
    UPDATE_DELETE_CONDITION,//更新操作 以及 删除操作
    SELECT_UPDATE_DELETE_CONDITION,// 查询操作 、 更新操作 以及 删除操作
    NON//默认无任何操作
}
