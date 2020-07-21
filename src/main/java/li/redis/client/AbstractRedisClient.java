package li.redis.client;

import li.redis.config.RedisConfig;
import li.redis.constants.CommonConstants;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import static li.redis.constants.CommonConstants.NEW_LINE;

/**
 * 抽象层，抽取公共代码
 */
public abstract class AbstractRedisClient {
    protected RedisConfig config;
    protected Socket socket;
    protected OutputStream outputStream;
    protected InputStream inputStream;
    protected byte[] buffer;

    public AbstractRedisClient(RedisConfig config) {
        this.config = config;
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(config.getHost(), config.getPort()));
        outputStream = socket.getOutputStream();
        inputStream = socket.getInputStream();
        buffer = new byte[128];
    }


}
