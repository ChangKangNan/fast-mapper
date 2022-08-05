package cn.ft.ckn.fastmapper;

import cn.ft.ckn.fastmapper.component.PageInfo;
import cn.ft.ckn.fastmapper.config.FastMapperConfig;
import cn.ft.ckn.fastmapper.fm.Stock;
import cn.ft.ckn.fastmapper.fm.action.StockAction;
import cn.ft.ckn.fastmapper.mapper.StockMapper;
import cn.ft.ckn.fastmapper.util.FastCustomer;
import cn.hutool.json.JSONUtil;
import com.alibaba.druid.pool.DruidDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = FastMapperApplication.class)
public class FastMapperApplicationTests {
    @Test
    void select(){
       Stock stock = StockMapper.SELECT()
                .id().equal(1)
                .stockName().equal("江南仓库")
                .one();
     System.out.println(JSONUtil.toJsonStr(stock));
        System.out.println("----------------------------------------");
        List<Stock> stocks = StockMapper.SELECT()
               // .stockName().equal("江南仓库")
                .id().desc()
                .page(1,3)
                .list();
        System.out.println(JSONUtil.toJsonStr(stocks));
        System.out.println("----------------------------------------");
        DruidDataSource salve=new DruidDataSource();
        try {
            salve.setDriverClassName("com.mysql.jdbc.Driver");
            salve.setUrl("jdbc:mysql://127.0.0.1:3306/test1?useSSL=false&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&serverTimezone=UTC");
            salve.setUsername("root");
            salve.setPassword("123456");
        }catch (Exception e){}
        Stock stock1 = StockMapper.SELECT()
                .setSalveDataSource(salve)
                .id().equal(1)
                .one();
        System.out.println(JSONUtil.toJsonStr(stock1));

        DruidDataSource salve1=new DruidDataSource();
        try {
            salve1.setDriverClassName("com.mysql.jdbc.Driver");
            salve1.setUrl("jdbc:mysql://127.0.0.1:3306/test?useSSL=false&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&serverTimezone=UTC");
            salve1.setUsername("root");
            salve1.setPassword("123456");
        }catch (Exception e){e.printStackTrace();}
        StockAction.SelectMapper selectMapper = StockMapper.SELECT()
                .setSalveDataSource(salve1);
        StockMapper.SELECT()
                .setSalveDataSource(salve);
        Stock stock2 = selectMapper
                .id().equal(1)
                .one();
        System.out.println(JSONUtil.toJsonStr(stock2));
    }

    @Test
    void testInsert(){
        List<Stock> stockList=new ArrayList<>();
        Stock stock1=new Stock();
        stock1.setStockName("aaa");
        Stock stock2=new Stock();
        stock2.setStockName("bbb");
        Stock stock3=new Stock();
        stock3.setStockName("ccc");
        stockList.add(stock1);
        stockList.add(stock2);
        stockList.add(stock3);
        DruidDataSource salve=new DruidDataSource();
        try {
            salve.setDriverClassName("com.mysql.jdbc.Driver");
            salve.setUrl("jdbc:mysql://127.0.0.1:3306/test1?useSSL=false&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&serverTimezone=UTC");
            salve.setUsername("root");
            salve.setPassword("123456");
        }catch (Exception e){e.printStackTrace();}
        StockMapper.INSERT()
                .setSalveDataSource(salve)
                .insertBatch(stockList);
        StockMapper.INSERT()
                .insertBatch(stockList);
    }
    @Test
    void test2(){
/*        StockMapper.UPDATE()
                .id().equal(2)
                .value()
                .set(Stock::getStockName,"bcd")
                .set(Stock::getId,17)
                .execute();*/
        Stock stock=new Stock();
        stock.setStockName("yyyy");
        StockMapper.UPDATE()
                .id().equal(3)
                .update(stock);
    }

    @Test
    void test3(){
        StockMapper.DELETE().id().equal(3).delete();
    }


    @Test
    void test4(){
        FastMapperConfig.isOpenLogicDeletedAuto=true;
        FastMapperConfig.setDeleted("deleted",0,1);
        StockMapper.DELETE().id().equal(4).delete();
    }

    @Test
    void test5(){
        DruidDataSource salve=new DruidDataSource();
        try {
            salve.setDriverClassName("com.mysql.jdbc.Driver");
            salve.setUrl("jdbc:mysql://127.0.0.1:3306/test1?useSSL=false&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&serverTimezone=UTC");
            salve.setUsername("root");
            salve.setPassword("123456");
        }catch (Exception e){e.printStackTrace();}
        PageInfo<Map<String, Object>> page = FastCustomer.create().setSalveDataSource(salve).findPage(new StringBuilder("select id,stock_name from stock"),
                2, 3);
        System.out.println(JSONUtil.toJsonStr(page));
    }
}
