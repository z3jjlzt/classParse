package com.kkk.entity.constantinfo;

import com.kkk.ReadInfo;
import com.kkk.entity.U2;

import java.io.InputStream;

/**
 * ${DESCRIPTION}
 * Created by z3jjlzt on 2018/5/14.
 */
public class ConstantStringInfo implements ReadInfo {
    /**
     * 指向一个utf8的索引.表示该string内容
     */
    private int stringindex;

    @Override
    public void read(InputStream ins) {
        stringindex = U2.read(ins);
        System.out.println(" =string    #" + stringindex);
    }
}
