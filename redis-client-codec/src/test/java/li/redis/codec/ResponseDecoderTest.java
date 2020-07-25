package li.redis.codec;

import junit.framework.TestCase;
import org.junit.Test;

public class ResponseDecoderTest extends TestCase {

    @Test
    public void testResponseDecode() {
        assertEquals(ResponseDecoder.decodeSimpleString("+OK\r\n"),"OK");
    }
}