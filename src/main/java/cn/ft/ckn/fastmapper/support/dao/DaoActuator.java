package cn.ft.ckn.fastmapper.support.dao;

import java.util.List;
import java.util.Map;

public interface DaoActuator<T> {

    List<T> insert();

    List<T> select();

    List<Map<String, Object>> selectList();

    Integer count();

    Integer update();

    Integer delete();

}