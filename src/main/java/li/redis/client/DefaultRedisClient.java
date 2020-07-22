package li.redis.client;

import li.redis.command.CommandGenerator;
import li.redis.config.RedisConfig;
import li.redis.constants.RedisCommandConstants;
import li.redis.util.StreamUtil;

import java.io.IOException;

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
        String command = CommandGenerator.builder()
                .addString(RedisCommandConstants.GET)
                .addString(key)
                .buildCommand();
        try {
            outputStream.write(command.getBytes());
            inputStream.read(buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(buffer);
    }

    @Override
    public boolean del(String key) {
        String command = CommandGenerator.builder()
                .addString(RedisCommandConstants.DEL)
                .addString(key)
                .buildCommand();
        try {
            outputStream.write(command.getBytes());
            inputStream.read(buffer);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
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
