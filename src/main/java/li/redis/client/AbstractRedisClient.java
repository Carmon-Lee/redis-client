package li.redis.client;

import li.redis.command.CommandGenerator;
import li.redis.config.RedisConfig;
import li.redis.constants.RedisCommandConstants;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public abstract class AbstractRedisClient implements RedisClient {

    private RedisConfig config;
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
        String command = CommandGenerator.builder()
                .addString(RedisCommandConstants.SET)
                .addString(key)
                .addString(value)
                .buildCommand();

        try {
            outputStream.write(command.getBytes());
            inputStream.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(buffer);
    }
}
