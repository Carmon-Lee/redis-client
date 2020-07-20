package li.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * 使用Jedis连接Redis，用于获取RESP数据格式
 */
public class TestJedis {

    @Test
    public void testSet(){
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.set("book", "three body");
    }

}
