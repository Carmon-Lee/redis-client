package li.redis.util;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class StreamUtilTest extends TestCase {

    @Test
    public void test() throws IOException {
        String inputstring = "Hello redis";
        ByteArrayInputStream bais = new ByteArrayInputStream(inputstring.getBytes());

        byte[] bytes = StreamUtils.drainBytes(bais);
        System.out.println(new String(bytes));
        assertEquals(inputstring, new String(bytes));

        inputstring = "new ByteArrayInputStream(inputstring.getBytes())";
        bais = new ByteArrayInputStream(inputstring.getBytes());

        bytes = StreamUtils.drainBytes(bais);
        System.out.println(new String(bytes));
        assertEquals(inputstring, new String(bytes));
    }
}