package li.redis.client;

import li.redis.config.RedisConfig;

import java.io.IOException;

import static li.redis.constants.CommonConstants.NEW_LINE;

public class DefaultRedisClient extends AbstractRedisClient {

    public DefaultRedisClient(String host, int port) {
        super(new RedisConfig(host, port));
    }

    @Override
    public String set(final String key, String value) {
        StringBuilder sb = new StringBuilder();
        sb.append("*3").append(NEW_LINE);
        sb.append("$3").append(NEW_LINE);
        sb.append("set").append(NEW_LINE);
        sb.append("$").append(key.length()).append(NEW_LINE);
        sb.append(key).append(NEW_LINE);
        sb.append("$").append(value.length()).append(NEW_LINE);
        sb.append(value).append(NEW_LINE);

        try {
            outputStream.write(sb.toString().getBytes());
            inputStream.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(buffer);
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
