package li.redis.connection;

import li.redis.config.RedisConfig;
import lombok.NonNull;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

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
            e.printStackTrace();
        }
        return null;
    }

    public void releaseConnection(RedisConnection redisConnection) {
        try {
            pool.put(redisConnection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
