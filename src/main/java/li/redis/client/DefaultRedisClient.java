package li.redis.client;

import li.redis.codec.ResponseDecoder;
import li.redis.command.CommandGenerator;
import li.redis.config.RedisConfig;
import li.redis.constants.RedisCommandConstants;

import java.util.List;

public class DefaultRedisClient extends AbstractRedisClient {

    public DefaultRedisClient(String host, int port) {
        super(new RedisConfig(host, port, 10));
    }

    @Override
    public String set(final String key, String value) {
        String command = singleKeyCommand(RedisCommandConstants.SET, key, value);
        return ResponseDecoder.decodeSimpleString(executeAndGetString(command));
    }

    @Override
    public String get(String key) {
        String command = singleKeyCommand(RedisCommandConstants.GET,key);
        return ResponseDecoder.decodeBulkString(executeAndGetString(command));
    }

    @Override
    public boolean del(String key) {
        String command = singleKeyCommand(RedisCommandConstants.DEL,key);
        String result = executeAndGetString(command);
        return "OK".equals(result);
    }

    @Override
    public int incr(String key) {
        String command = singleKeyCommand(RedisCommandConstants.INCR,key);
        String result = executeAndGetString(command);
        return ResponseDecoder.decodeInteger(result);
    }

    @Override
    public int decr(String key) {
        String command = singleKeyCommand(RedisCommandConstants.DECR,key);
        String result = executeAndGetString(command);
        return ResponseDecoder.decodeInteger(result);
    }

    @Override
    public Object eval(String script, List<String> keys, List<String> args) {
        CommandGenerator commandGenerator = CommandGenerator.builder()
                .addString(RedisCommandConstants.EVAL)
                .addString(script);

        commandGenerator.addString(String.valueOf(keys.size()));
        for (String key : keys) {
            commandGenerator.addString(key);
        }
        for (String arg : args) {
            commandGenerator.addString(arg);
        }
        String command = commandGenerator.buildCommand();
        String result = executeAndGetString(command);
        return ResponseDecoder.decodeList(result);
    }



}
