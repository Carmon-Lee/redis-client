package li.redis.client;

import com.google.common.collect.Lists;
import li.redis.codec.ResponseDecoder;
import li.redis.config.RedisConfig;
import li.redis.constants.RedisCommandConstants;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DefaultRedisClient extends AbstractRedisClient {

    public DefaultRedisClient(String host, int port) {
        super(new RedisConfig(host, port, 10));
    }

    @Override
    public List<String> keys(String pattern) {
        String command = getSingleKeyCommand(RedisCommandConstants.KEYS, pattern);
        String result = executeAndGetString(command);
        return ResponseDecoder.decodeList(result);
    }

    @Override
    public String set(String key, String value) {
        String command = getSingleKeyCommand(RedisCommandConstants.SET, key, value);
        return ResponseDecoder.decodeSimpleString(executeAndGetString(command));
    }

    @Override
    public String get(String key) {
        String command = getSingleKeyCommand(RedisCommandConstants.GET, key);
        return ResponseDecoder.decodeBulkString(executeAndGetString(command));
    }

    @Override
    public boolean del(String key) {
        String command = getSingleKeyCommand(RedisCommandConstants.DEL, key);
        String result = executeAndGetString(command);
        return "OK".equals(result);
    }

    @Override
    public int incr(String key) {
        String command = getSingleKeyCommand(RedisCommandConstants.INCR, key);
        String result = executeAndGetString(command);
        return ResponseDecoder.decodeInteger(result);
    }

    @Override
    public int decr(String key) {
        String command = getSingleKeyCommand(RedisCommandConstants.DECR, key);
        String result = executeAndGetString(command);
        return ResponseDecoder.decodeInteger(result);
    }

    @Override
    public Object eval(String script, List<String> keys, List<String> args) {
        ArrayList<String> list = Lists.newArrayList(String.valueOf(keys.size()));
        list.addAll(keys);
        list.addAll(args);
        String command = getSingleKeyCommand(RedisCommandConstants.EVAL, script,
                list.toArray(new String[0]));
        String result = executeAndGetString(command);
        return ResponseDecoder.decodeList(result);
    }

    @Override
    public int append(String key, String value) {
        String command = getSingleKeyCommand(RedisCommandConstants.APPEND, key, value);
        String result = executeAndGetString(command);
        return ResponseDecoder.decodeInteger(result);
    }


}
