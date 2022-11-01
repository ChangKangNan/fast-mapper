package cn.ft.ckn.fastmapper.util;

import cn.hutool.json.JSONUtil;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.*;

//public class JDBCTest {
//    public static void main(String[] args) throws Exception {
//        DruidDataSource salve = new DruidDataSource();
//        try {
//            salve.setDriverClassName("com.mysql.jdbc.Driver");
//            salve.setUrl("jdbc:mysql://127.0.0.1:3306/test?useSSL=false&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&serverTimezone=UTC");
//            salve.setUsername("root");
//            salve.setPassword("123456");
//            salve.setConnectionErrorRetryAttempts(1);
//            salve.setBreakAfterAcquireFailure(true);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        JDBCUtils jdbcUtils = new JDBCUtils(salve);
//        List<Stock> stocks=new ArrayList<>();
//        for (int i = 1; i <= 50; i++) {
//            Stock stock=new Stock();
//            stock.setStockName("a"+i);
//            stock.setDeleted(false);
//            stock.setCreateTime(new Date());
//            stock.setUpdateTime(new Date());
//            stocks.add(stock);
//        }
//        SqlParameterSource[] sqlParameterSources = SqlParameterSourceUtils.createBatch(stocks);
//        jdbcUtils.updateBatch("insert into stock(id, stock_name, deleted, create_time, update_time) values (:id,:stockName,:deleted,:createTime,:updateTime)",sqlParameterSources);
//        Map<String, Object> params = new HashMap<>();
//        params.put("stock_name", "ccc");
//        params.put("id", 1);
//        String sql = "select * from stock where stock_name= :stock_name and id=:id";
//        Stock stock = jdbcUtils.queryForObject(sql, params, Stock.class);
//        System.out.println(JSONUtil.toJsonStr(stock));
//
//        List<Stock> stockList = jdbcUtils.queryForList("select * from stock", new HashMap<>(), Stock.class);
//        System.out.println(JSONUtil.toJsonStr(stockList));
//
//        Map<String, Object> objectMap = new HashMap<>();
//        objectMap.put("id", 15);
//        Long fromStock = jdbcUtils.queryForUniqueProperty("select id from stock where id=:id", objectMap, Long.class);
//        System.out.println(fromStock);
//
//        Map<String, Object> param2 = new HashMap<>();
//        param2.put("id", 11);
//        List<Map<String, Object>> maps = jdbcUtils.queryForMap("select * from stock where  id< :id", param2);
//        System.out.println(JSONUtil.toJsonStr(maps));

//        Stock stock1=new Stock();
//        stock1.setId(11L);
//        stock1.setStockName("abc");
//        stock1.setCreateTime(new Date());
//        stock1.setUpdateTime(new Date());
//        stock1.setDeleted(false);
//        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(stock1);
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        int insert = jdbcUtils.insert("insert into stock (stock_name,create_time,update_time) values(:stockName,:createTime,:updateTime)", parameterSource, keyHolder);
//        System.out.println(insert);
//        System.out.println(keyHolder.getKey());
//        System.out.println(JSONUtil.toJsonStr(keyHolder.getKeyList()));
//        String[] parameterNames = parameterSource.getParameterNames();
//        System.out.println(JSONUtil.toJsonStr(parameterNames));
//
//    }
//}
