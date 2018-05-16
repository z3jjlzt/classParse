package com.kkk.entity;

import java.io.IOException;
import java.io.InputStream;

/**
 * 对应2个字节，使用int表示
 * Created by z3jjlzt on 2018/4/26.
 */
@SuppressWarnings("ALL")
public class U2 {
    public static int read(InputStream ins) {
        byte[] bytes = new byte[2];
        try {
            ins.read(bytes, 0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int result = 0;
        for (int i = 0; i < bytes.length; i++) {
            result <<= 8;
            result |= bytes[i] & 0xff;
        }
        return result;
    }
}
