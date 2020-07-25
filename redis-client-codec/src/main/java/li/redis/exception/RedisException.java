package li.redis.exception;

import lombok.ToString;

@ToString
public class RedisException extends RuntimeException {

    private String code;
    private String message;

    public RedisException() {
        super();
    }

    public RedisException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public RedisException(String message) {
        super(message);
        this.message = message;
    }

    public RedisException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public RedisException(Throwable cause) {
        super(cause);
    }

    public RedisException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message;
    }
}
