package li.redis.codec;

import static li.redis.constants.CommonConstants.NEW_LINE;
import static li.redis.constants.ProtocolConstants.BULK_STRING;
import static li.redis.constants.ProtocolConstants.SIMPLE_STRING;

public class DataEncoder {

    public static String encodeSimpleString(String s) {
        return SIMPLE_STRING
                + s
                + NEW_LINE;
    }

    public static String encodeBulkString(String s) {
        return BULK_STRING
                + s.length()
                + NEW_LINE
                + s
                + NEW_LINE;
    }

}
