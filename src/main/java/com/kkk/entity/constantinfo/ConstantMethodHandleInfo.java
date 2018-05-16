package com.kkk.entity.constantinfo;

import com.kkk.ReadInfo;
import com.kkk.entity.U1;
import com.kkk.entity.U2;
import lombok.Data;

import java.io.InputStream;

/**
 * 方法句柄
 * Created by z3jjlzt on 2018/5/14.
 */
@Data
public class ConstantMethodHandleInfo implements ReadInfo {
    /**
     * 方法句柄类型.
     */
    private short rerkind;
    /**
     *
     */
    private int refindex;

    @Override
    public void read(InputStream ins) {
        rerkind = U1.read(ins);
        refindex = U2.read(ins);
        System.out.println(" =methodhandle    #" + rerkind +",#" + refindex);
    }

    @Override
    public String toString() {
        return "  =methodhandle    " + refindex + ",#" + refindex;
    }
}
