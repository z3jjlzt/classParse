package com.kkk.entity.constantinfo;

import com.kkk.ReadInfo;
import com.kkk.entity.U2;

import java.io.InputStream;

/**
 * ${DESCRIPTION}
 * Created by z3jjlzt on 2018/5/14.
 */
public class ConstantMethodTypeInfo implements ReadInfo {
    /**
     * 描述符.
     */
    private int descriptorindex;

    @Override
    public void read(InputStream ins) {
        descriptorindex = U2.read(ins);
        System.out.println(" =methodtype    #" + descriptorindex);
    }
}
