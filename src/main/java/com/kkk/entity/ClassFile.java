package com.kkk.entity;

import com.kkk.ReadInfo;
import com.kkk.entity.attributeinfo.AttrCodeInfo;
import com.kkk.entity.attributeinfo.AttributeBaseInfo;
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
class ClassFile {
    private String magic;
    private int minor_version;
    private int major_version;
    private int constant_pool_count;
    private ReadInfo[] constantinfos;
    private String access_flags;
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

    @SuppressWarnings("WeakerAccess")
    public void init(InputStream inputStream) {
        setMagic(Long.toHexString(U4.read(inputStream)));
        setMinor_version(U2.read(inputStream));
        setMajor_version(U2.read(inputStream));

        //常量池信息
        setConstant_pool_count(U2.read(inputStream));
        constantinfos = new ReadInfo[constant_pool_count];
        for (int i = 1; i < constant_pool_count; i++) {
            constantinfos[i] = Constant.getCPInfoByTag(inputStream);
            if (constantinfos[i] instanceof ConstantDoubleInfo || constantinfos[i] instanceof ConstantLongInfo) {
                i++;
            }
        }
        for (int i = 1; i < constant_pool_count; i++) {
            String simpleName = constantinfos[i].getClass().getSimpleName();
            int index1,index2;
            switch (simpleName) {
                case "ConstantClassInfo":
                    index1 = ((ConstantClassInfo) constantinfos[i]).getNameindex();
                    ((ConstantClassInfo) constantinfos[i]).setName(Utf8ToString(index1));
                    break;
                case "ConstantMethodRefInfo":
                    index1 = ((ConstantMethodRefInfo) constantinfos[i]).getClassindex();
                    ((ConstantMethodRefInfo) constantinfos[i]).setClassname(ClassToString(index1));
                    index2 = ((ConstantMethodRefInfo) constantinfos[i]).getNameandtypeindex();
                    ((ConstantMethodRefInfo) constantinfos[i]).setNameandtype(NameAndTypeToString(index2));
                    break;
                case "ConstantFieldRefInfo":
                     index1 = ((ConstantFieldRefInfo) constantinfos[i]).getClassindex();
                    ((ConstantFieldRefInfo) constantinfos[i]).setClassname(ClassToString(index1));
                     index2 = ((ConstantFieldRefInfo) constantinfos[i]).getNameandtypeindex();
                    ((ConstantFieldRefInfo) constantinfos[i]).setNameandtype(NameAndTypeToString(index2));
                    break;
                case "ConstantStringInfo":
                    index1 = ((ConstantStringInfo) constantinfos[i]).getStringindex();
                    ((ConstantStringInfo) constantinfos[i]).setValue(Utf8ToString(index1));
                    break;
                case "ConstantNameAndTypeInfo":
                    index1 = ((ConstantNameAndTypeInfo) constantinfos[i]).getNameindex();
                    ((ConstantNameAndTypeInfo) constantinfos[i]).setName(Utf8ToString(index1));
                    index2 = ((ConstantNameAndTypeInfo) constantinfos[i]).getDescriptorindex();
                    ((ConstantNameAndTypeInfo) constantinfos[i]).setDescriptor(Utf8ToString(index2));
                    break;
                case "ConstantMethodTypeInfo":
                    index1 = ((ConstantMethodTypeInfo) constantinfos[i]).getDescriptorindex();
                    ((ConstantMethodTypeInfo) constantinfos[i]).setDescriptor(Utf8ToString(index1));
                    break;
                case "ConstantInterfaceMethodRefInfo":
                    index1 = ((ConstantInterfaceMethodRefInfo) constantinfos[i]).getClassindex();
                    ((ConstantInterfaceMethodRefInfo) constantinfos[i]).setClassname(ClassToString(index1));
                    index2 = ((ConstantInterfaceMethodRefInfo) constantinfos[i]).getNameandtypeindex();
                    ((ConstantInterfaceMethodRefInfo) constantinfos[i]).setNameandtype(NameAndTypeToString(index2));
                    break;
                default:
                    break;
            }
            if (constantinfos[i] instanceof ConstantDoubleInfo || constantinfos[i] instanceof ConstantLongInfo) {
                i++;
            }
        }

        //确定继承关系
        setAccess_flags(Integer.toBinaryString(U2.read(inputStream)));
        setThis_class(U2.read(inputStream));
        setSuper_class(U2.read(inputStream));
        setInterfaces_count(U2.read(inputStream));
        interfaceinfos = new InterfaceInfo[interfaces_count];
        for (int i = 0; i < interfaces_count; i++) {
            InterfaceInfo info = new InterfaceInfo();
            info.read(inputStream);
            int class_info_index = ((ConstantClassInfo) constantinfos[info.getName_index()]).getNameindex();
            info.setName(Utf8ToString(class_info_index));
            interfaceinfos[i] = info;
        }

        //字段信息
        setFields_count(U2.read(inputStream));
        fieldinfos = new ReadInfo[fields_count];
        for (int i = 0; i < fields_count; i++) {
            ReadInfo fieldinfo = new FieldInfo();
            fieldinfo.read(inputStream);
            ((FieldInfo) fieldinfo).setName(Utf8ToString(((FieldInfo) fieldinfo).getName_index()));
            ((FieldInfo) fieldinfo).setDescriptor(Utf8ToString(((FieldInfo) fieldinfo).getDescriptor_index()));
            for (AttributeBaseInfo attributeInfo : ((FieldInfo) fieldinfo).getAttributeInfos()) {
                attributeInfo.setAttr_name(Utf8ToString(attributeInfo.getAttr_name_index()));
            }
            fieldinfos[i] = fieldinfo;
        }

        //方法信息
        setMethods_count(U2.read(inputStream));
        methodinfos = new ReadInfo[methods_count];
        for (int i = 0; i < methods_count; i++) {
            ReadInfo methodinfo = new MethodInfo();
            methodinfo.read(inputStream);
            ((MethodInfo) methodinfo).setName(Utf8ToString(((MethodInfo) methodinfo).getName_index()));
            ((MethodInfo) methodinfo).setDescriptor(Utf8ToString(((MethodInfo) methodinfo).getDescriptor_index()));

            //2次解析的方法属性集合
            AttributeBaseInfo[] infos = new AttributeBaseInfo[((MethodInfo) methodinfo).getAttributeInfos().length];
            for (int j = 0; j < ((MethodInfo) methodinfo).getAttributeInfos().length; j++) {
                AttributeBaseInfo attributeBaseInfo = ((MethodInfo) methodinfo).getAttributeInfos()[j];
                attributeBaseInfo.setAttr_name(Utf8ToString(attributeBaseInfo.getAttr_name_index()));
                switch (attributeBaseInfo.getAttr_name()) {
                    case "Code":
                        AttrCodeInfo attrCodeInfo = new AttrCodeInfo();
                        attrCodeInfo.setAttr_name(attributeBaseInfo.getAttr_name());
                        attrCodeInfo.setAttr_name_index(attributeBaseInfo.getAttr_name_index());
                        attrCodeInfo.setAttrs_length(attributeBaseInfo.getAttrs_length());
                        attrCodeInfo.setBytes(attributeBaseInfo.getBytes());
                        attrCodeInfo.parseInstruction();
                        infos[j] = attrCodeInfo;
                        break;
                    default:
                        break;
                }
            }
            ((MethodInfo) methodinfo).setAttributeInfos(infos);
            methodinfos[i] = methodinfo;
        }

        //属性信息
        setAttrs_count(U2.read(inputStream));
        attrinfos = new ReadInfo[attrs_count];
        for (int i = 0; i < attrs_count; i++) {
            ReadInfo attr = new AttributeBaseInfo();
            attr.read(inputStream);
            ((AttributeBaseInfo) attr).setAttr_name(Utf8ToString(((AttributeBaseInfo) attr).getAttr_name_index()));
            attrinfos[i] = attr;
        }
    }

    /**
     * utf8表转string
     * @param index 常量池对应的下标
     * @return 对应的string值
     */
    private String Utf8ToString(int index) {
        return ((ConstantUTF8Info) constantinfos[index]).getValue();
    }

    /**
     * class表转string
     * @param index 常量池对应的下标
     * @return 对应的string值
     */
    private String ClassToString(int index) {
        int nameindex = ((ConstantClassInfo) constantinfos[index]).getNameindex();
        return Utf8ToString(nameindex);
    }

    /**
     * nameandtype表转string
     * @param index 常量池对应的下标
     * @return 对应的string值
     */
    private String NameAndTypeToString(int index) {
        int descriptorindex = ((ConstantNameAndTypeInfo) constantinfos[index]).getDescriptorindex();
        int nameindex = ((ConstantNameAndTypeInfo) constantinfos[index]).getNameindex();
        return Utf8ToString(descriptorindex) + Utf8ToString(nameindex);
    }

    public static void main(String[] args) throws IOException {
        ClassFile classFile = new ClassFile();
        try (InputStream ins = new FileInputStream("F:\\classParse\\src\\main\\AreaTeacherAssistanceServiceImpl.class")) {
            classFile.init(ins);
            printClassFile(classFile);
        }
    }

    /**
     * 输出解析的class文件内容
     * @param classFile classfile文件
     */
    private static void printClassFile(ClassFile classFile) {

        System.out.println("magic = " + classFile.magic);
        System.out.println("minor_version = " + classFile.minor_version);
        System.out.println("major_version = " + classFile.major_version);
        //常量池信息
        System.out.println("cp_count = " + classFile.constant_pool_count);
        System.out.println("Constant Pool:");
        ReadInfo[] constantinfos = classFile.getConstantinfos();
        for (int i = 1; i < constantinfos.length; i++) {
            System.out.println("    #" + i + " " + constantinfos[i]);
        }

    }
}
