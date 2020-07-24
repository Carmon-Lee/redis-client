package li.redis.client;

import li.redis.codec.ResponseDecoder;
import li.redis.command.CommandGenerator;
import li.redis.config.RedisConfig;
import li.redis.constants.RedisCommandConstants;

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


    private String singleKeyCommand(String command, String key, String... params) {
        CommandGenerator commandGenerator = CommandGenerator.builder()
                .addString(command)
                .addString(key);
        if (params!=null) {
            for (String param : params) {
                commandGenerator.addString(param);
            }
        }
        return commandGenerator.buildCommand();
    }
}
