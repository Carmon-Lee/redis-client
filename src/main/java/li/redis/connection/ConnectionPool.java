package li.redis.connection;

import li.redis.config.RedisConfig;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Slf4j
public class ConnectionPool {

    private int poolSize;
    private BlockingQueue<RedisConnection> pool;
    private RedisConfig redisConfig;

    public ConnectionPool(@NonNull RedisConfig redisConfig) {
        this.redisConfig = redisConfig;
        this.poolSize = redisConfig.getPoolSize() <= 0 ? 10 : redisConfig.getPoolSize();
        this.pool = new ArrayBlockingQueue<>(poolSize);
        initPool();
    }

    private void initPool() {
        for (int i = 0; i < poolSize; i++) {
            pool.add(new RedisConnection(redisConfig.getHost(),redisConfig.getPort()));
        }
    }

    public RedisConnection acquireConnection() {
        try {
            return pool.take();
        } catch (InterruptedException e) {
            log.error("interrupted while taking connection from pool,", e);
        }
        return null;
    }

    public void releaseConnection(RedisConnection redisConnection) {
        try {
            pool.put(redisConnection);
        } catch (InterruptedException e) {
            log.error("interrupted while putting connection to pool,", e);
        }
    }
}
