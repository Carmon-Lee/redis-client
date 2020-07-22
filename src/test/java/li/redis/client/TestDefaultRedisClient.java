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
        String set = client.set("city", "shenzhen");
        System.out.println(set);
    }

    @Test
    public void testGet(){
        String name = client.get("book");
        System.out.println(name);
    }

    @Test
    public void testDel(){
        boolean del = client.del("city");
        System.out.println(del);
    }


}
