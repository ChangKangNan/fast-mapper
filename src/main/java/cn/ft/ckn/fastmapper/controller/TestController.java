package cn.ft.ckn.fastmapper.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author ckn
 * @date 2022/7/26
 */
@RestController
@Slf4j
public class TestController {
    // 注入自定义线程池bean
    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @RequestMapping("/getThreadPoolInfo")
    public void getThreadPoolInfo(){
        System.out.println("getActiveCount--"+threadPoolExecutor.getActiveCount());
        System.out.println("getCorePoolSize--"+threadPoolExecutor.getCorePoolSize());
        System.out.println("getPoolSize--"+threadPoolExecutor.getPoolSize());
    }

}
