package li.redis.codec;

import junit.framework.TestCase;
import org.junit.Test;

public class BulkStringEncoderTest extends TestCase {

    @Test
    public void testBulkEncode() {
        assertEquals(BulkStringEncoder.encode("set"),"$3\r\nset\r\n");
    }
}