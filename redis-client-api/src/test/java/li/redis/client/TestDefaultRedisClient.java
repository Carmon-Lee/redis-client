package li.redis.client;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
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
    public void testKeys(){
        List<String> keys = client.keys("*");
        System.out.println(Arrays.toString(keys.toArray()));
    }

    @Test
    public void testSet() {
        String set = client.set("nation", "UN");
        System.out.println(set);
    }

    @Test
    public void testGet(){
        String result = client.get("author");
        System.out.println(result);
    }

    @Test
    public void testDel(){
        boolean del = client.del("nation");
        System.out.println(del);
    }

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

    @Test
    public void testIncr(){
        System.out.println(client.incr("counter"));
        System.out.println(client.incr("counter"));
        System.out.println(client.incr("counter"));
        System.out.println(client.decr("counter"));
        System.out.println(client.decr("counter"));
        System.out.println(client.decr("counter"));
    }

    @Test
    public void testEval(){
        Object eval = client.eval("return {KEYS[1],KEYS[2],ARGV[1],ARGV[2]}",
                Lists.newArrayList("key1", "key2"),
                Lists.newArrayList("first", "second"));
        System.out.println(eval);
    }

    @Test
    public void testAppend(){
//        int result = client.append("author", " Holmes");
//        System.out.println(result);
//        System.out.println(client.get("author"));
    }





}
