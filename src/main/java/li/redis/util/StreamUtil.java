package li.redis.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamUtil {

    public static byte[] drainBytes(InputStream inputStream) throws IOException {
        // 大多数数据长度小于128，分配过长会产生不必要的内存消耗
        byte[] buffer = new byte[128];
        int readCount = inputStream.read(buffer);
        // 假如buffer未占满，则直接返回实际的长度
        if (readCount < buffer.length) {
            return Arrays.copyOf(buffer, readCount);
        }
        // 假如buffer已经满了，可以通过逐次递增申请内存
        List<byte[]> bytesList = new ArrayList<>();
        int totalCount = 0;

        // 假如buffer填满，说明可能还有数据未读完，继续分批次读完
        while (readCount == buffer.length) {
            bytesList.add(buffer);
            totalCount += readCount;

            buffer = new byte[buffer.length * 2];
            readCount = inputStream.read(buffer);
        }
        totalCount += readCount;
        byte[] output = new byte[totalCount];
        int copyIndex = 0;
        for (byte[] bytes : bytesList) {
            System.arraycopy(bytes,0, output, copyIndex, bytes.length);
            copyIndex += bytes.length;
        }
        if (readCount>0) {
            System.arraycopy(buffer,0, output, copyIndex, readCount);
        }
        return output;
    }
}
