package com.kkk.entity;

import com.kkk.ReadInfo;
import com.kkk.entity.attributeinfo.AttributeInfo;
import com.kkk.entity.constantinfo.*;
import com.kkk.entity.fieldinfo.FieldInfo;
import com.kkk.entity.methodinfo.MethodInfo;
import lombok.Data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * classfile文件格式
 */
@Data
public class ClassFile {
    private String magic;
    private int minor_version;
    private int major_version;
    private int constant_pool_count;
    private ReadInfo[] constantinfos;
    private String  access_flags;
    private int this_class;
    private int super_class;
    private int interfaces_count;
    private ReadInfo[] interfaceinfos;
    private int fields_count;
    private ReadInfo[] fieldinfos;
    private int methods_count;
    private ReadInfo[] methodinfos;
    private int attrs_count;
    private ReadInfo[] attrinfos;

    public void init(InputStream inputStream) {
        setMagic(Long.toHexString(U4.read(inputStream)));
        setMinor_version(U2.read(inputStream));
        setMajor_version(U2.read(inputStream));

        //常量池信息
        setConstant_pool_count(U2.read(inputStream));
        constantinfos = new ReadInfo[constant_pool_count - 1];
        for (int i = 0; i < constant_pool_count - 1; i++) {
            constantinfos[i] = Constant.getCPInfoByTag(inputStream);
            if (constantinfos[i] instanceof ConstantDoubleInfo || constantinfos[i] instanceof ConstantLongInfo) {
                i++;
            }
        }

        //确定继承关系
        setAccess_flags(Integer.toBinaryString(U2.read(inputStream)));
        setThis_class(U2.read(inputStream));
        setSuper_class(U2.read(inputStream));
        setInterfaces_count(U2.read(inputStream));
        interfaceinfos = new ConstantInterfaceInfo[interfaces_count];
        for (int i = 0; i < interfaces_count; i++) {
            ConstantInterfaceInfo info = new ConstantInterfaceInfo();
            info.read(inputStream);
            int class_info_index = ((ConstantClassInfo) constantinfos[info.getName_index() - 1]).getNameindex() - 1;
            info.setName(Utf8ToString(class_info_index));
            interfaceinfos[i] = info;
        }

        //字段信息
        setFields_count(U2.read(inputStream));
        fieldinfos = new ReadInfo[fields_count];
        for (int i = 0; i < fields_count; i++) {
            ReadInfo fieldinfo = new FieldInfo();
            fieldinfo.read(inputStream);
            ((FieldInfo) fieldinfo).setName(Utf8ToString(((FieldInfo) fieldinfo).getName_index() - 1));
            ((FieldInfo) fieldinfo).setDescriptor(Utf8ToString(((FieldInfo) fieldinfo).getDescriptor_index() - 1));
            for (AttributeInfo attributeInfo : ((FieldInfo) fieldinfo).getAttributeInfos()) {
                attributeInfo.setAttr_name(Utf8ToString(attributeInfo.getAttr_name_index() - 1));
            }
            fieldinfos[i] = fieldinfo;
        }

        //方法信息
        setMethods_count(U2.read(inputStream));
        methodinfos = new ReadInfo[methods_count];
        for (int i = 0; i < methods_count; i++) {
            ReadInfo methodinfo = new MethodInfo();
            methodinfo.read(inputStream);
            ((MethodInfo) methodinfo).setName(Utf8ToString(((MethodInfo) methodinfo).getName_index() - 1));
            ((MethodInfo) methodinfo).setDescriptor(Utf8ToString(((MethodInfo) methodinfo).getDescriptor_index() - 1));
            for (AttributeInfo attributeInfo : ((MethodInfo) methodinfo).getAttributeInfos()) {
                attributeInfo.setAttr_name(Utf8ToString(attributeInfo.getAttr_name_index() - 1));
            }
            methodinfos[i] = methodinfo;
        }

        //属性信息
        setAttrs_count(U2.read(inputStream));
        attrinfos = new ReadInfo[attrs_count];
        for (int i = 0; i < attrs_count; i++) {
            ReadInfo attr = new AttributeInfo();
            attr.read(inputStream);
            ((AttributeInfo) attr).setAttr_name(Utf8ToString(((AttributeInfo) attr).getAttr_name_index() - 1));
            attrinfos[i] = attr;

        }
    }

    private  String Utf8ToString(int index) {
        return ((ConstantUTF8Info) constantinfos[index]).getValue();
    }

    public static void main(String[] args) throws IOException {
        ClassFile classFile = new ClassFile();
        try (InputStream ins = new FileInputStream("F:\\classParse\\src\\main\\AreaTeacherAssistanceServiceImpl.class")) {
            classFile.init(ins);
        }
    }
}
