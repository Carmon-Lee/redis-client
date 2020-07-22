package li.redis.client;

import li.redis.command.CommandGenerator;
import li.redis.config.RedisConfig;
import li.redis.constants.RedisCommandConstants;
import li.redis.util.StreamUtil;

import java.io.IOException;

public class DefaultRedisClient extends AbstractRedisClient {

    public DefaultRedisClient(String host, int port) {
        super(new RedisConfig(host, port));
    }

    @Override
    public String set(final String key, String value) {
        String command = CommandGenerator.builder()
                .addString(RedisCommandConstants.SET)
                .addString(key)
                .addString(value)
                .buildCommand();
        try {
            outputStream.write(command.getBytes());
            return new String(StreamUtil.drainBytes(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String get(String key) {
        return null;
    }

    @Override
    public boolean del(String key) {
        return false;
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
