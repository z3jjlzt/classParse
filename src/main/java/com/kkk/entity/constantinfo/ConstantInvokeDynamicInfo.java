package com.kkk.entity.constantinfo;

import com.kkk.ReadInfo;
import com.kkk.entity.U2;

import java.io.InputStream;

/**
 * 动态方法调用
 * Created by z3jjlzt on 2018/5/14.
 */
public class ConstantInvokeDynamicInfo implements ReadInfo {
    /**
     * 指向一个utf8的索引.表示该类名称
     */
    private int bootstrapmethodattrindex;
    /**
     * 名称和描述符
     */
    private int nameandtypeindex;

    @Override
    public void read(InputStream ins) {
        bootstrapmethodattrindex = U2.read(ins);
        nameandtypeindex = U2.read(ins);
    }

    @Override
    public String toString() {
        return "  =invokedynamic    #" + bootstrapmethodattrindex + ",#" + nameandtypeindex;
    }
}
