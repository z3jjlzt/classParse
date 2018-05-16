package com.kkk.entity.attributeinfo;

import com.kkk.ReadInfo;
import com.kkk.entity.Instruction;
import com.kkk.entity.U2;
import com.kkk.entity.U4;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import java.io.IOException;
import java.io.InputStream;

import static com.kkk.entity.Instruction.SET1;
import static com.kkk.entity.Instruction.SET2;

/**
 * ${DESCRIPTION}
 * Created by z3jjlzt on 2018/5/15.
 */
@SuppressWarnings("ALL")
public class AttrCodeInfo extends AttributeBaseInfo implements ReadInfo {
    private int max_stack;
    private int max_locals;
    private long code_length;
    private byte[] codes;
    private String[] codeStrings;
    private int exception_table_length;
    private ExceptionTableInfo[] exceptionTableInfos;
    private int attr_count;
    private AttributeBaseInfo[] attributeInfos;

    public void parseInstruction() {
        InputStream inputStream = new ByteInputStream(getBytes(), getBytes().length);
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
            if (SET1.contains(x)) {
                i++;
                x += codes[i] &0xff;
            }
            if (SET2.contains(x)) {
                i += 2;
                x += codes[i - 1] << 8 | codes[i];
            }
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
        attributeInfos = new AttributeBaseInfo[attr_count];
        for (int i = 0; i < attributeInfos.length; i++) {
            AttributeBaseInfo attributeInfo = new AttributeBaseInfo();
            attributeInfo.read(inputStream);
            attributeInfos[i] = attributeInfo;
        }
    }

    @Override
    public void read(InputStream inputStream) {
//        attr_name_index = U2.read(inputStream);
//        attrs_length = U4.read(inputStream);
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
            attributeInfos = new AttributeBaseInfo[attr_count];
            for (int i = 0; i < attributeInfos.length; i++) {
                AttributeBaseInfo attributeInfo = new AttributeBaseInfo();
                attributeInfo.read(inputStream);
                attributeInfos[i] = attributeInfo;
            }
        } else {
            try {
                byte[] bytes = new byte[((int) attrs_length)];
                inputStream.read(bytes, 0, bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
