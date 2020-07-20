package li.redis.mockserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 模拟一个服务端，用于获取RESP数据格式
 */
public class RedisServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6379);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();

        byte[] bytes = new byte[1024];
        inputStream.read(bytes);
        System.out.println(new String(bytes));

    }
}
