package cn.ft.ckn.fastmapper.thread;

//import cn.hutool.core.util.StrUtil;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//
//import java.util.concurrent.Executor;
//import java.util.concurrent.ThreadPoolExecutor;
//
///**
// * @author ckn
// * @date 2022/7/26
// */
//@Configuration
//@EnableAsync
//public class AsyncThreadPoolConfig {
//
//    @Bean("asyncTaskExecutorPool")
//    public Executor asyncTaskExecutorPool(){
//        ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(3);
//        executor.setMaxPoolSize(5);
//        executor.setQueueCapacity(1);
//        executor.setKeepAliveSeconds(300);
//        executor.setThreadNamePrefix("async-Executor-");
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//        executor.initialize();
//        return executor;
//    }
//}
