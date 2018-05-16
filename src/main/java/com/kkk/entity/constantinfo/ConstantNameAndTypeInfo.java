package com.kkk.entity.constantinfo;

import com.kkk.ReadInfo;
import com.kkk.entity.U2;
import lombok.Data;

import java.io.InputStream;

/**
 * ${DESCRIPTION}
 * Created by z3jjlzt on 2018/5/14.
 */
@Data
public class ConstantNameAndTypeInfo implements ReadInfo {
    /**
     * 指向一个utf8的索引.表示该类名称
     */
    private int nameindex;
    /**
     * 名称和描述符
     */
    private int descriptorindex;
    private String name;
    private String descriptor;

    @Override
    public void read(InputStream ins) {
        nameindex = U2.read(ins);
        descriptorindex = U2.read(ins);
    }

    @Override
    public String toString() {
        return "  =nameandtype    #" + nameindex + ",#" + descriptorindex +
                "  // " + name + ":" + descriptor;
    }
}
