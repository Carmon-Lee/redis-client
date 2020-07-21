package li.redis.client;

import li.redis.config.RedisConfig;
import li.redis.constants.CommonConstants;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import static li.redis.constants.CommonConstants.NEW_LINE;

public abstract class AbstractRedisClient implements RedisClient {

    private RedisConfig config;
    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private byte[] buffer;

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

    protected String setString(String key, String value) {
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
}
