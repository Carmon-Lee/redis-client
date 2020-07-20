package li.redis.client;

import org.junit.Before;
import org.junit.Test;

public class TestDefaultRedisClient {

    private DefaultRedisClient client;

    @Before
    public void init(){
         client = new DefaultRedisClient("localhost", 6379);
    }

    @Test
    public void testSet(){
        String set = client.set("hello", "Hello Redis");
        System.out.println(set);
    }


}
