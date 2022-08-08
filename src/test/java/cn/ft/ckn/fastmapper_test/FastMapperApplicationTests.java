package cn.ft.ckn.fastmapper_test;

import cn.ft.ckn.fastmapper.FastMapperApplication;
import cn.ft.ckn.fastmapper.component.PageInfo;
import cn.ft.ckn.fastmapper.config.FastMapperConfig;
import cn.ft.ckn.fastmapper.fm.Stock;
import cn.ft.ckn.fastmapper.fm.Stock1;
import cn.ft.ckn.fastmapper.fm.action.StockAction;
import cn.ft.ckn.fastmapper.join.JoinCustomer;
import cn.ft.ckn.fastmapper.mapper.StockMapper;
import cn.ft.ckn.fastmapper.util.FastCustomer;
import cn.hutool.json.JSONUtil;
import com.alibaba.druid.pool.DruidDataSource;
import org.junit.jupiter.api.Test;
import org.omg.CORBA.TRANSACTION_MODE;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest(classes = FastMapperApplication.class)
public class FastMapperApplicationTests {
    @Test
    void select(){
        FastMapperConfig.isOpenSQLPrint=true;
       Stock stock = StockMapper.SELECT()
                .id().equal(1L)
                .stockName().equal("江南仓库")
                .one();

       StockMapper.SELECT()
               .id().equal(2L)
               .id().desc();
        List<Stock> stocks = StockMapper.SELECT()
               // .stockName().equal("江南仓库")
                .id().desc()
                .page(1,3)
                .list();
        DruidDataSource salve=new DruidDataSource();
        try {
            salve.setDriverClassName("com.mysql.jdbc.Driver");
            salve.setUrl("jdbc:mysql://127.0.0.1:3306/test1?useSSL=false&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&serverTimezone=UTC");
            salve.setUsername("root");
            salve.setPassword("123456");
        }catch (Exception e){}
        Stock stock1 = StockMapper.SELECT()
                .setSalveDataSource(salve)
                .id().equal(1L)
                .one();
        DruidDataSource salve1=new DruidDataSource();
        try {
            salve1.setDriverClassName("com.mysql.jdbc.Driver");
            salve1.setUrl("jdbc:mysql://127.0.0.1:3306/test1?useSSL=false&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&serverTimezone=UTC");
            salve1.setUsername("root");
            salve1.setPassword("123456");
            salve1.setConnectionErrorRetryAttempts(1);
            salve1.setBreakAfterAcquireFailure(true);
        }catch (Exception e){e.printStackTrace();}
        Stock stock2 = StockMapper.SELECT()
                .setSalveDataSource(salve1)
                .id().equal(1L)
                .one();
    }

    @Test
    void testInsert(){
        FastMapperConfig.setDeleted(true,"deleted",false,true);
        FastMapperConfig.setTimeAuto(true,true);
        List<Stock> stockList=new ArrayList<>();
        Stock stock1=new Stock();
        stock1.setStockName("aaa");
        StockMapper.INSERT().insert(stock1);
/*

        Stock stock2=new Stock();
        stock2.setStockName("bbb");
        Stock stock3=new Stock();
        stock3.setStockName("ccc");
        stockList.add(stock1);
        stockList.add(stock2);
        stockList.add(stock3);*/
//        DruidDataSource salve=new DruidDataSource();
//        try {
//            salve.setDriverClassName("com.mysql.jdbc.Driver");
//            salve.setUrl("jdbc:mysql://127.0.0.1:3306/test-salve?useSSL=false&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&serverTimezone=UTC");
//            salve.setUsername("root");
//            salve.setPassword("123456");
//        }catch (Exception e){e.printStackTrace();}
//        StockMapper.INSERT()
//                .setSalveDataSource(salve)
//                .insertBatch(stockList);
//        StockMapper.INSERT()
//                .insertBatch(stockList);
    }
    @Test
    void test2(){
        FastMapperConfig.isOpenSQLPrint=true;
        FastMapperConfig.setTimeAuto(true,true);
        FastMapperConfig.setDeleted(true,"deleted",0,1);
        StockMapper.UPDATE()
                .id().equal(2L)
                .stockName().in("e")
                .or()
                .id().equal(15L)
                .value()
                .set(Stock::getStockName,"江Bei")
                .execute();
        Stock stock=new Stock();
        stock.setStockName("yyyy");
        StockMapper.UPDATE()
                .id().in(2L,4L,5L)
                .or()
                .id().equal(1L)
                .update(stock);
    }

    @Test
    void test3(){
        FastMapperConfig.isOpenSQLPrint=true;
        FastMapperConfig.setTimeAuto(true,true);
        FastMapperConfig.setDeleted(true,"deleted",0,1);
      //  StockMapper.DELETE().id().equal(3).closeDeletedProtect().delete();
        StockMapper.DELETE().id().equal(6L).or().stockName().equal("aaa").delete();
    }


    @Test
    void test4(){
        FastMapperConfig.isOpenSQLPrint=true;
        FastMapperConfig.setTimeAuto(true,true);
        FastMapperConfig.setDeleted(true,"deleted",0,1);
        StockMapper.DELETE().id().in(2L,4L,5L).or().id().equal(1L).closeDeletedProtect().delete();
    }

    @Test
    void test5(){
        DruidDataSource salve=new DruidDataSource();
        try {
            salve.setDriverClassName("com.mysql.jdbc.Driver");
            salve.setUrl("jdbc:mysql://127.0.0.1:3306/test?useSSL=false&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&serverTimezone=UTC");
            salve.setUsername("root");
            salve.setPassword("123456");
        }catch (Exception e){e.printStackTrace();}
        PageInfo<Map<String, Object>> page = FastCustomer.create()
                .setSalveDataSource(salve).
                findPage(new StringBuilder("select id,stock_name from stock"),
                2, 3,new HashMap<>());
        System.out.println(JSONUtil.toJsonStr(page));
    }

    @Test
    void test6(){
        FastMapperConfig.isOpenSQLPrint=true;
        FastMapperConfig.setDeleted(true,"deleted",0,1);
        Stock stock = StockMapper.SELECT()
                .id().equal(1L)
                .or()
                .stockName().equal("江南仓库")
                .stockName().equal("bbb")
                .one();
        System.out.println(JSONUtil.toJsonStr(stock));
    }

    @Test
    void test7(){
        FastMapperConfig.setDeleted(true,"deleted",0,1);
        List<Stock> stocks = StockMapper.SELECT()
                .id().in(2L, 4L, 5L)
                .list();
        System.out.println(JSONUtil.toJsonStr(stocks));
    }

    @Test
    void test8(){
        List<Stock> stocks = FastCustomer.create().selectByFile("/test.sql", new HashMap<>(), Stock.class);
        System.out.println(JSONUtil.toJsonStr(stocks));
    }
    @Test
    void test9(){


    }

}
