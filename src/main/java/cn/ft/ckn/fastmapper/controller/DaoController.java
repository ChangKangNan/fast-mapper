package cn.ft.ckn.fastmapper.controller;

import cn.ft.ckn.fastmapper.component.PageInfo;
import cn.ft.ckn.fastmapper.config.FastMapperConfig;
import cn.ft.ckn.fastmapper.fm.Stock;
import cn.ft.ckn.fastmapper.mapper.StockMapper;
import cn.ft.ckn.fastmapper.util.FastCustomer;
import cn.hutool.json.JSONUtil;
import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author ckn
 * @date 2022/7/26
 */
@RestController
@Slf4j
public class DaoController {

    @RequestMapping("/test")
    public void test() {
        FastMapperConfig.isOpenSQLPrint = true;
        FastMapperConfig.setDeleted(true, "deleted", false, true);
        FastMapperConfig.setTimeAuto(true, true);
        FastMapperConfig.setDeleted(true, "deleted", 0, 1);
        DruidDataSource salve = new DruidDataSource();
        try {
            salve.setDriverClassName("com.mysql.jdbc.Driver");
            salve.setUrl("jdbc:mysql://127.0.0.1:3306/test?useSSL=false&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&serverTimezone=UTC");
            salve.setUsername("root");
            salve.setPassword("123456");
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageInfo<Map<String, Object>> page = FastCustomer.create()
                .setSalveDataSource(salve).
                findPage(new StringBuilder("select id,stock_name from stock"),
                        2, 3,new HashMap<>());
        System.out.println(JSONUtil.toJsonStr(page));
    }

}
