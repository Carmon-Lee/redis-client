package li.redis.client;

import li.redis.command.CommandGenerator;
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

    private byte[] executeCommand(String command) {
        RedisConnection connection = connectionPool.acquireConnection();
        byte[] result = connection.execute(command);
        connectionPool.releaseConnection(connection);
        return result;
    }

    protected String executeAndGetString(String command) {
        byte[] result = executeCommand(command);
        return new String(result);
    }

    protected String singleKeyCommand(String command, String key, String... params) {
        CommandGenerator commandGenerator = CommandGenerator.builder()
                .addString(command)
                .addString(key);
        if (params!=null) {
            for (String param : params) {
                commandGenerator.addString(param);
            }
        }
        return commandGenerator.buildCommand();
    }

}
