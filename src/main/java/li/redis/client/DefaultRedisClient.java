package li.redis.client;

import li.redis.config.RedisConfig;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class DefaultRedisClient extends AbstractRedisClient {
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;

    public DefaultRedisClient(String host, int port) {
        super(new RedisConfig(host, port));
    }

    /**
     * *3
     * $3
     * SET
     * $4
     * name
     * $6
     * lipeng
     */
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
