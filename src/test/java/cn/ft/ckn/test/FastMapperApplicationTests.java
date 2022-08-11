package cn.ft.ckn.test;

import cn.ft.ckn.fastmapper.FastMapperApplication;
import cn.ft.ckn.fastmapper.component.PageInfo;
import cn.ft.ckn.fastmapper.component.SFunction;
import cn.ft.ckn.fastmapper.config.FastMapperConfig;
import cn.ft.ckn.test.fm.action.OtherOpenAction;
import cn.ft.ckn.test.fm.bean.OtherBill;
import cn.ft.ckn.test.fm.bean.OtherOpen;
import cn.ft.ckn.test.fm.dao.OtherOpenMapper;
import cn.ft.ckn.fastmapper.join.JoinCustomer;
import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = FastMapperApplication.class)
public class FastMapperApplicationTests {
/*    @Test
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
*//*

        Stock stock2=new Stock();
        stock2.setStockName("bbb");
        Stock stock3=new Stock();
        stock3.setStockName("ccc");
        stockList.add(stock1);
        stockList.add(stock2);
        stockList.add(stock3);*//*
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
    }*/
    @Test
    void test9(){
        FastMapperConfig.isOpenSQLPrint=true;
        FastMapperConfig.setDeleted(true,"delete_flag",0,1);
        OtherOpen otherOpen = OtherOpenMapper.SELECT().id().equal(20L).one();
        OtherOpenAction.BaseSelectMapper select = OtherOpenMapper.SELECT();
        select.id().equal(20L);
        select.institutionId().equal(2L);
        List<OtherOpen> otherOpens = select.list();
        List<OtherOpen> opens = OtherOpenMapper.SELECT()
                .institutionId().equal(2L)
                .id().less(100L)
                .page(1, 10)
                .list();
    }
    @Test
    void test10(){
//        FastMapperConfig.isOpenSQLPrint=true;
//        PageInfo<Map<String, Object>> customerPage =
//                new JoinCustomer<>(OtherBill.class).
//                leftJoin(OtherOpen.class
//                , new HashMap<SFunction<OtherBill, ?>, SFunction<OtherOpen, ?>>(){{
//                    put(OtherBill::getOtherOpenId,OtherOpen::getId);
//                }}
//                , new HashMap<SFunction<OtherOpen, ?>, Object>(){{
//                    put(OtherOpen::getInstitutionId,2);
//                }}
//                , OtherOpen::getOrgName)
//                .findPage(1, 5);

/*        List<OtherOpen> all = new JoinCustomer<>(OtherBill.class, OtherBill::getBno, OtherBill::getSpNo).
                leftJoin(OtherOpen.class
                        , new HashMap<SFunction<OtherBill, ?>, SFunction<OtherOpen, ?>>() {{
                            put(OtherBill::getOtherOpenId, OtherOpen::getId);
                        }}
                        , new HashMap<SFunction<OtherOpen, ?>, Object>() {{
                            put(OtherOpen::getInstitutionId, 2);
                        }}
                        , OtherOpen::getOrgName)
                .findAll(OtherOpen.class);*/
//        System.out.println(JSONUtil.toJsonStr(customerPage));
        FastMapperConfig.isOpenSQLPrint=true;
        JoinCustomer<OtherBill> billJoinCustomer = new JoinCustomer<>(OtherBill.class);
        PageInfo<Map<String, Object>> mapPageInfo = new JoinCustomer<>(OtherBill.class)
                .select(OtherBill::getContractNo, OtherBill::getBno)
                .where(OtherBill::getOtherOpenId, 29L)
                .leftJoin(OtherOpen.class, OtherBill::getOtherOpenId, OtherOpen::getId)
                .select(OtherOpen::getOrgName)
                .leftJoinGroup(billJoinCustomer,OtherBill::getBno,OtherBill::getBno)
                .findPage(1, 3);
        System.out.println(JSONUtil.toJsonStr(mapPageInfo));
    }
}
