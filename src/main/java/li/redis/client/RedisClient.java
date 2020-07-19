package li.redis.client;

public interface RedisClient {


    String get(String key);

    boolean set(String key, int value);

    boolean del(String key);

    int incr(String key);

    int decr(String key);
}
