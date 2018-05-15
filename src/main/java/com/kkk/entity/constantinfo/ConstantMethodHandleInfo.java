package com.kkk.entity.constantinfo;

import com.kkk.ReadInfo;
import com.kkk.entity.U2;

import java.io.InputStream;

/**
 * ${DESCRIPTION}
 * Created by z3jjlzt on 2018/5/14.
 */
public class ConstantMethodHandleInfo implements ReadInfo {
    /**
     * 方法句柄类型.
     */
    private int rerkind;
    /**
     *
     */
    private int refindex;

    @Override
    public void read(InputStream ins) {
        rerkind = U2.read(ins);
        refindex = U2.read(ins);
        System.out.println(" =methodhandle    #" + rerkind +",#" + refindex);
    }
}
