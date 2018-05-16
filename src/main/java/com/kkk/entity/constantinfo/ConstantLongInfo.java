package com.kkk.entity.constantinfo;

import com.kkk.ReadInfo;
import com.kkk.entity.U4;

import java.io.InputStream;

/**
 * long类型常量
 * Created by z3jjlzt on 2018/5/14.
 */
public class ConstantLongInfo implements ReadInfo {
    private long low_bytes;
    private long high_bytes;

    @Override
    public void read(InputStream ins) {
        high_bytes = U4.read(ins);
        low_bytes = U4.read(ins);
    }

    @Override
    public String toString() {
        return "  =long    " + (high_bytes << 32 + low_bytes);
    }
}
