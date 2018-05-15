package com.kkk.entity.constantinfo;

import com.kkk.entity.U2;

import java.io.InputStream;

/**
 * ${DESCRIPTION}
 * Created by z3jjlzt on 2018/5/14.
 */
public class ConstantInvokeDynamic extends ConstantInfo{
    /**
     * 指向一个utf8的索引.表示该类名称
     */
    private int bootStrapMethodAttrIndex;
    /**
     * 名称和描述符
     */
    private int nameandtypeindex;

    @Override
    public void read(InputStream ins) {
        bootStrapMethodAttrIndex = U2.read(ins);
        nameandtypeindex = U2.read(ins);
        System.out.println(" =invokedynamic    #" + bootStrapMethodAttrIndex +",#" + nameandtypeindex);
    }
}
