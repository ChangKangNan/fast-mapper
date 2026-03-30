package cn.ft.ckn.fastmapper.bean.em;

public enum FillObjStrategy {
    INSERT_OBJ,// 插入操作时填充对象
    UPDATE_OBJ,// 更新操作时填充对象
    INSERT_UPDATE_OBJ,//插入以及更新时填充对象
    NON//默认无任何操作
}
