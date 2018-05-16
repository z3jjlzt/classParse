package com.kkk.entity.constantinfo;

import com.kkk.ReadInfo;
import com.kkk.entity.U2;
import lombok.Data;

import java.io.InputStream;

/**
 * 接口方法
 * Created by z3jjlzt on 2018/5/14.
 */
@Data
public class ConstantInterfaceMethodRefInfo implements ReadInfo {
    /**
     * 指向一个utf8的索引.表示该类名称
     */
    private int classindex;
    /**
     * 名称和描述符
     */
    private int nameandtypeindex;
    private String classname;
    private String nameandtype;

    @Override
    public void read(InputStream ins) {
        classindex = U2.read(ins);
        nameandtypeindex = U2.read(ins);
    }

    @Override
    public String toString() {
        return "  =interfacemethodref    #" + classindex + ",#" + nameandtypeindex +
                "  // " + classname + ":" + nameandtype;
    }
}
