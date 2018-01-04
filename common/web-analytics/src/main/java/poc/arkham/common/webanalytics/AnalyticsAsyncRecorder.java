package poc.arkham.common.webanalytics;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Async;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class AnalyticsAsyncRecorder {

    private static final Logger LOG = LoggerFactory.getLogger(AnalyticsAsyncRecorder.class);

    private StringRedisTemplate strRedisTemplate;

    public AnalyticsAsyncRecorder(StringRedisTemplate strRedisTemplate) {
        this.strRedisTemplate = strRedisTemplate;
    }

    @Async(/* "specificThreadPoolTaskExecutor" */)
        // the default executor is a SimpleAsyncTaskExecutor
        // A custom executor can be used for some Async process or all of them (see RedisConfig).
    void doRecordAsync(String method,  String requestUri) {

        // personal reminder:
        // Methods with @Async annotation can return a CompletableFuture.
        // In such case, the caller should explicitely call result.get() or result.join().
        // If not, there is a risk that the async task is killed before the end of the process.
        // So, if you don't need to orchestrate the async process, just don't return anything.
        //
        // also, to join several futures:
        // CompletableFuture.allOf(record1, record2, record3).join();

        String user = Math.random() < 0.5 ? "John" : "Peter";
        ZSetOperations<String, String> zset = strRedisTemplate.opsForZSet();
        String key = "analytics:" + requestUri + ":" + method;
        Long time = Long.valueOf(new Date().getTime());
        zset.add(key, time.toString(), time.doubleValue());
        // zset.removeRangeByScore() // TODO: remove old values in the set
        strRedisTemplate.expire(key, 10, TimeUnit.DAYS); // set or update the key's time to live

        // TODO track the actions of a specific user :)
        // TODO defer this process

        LOG.info(key + " recorded");
    }


}
