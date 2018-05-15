package com.kkk.entity.constantinfo;

import com.kkk.entity.constantinfo.ConstantDouble;
import com.kkk.entity.constantinfo.ConstantInfo;
import com.kkk.entity.constantinfo.ConstantLong;
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
    private ConstantInfo[] constantInfos;

    public ConstantPool(int constant_pool_count) {
        this.constant_pool_count = constant_pool_count;
        constantInfos = new ConstantInfo[constant_pool_count];
    }

    public void read(InputStream ins) {
        for (int i = 0; i < constant_pool_count; i++) {
            System.out.printf("#" + (i + 1));
            constantInfos[i] = ConstantInfo.getConstantInfoByTag(ins);
            if (constantInfos[i] instanceof ConstantDouble || constantInfos[i] instanceof ConstantLong) {
                i++;
            }
        }
    }
}
