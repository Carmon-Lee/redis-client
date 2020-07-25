package li.redis.client;

import java.util.List;

public interface RedisClient {

    String set(final String key, String value);

    String get(String key);

    boolean del(String key);

    int incr(String key);

    int decr(String key);

    Object eval(String script, List<String> keys, List<String> args);
}
