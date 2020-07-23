package li.redis.client;

import li.redis.command.CommandGenerator;
import li.redis.config.RedisConfig;
import li.redis.constants.RedisCommandConstants;

public class DefaultRedisClient extends AbstractRedisClient {

    public DefaultRedisClient(String host, int port) {
        super(new RedisConfig(host, port, 10));
    }

    @Override
    public String set(final String key, String value) {
        String command = CommandGenerator.builder()
                .addString(RedisCommandConstants.SET)
                .addString(key)
                .addString(value)
                .buildCommand();
        return executeCommand(command);
    }

    @Override
    public String get(String key) {
        String command = CommandGenerator.builder()
                .addString(RedisCommandConstants.GET)
                .addString(key)
                .buildCommand();
        return executeCommand(command);
    }

    @Override
    public boolean del(String key) {
        String command = CommandGenerator.builder()
                .addString(RedisCommandConstants.DEL)
                .addString(key)
                .buildCommand();
        String result = executeCommand(command);
        return "OK".equals(result);
    }

    @Override
    public int incr(String key) {
        return 0;
    }

    @Override
    public int decr(String key) {
        return 0;
    }
}
