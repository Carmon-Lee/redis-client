package li.redis.command;

import li.redis.codec.BulkStringEncoder;
import li.redis.constants.CommonConstants;
import li.redis.constants.ProtocolConstants;

public class CommandGenerator {

    private int count;
    private StringBuilder sb;

    public static CommandGenerator builder() {
        CommandGenerator generator = new CommandGenerator();
        generator.sb = new StringBuilder();
        return generator;
    }

    public CommandGenerator addString(String str) {
        count++;
        sb.append(BulkStringEncoder.encode(str));
        return this;
    }

    public String buildCommand() {
        sb.insert(0, ProtocolConstants.ARRAY
                + count + CommonConstants.NEW_LINE);
        return sb.toString();
    }
}
