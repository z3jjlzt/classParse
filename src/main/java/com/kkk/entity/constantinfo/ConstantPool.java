package com.kkk.entity.constantinfo;

import com.kkk.ReadInfo;
import lombok.Data;

import java.io.InputStream;

/**
 * Created by z3jjlzt on 2018/4/26.
 */
@Data
public class ConstantPool {

    /**
     * 常量池中常量size为该值减一
     */
    private int constant_pool_count;
    /**
     * cp_count个常量
     */
    private ReadInfo[] constantBaseInfos;

    public ConstantPool(int constant_pool_count) {
        this.constant_pool_count = constant_pool_count;
        constantBaseInfos = new ReadInfo[constant_pool_count];
    }

    public void read(InputStream ins) {
        for (int i = 0; i < constant_pool_count; i++) {
            System.out.printf("#" + (i + 1));
            constantBaseInfos[i] = Constant.getCPInfoByTag(ins);
            if (constantBaseInfos[i] instanceof ConstantDoubleInfo || constantBaseInfos[i] instanceof ConstantLongInfo) {
                i++;
            }
        }
    }
}
