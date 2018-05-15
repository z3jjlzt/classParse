package com.kkk.entity.constantinfo;

import com.kkk.entity.U4;

import java.io.InputStream;

/**
 * ${DESCRIPTION}
 * Created by z3jjlzt on 2018/5/14.
 */
public class ConstantInteger extends ConstantInfo{
    private long bytes;

    @Override
    public void read(InputStream ins) {
        bytes = U4.read(ins);
        System.out.println(" =integer    " + bytes);
    }
}
