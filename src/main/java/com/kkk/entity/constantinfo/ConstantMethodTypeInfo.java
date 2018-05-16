package com.kkk.entity.constantinfo;

import com.kkk.ReadInfo;
import com.kkk.entity.U2;
import lombok.Data;

import java.io.InputStream;

/**
 * 表示方法类型
 * Created by z3jjlzt on 2018/5/14.
 */
@Data
public class ConstantMethodTypeInfo implements ReadInfo {
    /**
     * 描述符.
     */
    private int descriptorindex;
    private String descriptor;

    @Override
    public void read(InputStream ins) {
        descriptorindex = U2.read(ins);
    }

    @Override
    public String toString() {
        return "  =methodtype    #" + descriptorindex + "    // " + descriptor;
    }
}
