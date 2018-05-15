package com.kkk.entity.constantinfo;

import com.kkk.entity.U4;

import java.io.InputStream;

/**
 * ${DESCRIPTION}
 * Created by z3jjlzt on 2018/5/14.
 */
public class ConstantLong extends ConstantInfo{
    private long low_bytes;
    private long high_bytes;

    @Override
    public void read(InputStream ins) {
        high_bytes = U4.read(ins);
        low_bytes = U4.read(ins);
        System.out.println(" =long    " + ((high_bytes << 32) + low_bytes));
    }
}
