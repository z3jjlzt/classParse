package com.kkk.entity;

import java.io.IOException;
import java.io.InputStream;

/**
 * 对应1个字节，使用short表示
 * Created by z3jjlzt on 2018/4/26.
 */
@SuppressWarnings("ALL")
public class U1 {
    public static short read(InputStream ins) {
        byte[] bytes = new byte[1];
        try {
            ins.read(bytes, 0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ((short) (bytes[0] & 0xff));
    }
}
