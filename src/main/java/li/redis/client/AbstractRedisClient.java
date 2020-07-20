package li.redis.client;

import li.redis.config.RedisConfig;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public abstract class AbstractRedisClient {

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
        sb.append("*3").append("\r\n");
        sb.append("$3").append("\r\n");
        sb.append("set").append("\r\n");
        sb.append("$").append(key.length()).append("\r\n");
        sb.append(key).append("\r\n");
        sb.append("$").append(value.length()).append("\r\n");
        sb.append(value).append("\r\n");

        try {
            outputStream.write(sb.toString().getBytes());
            inputStream.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(buffer);
    }
}
