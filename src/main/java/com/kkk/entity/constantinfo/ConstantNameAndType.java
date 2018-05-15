package com.kkk.entity.constantinfo;

import com.kkk.entity.U2;

import java.io.InputStream;

/**
 * ${DESCRIPTION}
 * Created by z3jjlzt on 2018/5/14.
 */
public class ConstantNameAndType extends ConstantInfo{
    /**
     * 指向一个utf8的索引.表示该类名称
     */
    private int nameindex;
    /**
     * 名称和描述符
     */
    private int descriptorindex;

    @Override
    public void read(InputStream ins) {
        nameindex = U2.read(ins);
        descriptorindex = U2.read(ins);
        System.out.println(" =nameandtype    #" + nameindex +",#" + descriptorindex);
    }
}
