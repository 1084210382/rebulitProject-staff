package com.example.demo.threadpool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author whn
 */
@Configuration
public class PoolConfig {
    @Bean(name = "executor")
    public ThreadPoolExecutor executor(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(6,
                7,
                5,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(1000),
                (ThreadFactory) Thread::new,
                new ThreadPoolExecutor.DiscardPolicy());
        return threadPoolExecutor;
    }
}
