package li.redis.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RedisConfig {

    /**
     * 服务器地址
     */
    private String host;

    /**
     * 端口
     */
    private int port;
}
