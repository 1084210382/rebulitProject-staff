package com.example.demo.threadpool;

import com.example.demo.config.GetBeanUtil;
import com.example.demo.config.RedisService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Callable;

/**
 * @author whn
 */
public class ThreadSingle implements Callable<Object> {
    RedisService redisService = (RedisService) GetBeanUtil.getBean("getRedisService");
    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Object call() throws Exception {
        return redisService.get("name");
    }
}
