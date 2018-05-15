package com.kkk.entity.attributeinfo;

import com.kkk.entity.Instruction;
import com.kkk.entity.U2;
import com.kkk.entity.U4;
import com.kkk.entity.constantinfo.ConstantPool;
import com.kkk.entity.constantinfo.ConstantUTF8;

import java.io.IOException;
import java.io.InputStream;

/**
 * ${DESCRIPTION}
 * Created by z3jjlzt on 2018/5/15.
 */
public class AttrCodeInfo extends AttributeInfo {
    private int max_stack;
    private int max_locals;
    private long code_length;
    private byte[] codes;
    private String[] codeStrings;
    private int exception_table_length;
    private ExceptionTableInfo[] exceptionTableInfos;
    private int attr_count;
    private AttributeInfo[] attributeInfos;

    @Override
    public void read(InputStream inputStream, ConstantPool constantPool) {
        attr_name_index = U2.read(inputStream);
        attr_name = ((ConstantUTF8) constantPool.getConstantInfos()[attr_name_index - 1]).getValue();
        attrs_length = U4.read(inputStream);
        if ("Code".equals(attr_name)) {
            max_stack = U2.read(inputStream);
            max_locals = U2.read(inputStream);
            code_length = U4.read(inputStream);
            codes = new byte[((int) code_length)];
            codeStrings = new String[((int) code_length)];
            try {
                inputStream.read(codes, 0, codes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < codes.length; i++) {
                String x = (String) Instruction.INSTRUCTION_SET.get(((codes[i] & 0xff)));
                System.out.println(x);
                codeStrings[i] = x;
            }
            //异常表信息
            exception_table_length = U2.read(inputStream);
            exceptionTableInfos = new ExceptionTableInfo[exception_table_length];
            for (int i = 0; i < exceptionTableInfos.length; i++) {
                ExceptionTableInfo exceptionTableInfo = new ExceptionTableInfo();
                exceptionTableInfo.read(inputStream);
                exceptionTableInfos[i] = exceptionTableInfo;
            }
            //属性信息
            attr_count = U2.read(inputStream);
            attributeInfos = new AttributeInfo[attr_count];
            for (int i = 0; i < attributeInfos.length; i++) {
                AttributeInfo attributeInfo = new AttributeInfo();
                attributeInfo.read(inputStream, constantPool);
                attributeInfos[i] = attributeInfo;
            }
        }else {
            try {
                byte[] bytes = new byte[((int) attrs_length)];
                inputStream.read(bytes, 0, bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
