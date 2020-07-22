package li.redis.codec;

import junit.framework.TestCase;
import org.junit.Test;

public class ResponseDecoderTest extends TestCase {

    @Test
    public void testResponseDecode() {
        assertEquals(ResponseDecoder.decodeSimpleString("+OK\r\n"),"OK");
        assertEquals(ResponseDecoder.decodeSimpleString("-WRONGTYPE Operation against a key holding the wrong kind of value\r\n"),"OK");
    }
}