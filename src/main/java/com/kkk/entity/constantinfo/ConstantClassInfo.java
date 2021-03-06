package com.kkk.entity.constantinfo;

import com.kkk.ReadInfo;
import com.kkk.entity.U2;
import lombok.Data;

import java.io.InputStream;

/**
 * 类类型
 * Created by z3jjlzt on 2018/5/14.
 */
@Data
public class ConstantClassInfo implements ReadInfo {
    /**
     * 指向一个utf8的索引.表示该类名称
     */
    private int nameindex;
    private String name;

    @Override
    public void read(InputStream ins) {
        nameindex = U2.read(ins);
    }

    @Override
    public String toString() {
        return "  =class    #" + nameindex + " //" + name;
    }
}
