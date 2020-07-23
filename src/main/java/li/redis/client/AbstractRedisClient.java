package li.redis.client;

import li.redis.config.RedisConfig;
import li.redis.connection.ConnectionPool;
import li.redis.connection.RedisConnection;

/**
 * 抽象层，抽取公共代码
 */
public abstract class AbstractRedisClient implements RedisClient {
    private final ConnectionPool connectionPool;

    public AbstractRedisClient(RedisConfig config) {
        connectionPool = new ConnectionPool(config);
    }

    protected String executeCommand(String command) {
        RedisConnection connection = connectionPool.acquireConnection();
        String result = connection.execute(command);
        connectionPool.releaseConnection(connection);
        return result;
    }
}
