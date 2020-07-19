package li.redis.client;

public class DefaultRedisClient implements RedisClient {
    public String get(String key) {
        return null;
    }

    public boolean set(String key, int value) {
        return false;
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
