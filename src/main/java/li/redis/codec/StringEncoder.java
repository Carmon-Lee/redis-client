package li.redis.codec;

import static li.redis.constants.CommonConstants.*;
import static li.redis.constants.ProtocolConstants.*;

public class StringEncoder {

    public static String encode(String s) {
        return SIMPLE_STRING
                + s
                + NEW_LINE;
    }

}
