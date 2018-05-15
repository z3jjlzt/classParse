package com.kkk.entity.constantinfo;

import com.kkk.entity.U1;

import java.io.InputStream;

/**
 * Created by z3jjlzt on 2018/4/26.
 */
public abstract class ConstantInfo {

    /**
     * 常量分类
     */
    public static final int CONSTANT_UTF8 = 1;
    public static final int CONSTANT_INTEGER = 3;
    public static final int CONSTANT_FLOAT = 4;
    public static final int CONSTANT_LONG = 5;
    public static final int CONSTANT_DOUBLE = 6;
    public static final int CONSTANT_CLASS = 7;
    public static final int CONSTANT_STRING = 8;
    public static final int CONSTANT_FIELDREF = 9;
    public static final int CONSTANT_METHODREF = 10;
    public static final int CONSTANT_INTERFACEMETHODREF = 11;
    public static final int CONSTANT_NAMEANDTYPE = 12;
    public static final int CONSTANT_METHODHANDLE = 15;
    public static final int CONSTANT_METHODTYPE = 16;
    public static final int CONSTANT_INVOKEDYNAMIC = 18;

    public abstract void read(InputStream ins);
    /**
     * 通过tag得到常量结构
     * @param ins
     * @return
     */
    public static ConstantInfo getConstantInfoByTag(InputStream ins) {
        int tag = U1.read(ins);
        ConstantInfo result = null;
        switch (tag) {
            case CONSTANT_UTF8: result = new ConstantUTF8();break;
            case CONSTANT_INTEGER: result = new ConstantInteger();break;
            case CONSTANT_FLOAT: result = new ConstantFloat();break;
            case CONSTANT_LONG: result = new ConstantLong();break;
            case CONSTANT_DOUBLE: result = new ConstantDouble();break;
            case CONSTANT_CLASS: result = new ConstantClass();break;
            case CONSTANT_STRING: result = new ConstantString();break;
            case CONSTANT_FIELDREF: result = new ConstantFieldRef();break;
            case CONSTANT_METHODREF: result = new ConstantMethodRef();break;
            case CONSTANT_INTERFACEMETHODREF: result = new ConstantInterfaceMethodRef();break;
            case CONSTANT_NAMEANDTYPE: result = new ConstantNameAndType();break;
            case CONSTANT_METHODHANDLE: result = new ConstantMethodHandle();break;
            case CONSTANT_METHODTYPE: result = new ConstantMethodType();break;
            case CONSTANT_INVOKEDYNAMIC: result = new ConstantInvokeDynamic();break;
            default:throw new RuntimeException("class文件格式有误");
        }
        result.read(ins);
        return result;
    }
}
