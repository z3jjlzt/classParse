package com.kkk.entity.constantinfo;

import com.kkk.ReadInfo;
import com.kkk.entity.U1;

import java.io.InputStream;

/**
 * Created by z3jjlzt on 2018/4/26.
 */
public class Constant {

    /**
     * 常量池tag分类
     */
    private static final int CONSTANT_UTF8 = 1;
    private static final int CONSTANT_INTEGER = 3;
    private static final int CONSTANT_FLOAT = 4;
    private static final int CONSTANT_LONG = 5;
    private static final int CONSTANT_DOUBLE = 6;
    private static final int CONSTANT_CLASS = 7;
    private static final int CONSTANT_STRING = 8;
    private static final int CONSTANT_FIELDREF = 9;
    private static final int CONSTANT_METHODREF = 10;
    private static final int CONSTANT_INTERFACEMETHODREF = 11;
    private static final int CONSTANT_NAMEANDTYPE = 12;
    private static final int CONSTANT_METHODHANDLE = 15;
    private static final int CONSTANT_METHODTYPE = 16;
    private static final int CONSTANT_INVOKEDYNAMIC = 18;

    /**
     * 通过tag得到常量池对应表信息
     * @param ins 数据流
     * @return 返回对应的表信息
     */
    public static ReadInfo getCPInfoByTag(InputStream ins) {
        int tag = U1.read(ins);
        ReadInfo result;
        switch (tag) {
            case CONSTANT_UTF8: result = new ConstantUTF8Info();break;
            case CONSTANT_INTEGER: result = new ConstantIntegerInfo();break;
            case CONSTANT_FLOAT: result = new ConstantFloatInfo();break;
            case CONSTANT_LONG: result = new ConstantLongInfo();break;
            case CONSTANT_DOUBLE: result = new ConstantDoubleInfo();break;
            case CONSTANT_CLASS: result = new ConstantClassInfo();break;
            case CONSTANT_STRING: result = new ConstantStringInfo();break;
            case CONSTANT_FIELDREF: result = new ConstantFieldRefInfo();break;
            case CONSTANT_METHODREF: result = new ConstantMethodRefInfo();break;
            case CONSTANT_INTERFACEMETHODREF: result = new ConstantInterfaceMethodRefInfo();break;
            case CONSTANT_NAMEANDTYPE: result = new ConstantNameAndTypeInfo();break;
            case CONSTANT_METHODHANDLE: result = new ConstantMethodHandleInfo();break;
            case CONSTANT_METHODTYPE: result = new ConstantMethodTypeInfo();break;
            case CONSTANT_INVOKEDYNAMIC: result = new ConstantInvokeDynamicInfo();break;
            default:throw new RuntimeException("class文件格式有误");
        }
        result.read(ins);
        return result;
    }
}
