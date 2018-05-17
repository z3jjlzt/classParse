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
import java.util.function.Function;

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
        //标识和版本信息
        initTagAndVersion(inputStream);

        //常量池信息
        initConstantPool(inputStream);

        //确定继承关系
        initInheritance(inputStream);

        //字段信息
        initFieldInfo(inputStream);

        //方法信息
        initMethodInfo(inputStream);

        //属性信息
        initAttrInfo(inputStream);
    }

    private void initAttrInfo(InputStream inputStream) {
        setAttrs_count(U2.read(inputStream));
        attrinfos = new ReadInfo[attrs_count];
        for (int i = 0; i < attrs_count; i++) {
            ReadInfo attr = new AttributeBaseInfo();
            attr.read(inputStream);
            ((AttributeBaseInfo) attr).setAttr_name(utf8ToString(((AttributeBaseInfo) attr).getAttr_name_index()));
            attrinfos[i] = attr;
        }
    }

    private void initMethodInfo(InputStream inputStream) {
        setMethods_count(U2.read(inputStream));
        methodinfos = new ReadInfo[methods_count];
        for (int i = 0; i < methods_count; i++) {
            ReadInfo methodinfo = new MethodInfo();
            methodinfo.read(inputStream);
            ((MethodInfo) methodinfo).setName(utf8ToString(((MethodInfo) methodinfo).getName_index()));
            ((MethodInfo) methodinfo).setDescriptor(utf8ToString(((MethodInfo) methodinfo).getDescriptor_index()));

            //2次解析的方法属性集合
            AttributeBaseInfo[] infos = new AttributeBaseInfo[((MethodInfo) methodinfo).getAttributeInfos().length];
            for (int j = 0; j < ((MethodInfo) methodinfo).getAttributeInfos().length; j++) {
                AttributeBaseInfo attributeBaseInfo = ((MethodInfo) methodinfo).getAttributeInfos()[j];
                attributeBaseInfo.setAttr_name(utf8ToString(attributeBaseInfo.getAttr_name_index()));
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
                        infos[j] = attributeBaseInfo;
                        break;
                }
            }
            ((MethodInfo) methodinfo).setAttributeInfos(infos);
            methodinfos[i] = methodinfo;
        }
    }

    private void initFieldInfo(InputStream inputStream) {
        setFields_count(U2.read(inputStream));
        fieldinfos = new ReadInfo[fields_count];
        for (int i = 0; i < fields_count; i++) {
            ReadInfo fieldinfo = new FieldInfo();
            fieldinfo.read(inputStream);
            ((FieldInfo) fieldinfo).setName(utf8ToString(((FieldInfo) fieldinfo).getName_index()));
            ((FieldInfo) fieldinfo).setDescriptor(utf8ToString(((FieldInfo) fieldinfo).getDescriptor_index()));
            for (AttributeBaseInfo attributeInfo : ((FieldInfo) fieldinfo).getAttributeInfos()) {
                attributeInfo.setAttr_name(utf8ToString(attributeInfo.getAttr_name_index()));
            }
            fieldinfos[i] = fieldinfo;
        }
    }

    private void initInheritance(InputStream inputStream) {
        setAccess_flags(Integer.toBinaryString(U2.read(inputStream)));
        setThis_class(U2.read(inputStream));
        setSuper_class(U2.read(inputStream));
        setInterfaces_count(U2.read(inputStream));
        interfaceinfos = new InterfaceInfo[interfaces_count];
        for (int i = 0; i < interfaces_count; i++) {
            InterfaceInfo info = new InterfaceInfo();
            info.read(inputStream);
            int class_info_index = ((ConstantClassInfo) constantinfos[info.getName_index()]).getNameindex();
            info.setName(utf8ToString(class_info_index));
            interfaceinfos[i] = info;
        }
    }

    private void initConstantPool(InputStream inputStream) {
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
            switch (simpleName) {
                case "ConstantClassInfo":
                    ConstantClassInfo classInfo = (ConstantClassInfo) constantinfos[i];
                    classInfo.setName(utf8ToString(classInfo.getNameindex()));
                    break;
                case "ConstantMethodRefInfo":
                    ConstantMethodRefInfo methodRef = (ConstantMethodRefInfo) constantinfos[i];
                    methodRef.setClassname(class2String(methodRef.getClassindex()));
                    methodRef.setNameandtype(nameAndType2String(methodRef.getNameandtypeindex()));

                    break;
                case "ConstantFieldRefInfo":
                    ConstantFieldRefInfo fieldRef = (ConstantFieldRefInfo) constantinfos[i];
                    fieldRef.setClassname(class2String(fieldRef.getClassindex()));
                    fieldRef.setNameandtype(nameAndType2String(fieldRef.getNameandtypeindex()));

                    break;
                case "ConstantStringInfo":
                    ConstantStringInfo string = (ConstantStringInfo) constantinfos[i];
                    string.setValue(utf8ToString(string.getStringindex()));
                    break;
                case "ConstantNameAndTypeInfo":
                    ConstantNameAndTypeInfo nameAndType = (ConstantNameAndTypeInfo) constantinfos[i];
                    nameAndType.setName(utf8ToString(nameAndType.getNameindex()));
                    nameAndType.setDescriptor(utf8ToString(nameAndType.getDescriptorindex()));
                    break;
                case "ConstantMethodTypeInfo":
                    ConstantMethodTypeInfo methodType = (ConstantMethodTypeInfo) constantinfos[i];
                    methodType.setDescriptor(utf8ToString(methodType.getDescriptorindex()));
                    break;
                case "ConstantInterfaceMethodRefInfo":
                    ConstantInterfaceMethodRefInfo interfaceMethodRef = (ConstantInterfaceMethodRefInfo) constantinfos[i];
                    interfaceMethodRef.setClassname(class2String(interfaceMethodRef.getClassindex()));
                    interfaceMethodRef.setNameandtype(nameAndType2String(interfaceMethodRef.getNameandtypeindex()));
                    break;
                default:
                    break;
            }
            if (constantinfos[i] instanceof ConstantDoubleInfo || constantinfos[i] instanceof ConstantLongInfo) {
                i++;
            }
        }
    }

    private void initTagAndVersion(InputStream inputStream) {
        setMagic(Long.toHexString(U4.read(inputStream)));
        setMinor_version(U2.read(inputStream));
        setMajor_version(U2.read(inputStream));
    }

    /**
     * 将t转换为结果R
     *
     * @param t        原始数据
     * @param function 转换函数
     * @param <T>      原始数据类型
     * @param <R>      返回数据类型
     * @return 转换后的数据
     */
    private static <T, R> R map(T t, Function<T, R> function) {
        return function.apply(t);
    }

    /**
     * 将t两次转换为结果R
     *
     * @param t   原始数据
     * @param f1  转换函数1
     * @param f2  转换函数2
     * @param <T> 原始数据类型
     * @param <Q> 中间数据类型
     * @param <R> 返回数据类型
     * @return 转换后的数据
     */
    private static <T, Q, R> R doubleMap(T t, Function<T, Q> f1, Function<Q, R> f2) {
        return f1.andThen(f2).apply(t);
    }

    /**
     * 获取名称和类型的string
     *
     * @param index 下标
     * @return 对应的string字符串
     */
    private String nameAndType2String(int index) {
        return doubleMap(index,
                x -> ((ConstantNameAndTypeInfo) constantinfos[x]),
                x -> ((ConstantUTF8Info) constantinfos[x.getNameindex()]).getValue() +
                        ((ConstantUTF8Info) constantinfos[x.getDescriptorindex()]).getValue());
    }

    /**
     * 获取class表的string
     *
     * @param index 下标
     * @return 对应的string字符串
     */
    private String class2String(int index) {
        return doubleMap(index,
                x -> ((ConstantClassInfo) constantinfos[x]).getNameindex(),
                x -> ((ConstantUTF8Info) constantinfos[x]).getValue());
    }

    /**
     * 获取utf8表的string
     *
     * @param index 下标
     * @return 对应的string字符串
     */
    private String utf8ToString(int index) {
        return map(index,
                x -> ((ConstantUTF8Info) constantinfos[x]).getValue());
    }

    public static void main(String[] args) throws IOException {
        ClassFile classFile = new ClassFile();
        try (InputStream ins = new FileInputStream("F:\\classParse\\src\\main\\App.class")) {
            classFile.init(ins);
            printClassFile(classFile);
//            System.out.println(classFile);
        }
    }

    /**
     * 输出解析的class文件内容
     *
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
        //继承关系
        System.out.println("access_flag = " + classFile.access_flags);
        System.out.println("this_class = " + classFile.class2String(classFile.this_class));
        System.out.println("super_class = " + classFile.class2String(classFile.super_class));
        System.out.println("interfaces_count = " + classFile.interfaces_count);
        for (int i = 0; i < classFile.interfaces_count; i++) {
            System.out.println("#interface" + (i + 1) + " " + classFile.getInterfaceinfos()[i]);
        }
        //字段信息
        System.out.println("field_count = " + classFile.getFields_count());
        for (int i = 0; i < classFile.fields_count; i++) {
            System.out.println("#field" + (i + 1) + " " + classFile.getFieldinfos()[i]);
        }
        //方法信息
        System.out.println("method_count = " + classFile.methods_count);
        for (int i = 0; i < classFile.methods_count; i++) {
            MethodInfo methodInfo = ((MethodInfo) classFile.getMethodinfos()[i]);
            System.out.println("#method" + (i + 1) + " " + methodInfo.getName() + methodInfo.getDescriptor());
            AttributeBaseInfo[] attributeInfos = methodInfo.getAttributeInfos();
            //noinspection ForLoopReplaceableByForEach
            for (int j = 0; j < attributeInfos.length; j++) {
                String attr_name = attributeInfos[j].getAttr_name();
                switch (attr_name) {
                    case "Code":
                        AttrCodeInfo attributeInfo = (AttrCodeInfo) attributeInfos[j];
                        System.out.println(attributeInfo);
                        break;
                        default:
                            System.out.println(attributeInfos[j].getAttr_name());
                            break;
                }
            }
        }
        //属性信息
        System.out.println("attr_count = " + classFile.attrs_count);
        for (int i = 0; i < classFile.attrs_count; i++) {
            System.out.println("#attr" + (i + 1) + " " + ((AttributeBaseInfo) classFile.getAttrinfos()[i]).getAttr_name());
        }
    }
}
