package com.kkk.entity.attributeinfo;

import com.kkk.entity.Instruction;
import com.kkk.entity.U2;
import com.kkk.entity.U4;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import lombok.Data;

import java.io.IOException;
import java.io.InputStream;

import static com.kkk.entity.Instruction.SET1;
import static com.kkk.entity.Instruction.SET2;

/**
 * ${DESCRIPTION}
 * Created by z3jjlzt on 2018/5/15.
 */
@SuppressWarnings("ALL")
@Data
public class AttrCodeInfo extends AttributeBaseInfo{
    private int max_stack;
    private int max_locals;
    private long code_length;
    private byte[] codes;
    private String codeStrings;
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
        try {
            inputStream.read(codes, 0, codes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < codes.length; i++) {
            String x = (String) Instruction.INSTRUCTION_SET.get(((codes[i] & 0xff)));
            if (SET1.contains(x)) {
                i++;
                x += " ";
                x += codes[i] &0xff;
            }
            if (SET2.contains(x)) {
                i += 2;
                x += " ";
                x += codes[i - 1] << 8 | codes[i];
            }
//            System.out.println(x);
            sb.append(x).append("\n");
        }
        codeStrings = sb.toString();
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
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("max_stack = ").append(max_stack).append(",maxlocals = ").append(max_locals)
                .append("\n").append(codeStrings);
        return sb.toString();
    }
}
