package li.redis.client;

public interface RedisClient {

    String set(final String key, String value);

    String get(String key);

    boolean del(String key);

    int incr(String key);

    int decr(String key);
}
