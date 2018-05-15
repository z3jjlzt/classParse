package com.kkk;

import com.kkk.entity.attributeinfo.AttributeInfo;
import com.kkk.entity.constantinfo.ConstantPool;
import com.kkk.entity.U2;
import com.kkk.entity.U4;
import com.kkk.entity.constantinfo.ConstantClass;
import com.kkk.entity.constantinfo.ConstantInfo;
import com.kkk.entity.constantinfo.ConstantUTF8;
import com.kkk.entity.fieldinfo.FieldInfo;
import com.kkk.entity.methodinfo.MethodInfo;

import java.io.*;

/**
 * Hello world!
 */
public class App implements Serializable{
    public static final int l1 = 33333333;
    public static final double l2 = 555555555555555d;
    public static final String s1 = "你好呀";
    public static void main(String[] args)  {
        System.out.println(Integer.parseInt("00",  16));
        try (InputStream fileInputStream = new FileInputStream("F:\\classParse\\src\\main\\AreaTeacherAssistanceServiceImpl.class")) {
            System.out.println("magic = " + Long.toHexString(U4.read(fileInputStream)));
            System.out.println("minor_version = " + U2.read(fileInputStream));
            System.out.println("major_version = " + U2.read(fileInputStream));
            //常量池信息
            int cp_count = U2.read(fileInputStream);
            System.out.println("cp_count = " + cp_count);
            System.out.println();
            ConstantPool constantPool = new ConstantPool(cp_count - 1);
            constantPool.read(fileInputStream);
            System.out.println();
            //确定类继承关系
            System.out.println("access_flags = 0x" + Integer.toBinaryString(U2.read(fileInputStream)));
            System.out.println("this_class = " + getNameByIndex(constantPool,U2.read(fileInputStream)));
            System.out.println("super_class = " + getNameByIndex(constantPool,U2.read(fileInputStream)));
            int interface_count = U2.read(fileInputStream);
            System.out.println("interfaces_count = " + interface_count);
            for (int i = 0; i < interface_count; i++) {
                int index = U2.read(fileInputStream);
                System.out.println("interface" + i + " = #" + getNameByIndex(constantPool,index));
            }
            //字段信息
            int field_count = U2.read(fileInputStream);
            System.out.println("field_count = " + field_count);
            for (int i = 0; i < field_count; i++) {
                FieldInfo fieldInfo = new FieldInfo();
                fieldInfo.read(fileInputStream,constantPool);
                System.out.print("field" + i + " name=" + fieldInfo.getName()
                        + ",descriptor=" + fieldInfo.getDescriptor()
                        + ",fieldAttrCount=" + fieldInfo.getAttributes_count());
                for (int j = 0; j < fieldInfo.getAttributes_count(); j++) {
                    System.out.print(" fieldAttrName=" + fieldInfo.getAttributeInfos()[j].getAttr_name());
                }
                System.out.println();
            }

            //方法信息
            int method_count = U2.read(fileInputStream);
            System.out.println("method_count = " + method_count);
            for (int i = 0; i < method_count; i++) {
                MethodInfo methodInfo = new MethodInfo();
                methodInfo.read(fileInputStream,constantPool);
                System.out.print("method" + i + " name=" + methodInfo.getName()
                        + ",descriptor=" + methodInfo.getDescriptor()
                        + ",methodAttrCount=" + methodInfo.getAttributes_count());
                for (int j = 0; j < methodInfo.getAttributes_count(); j++) {
                    System.out.print(" methodAttrName=" + methodInfo.getAttributeInfos()[j].getAttr_name());
                }
                System.out.println();
            }

            //属性信息
            int attr_count = U2.read(fileInputStream);
            System.out.println("attr_count = " + attr_count);
            for (int i = 0; i < attr_count; i++) {
                AttributeInfo attributeInfo = new AttributeInfo();
                attributeInfo.read(fileInputStream,constantPool);
                System.out.println("attr" + i + " name=" + attributeInfo.getAttr_name());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * 通过下标获取该索引的字面值
     * @param index
     * @return
     */
    public static String getNameByIndex(ConstantPool constantPool,int index) {
        ConstantInfo constantInfo = constantPool.getConstantInfos()[index - 1];
        String reslut = null;
        if (constantInfo instanceof ConstantClass) {
            ConstantUTF8 value = ((ConstantUTF8) constantPool.getConstantInfos()[((ConstantClass) constantInfo).getNameindex() - 1]);
            reslut = value.getValue();
        }

        return reslut;
    }

    private String gg() {
        return "dfsd";
    }
}
