package li.redis.codec;

public interface Encoder<T> {

    String encode(T t);
}
