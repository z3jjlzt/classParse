package com.kkk.entity;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * 对应4个字节，使用long表示
 * Created by z3jjlzt on 2018/4/26.
 */
public class U4 {
    public static long read(InputStream ins) {
        byte[] bytes = new byte[4];
        try {
            ins.read(bytes, 0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long result = 0;
        for (int i = 0; i < bytes.length; i++) {
            result <<= 8;
            int tmp = bytes[i] & 0xff;
            result |= tmp;
        }
        return result;
    }
}
