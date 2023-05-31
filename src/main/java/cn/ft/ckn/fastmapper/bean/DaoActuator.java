package cn.ft.ckn.fastmapper.bean;

import java.util.List;

public interface DaoActuator<T> {

    List<T> insert();

    List<T> select();

    Integer count();

    Integer update();

    Integer delete();

}