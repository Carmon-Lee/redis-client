package li.redis.codec;

import com.sun.org.apache.bcel.internal.generic.NEW;
import li.redis.constants.CommonConstants;
import li.redis.constants.ProtocolConstants;

import static li.redis.constants.CommonConstants.NEW_LINE;

public class BulkStringEncoder {

    public static String encode(String s) {
        return ProtocolConstants.BULK_STRING +
                s.length() +
                NEW_LINE +
                s +
                NEW_LINE;
    }
}
