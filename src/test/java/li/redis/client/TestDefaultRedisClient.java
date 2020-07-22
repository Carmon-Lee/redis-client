package li.redis.client;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestDefaultRedisClient {

    private DefaultRedisClient client;

    @Before
    public void init() {
        client = new DefaultRedisClient("localhost", 6379);
    }

    @Test
    public void testSet() {
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

    // todo 这种方式存在多线程问题，会发生粘包，可能一次接收到多个结果，需要修改实现方式
    @Test
    public void testMultiThreadSet() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Map<String,String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 10000; i++) {
            executorService.submit(() -> map.put(client.set("foo","bar"),""));
        }
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 正常来说，假如客户端实现方式是线程安全的，那么这里只会存在一个key,即：+OK
        System.out.println(map.keySet());
    }


}
