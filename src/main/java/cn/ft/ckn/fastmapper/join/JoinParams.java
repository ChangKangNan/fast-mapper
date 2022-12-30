package cn.ft.ckn.fastmapper.join;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ckn
 * @date 2022/8/11
 */
public class JoinParams{
    public  Map<String, Object> where;
    public  String mainTable;
    public ArrayList<String> columns;
    public Map<String, Map<String, String>> joins;
    public Map<String,String> relation;
    public Map<String,Integer> deeps;
    public Class obj;
    public Boolean isMaster = true;
    public DataSource dataSource;

   public JoinParams(){
       where = new HashMap<>();
       columns = new ArrayList<>();
       joins = new HashMap<>();
       relation=new HashMap<>();
       deeps=new HashMap<>();
    }
}
