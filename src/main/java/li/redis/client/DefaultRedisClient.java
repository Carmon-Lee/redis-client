package li.redis.client;

import li.redis.config.RedisConfig;

public class DefaultRedisClient extends AbstractRedisClient implements RedisClient {

    public DefaultRedisClient(String host, int port) {
        super(new RedisConfig(host, port));
    }

    public String set(final String key, String value) {
        return setString(key, value);
    }

    public String get(String key) {
        return null;
    }

    public boolean del(String key) {
        return false;
    }

    public int incr(String key) {
        return 0;
    }

    public int decr(String key) {
        return 0;
    }
}
