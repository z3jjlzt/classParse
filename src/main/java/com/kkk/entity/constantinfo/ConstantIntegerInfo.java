package com.kkk.entity.constantinfo;

import com.kkk.ReadInfo;
import com.kkk.entity.U4;

import java.io.InputStream;

/**
 * int类型常量
 * Created by z3jjlzt on 2018/5/14.
 */
public class ConstantIntegerInfo implements ReadInfo {
    private long bytes;

    @Override
    public void read(InputStream ins) {
        bytes = U4.read(ins);
    }

    @Override
    public String toString() {
        return "  =integer    " + bytes;
    }
}
