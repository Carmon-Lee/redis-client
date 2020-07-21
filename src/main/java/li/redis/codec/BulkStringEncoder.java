package li.redis.codec;

import static li.redis.constants.ProtocolConstants.*;
import static li.redis.constants.CommonConstants.*;

public class BulkStringEncoder {
    public static String encode(String s) {
        return BULK_STRING
                + s.length()
                + NEW_LINE
                + s
                + NEW_LINE;
    }
}
