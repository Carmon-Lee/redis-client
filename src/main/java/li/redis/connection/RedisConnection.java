package li.redis.connection;

import li.redis.util.StreamUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

@Slf4j
public class RedisConnection {

    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;

    public RedisConnection(String host, int port) {
        socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(host, port));
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();
        } catch (IOException e) {
            log.error("Creating Socket error,", e);
        }
    }

    public String execute(String command) {
        try {
            outputStream.write(command.getBytes());
            byte[] bytes = StreamUtil.drainBytes(inputStream);
            return new String(bytes);
        } catch (IOException e) {
            log.error("writing command or receiving response error,", e);
        }
        return null;
    }
}
