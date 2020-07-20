package li.redis.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class DefaultRedisClient implements RedisClient {
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;

    public DefaultRedisClient(String host, int port) {
        try {
            this.socket = new Socket(host, port);
            this.inputStream = this.socket.getInputStream();
            this.outputStream = this.socket.getOutputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        StringBuilder sb = new StringBuilder();
        sb.append("*3").append("\r\n");
        sb.append("$3").append("\r\n");
        sb.append("set").append("\r\n");
        sb.append("$").append(key.length()).append("\r\n");
        sb.append(key).append("\r\n");
        sb.append("$").append(value.length()).append("\r\n");
        sb.append(value).append("\r\n");

        byte[] bytes = new byte[1024];
        try {
            outputStream.write(sb.toString().getBytes());  //把字符串内容写入输出流
            inputStream.read(bytes);  //从输入流中读入字节数组
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(bytes);
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
