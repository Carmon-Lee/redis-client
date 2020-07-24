package li.redis.codec;

import li.redis.constants.ProtocolConstants;
import li.redis.exception.RedisException;

import java.util.List;

import static li.redis.constants.CommonConstants.NEW_LINE;
import static li.redis.constants.ProtocolConstants.EXCEPTION;

public class ResponseDecoder {

    public static String decodeSimpleString(String response) {
        throwExceptionIfNecessary(response);
        if (response.startsWith(ProtocolConstants.SIMPLE_STRING)) {
            return response.substring(1, response.lastIndexOf(NEW_LINE));
        }
        throw new RedisException("响应结果异常");
    }

    public static String decodeBulkString(String response) {
        throwExceptionIfNecessary(response);
        if (response.startsWith(ProtocolConstants.BULK_STRING)) {
            if (response.substring(1).startsWith("-1")) {
                return null;
            } else if (response.substring(1).startsWith("0")) {
                return "";
            } else {
                return response.substring(1, response.lastIndexOf(NEW_LINE));
            }
        }
        throw new RedisException("响应结果异常");
    }

    public static int decodeInteger(String response) {
        throwExceptionIfNecessary(response);
        if (!response.startsWith(ProtocolConstants.NUMBER)) {
            throw new RedisException("Wrong message type, expected int, but response don't correspond",response);
        }
        response = response.replace(NEW_LINE,"");
        return Integer.parseInt(response.substring(1));
    }

    public static List<String> decodeList(String response) {
        throwExceptionIfNecessary(response);
        if (!response.startsWith(ProtocolConstants.ARRAY)) {
            throw new RedisException("Wrong message type, expected int, but response don't correspond",response);
        }
        return null;
    }


    private static void throwExceptionIfNecessary(String response) {
        if (null == response || !response.contains(NEW_LINE)) {
            throw new RuntimeException(String.format("响应结果[%s]异常", response));
        }
        if (response.startsWith(EXCEPTION)) {
            String[] split = response.substring(1, response.lastIndexOf(NEW_LINE)).split(" ", 2);
            throw new RedisException(split[0], split[1]);
        }
    }
}
