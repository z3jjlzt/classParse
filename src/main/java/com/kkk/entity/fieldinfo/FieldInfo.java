package com.kkk.entity.fieldinfo;

import com.kkk.entity.U2;
import com.kkk.entity.attributeinfo.AttributeInfo;
import com.kkk.entity.constantinfo.ConstantPool;
import com.kkk.entity.constantinfo.ConstantUTF8Info;
import lombok.Data;

import java.io.InputStream;

/**
 * 字段
 * Created by z3jjlzt on 2018/5/15.
 */
@Data
public class FieldInfo {
    private int access_flags;
    private int name_index;
    private int descriptor_index;
    private int attributes_count;
    private String name,descriptor;
    private AttributeInfo[] attributeInfos;

    public void read(InputStream inputStream, ConstantPool constantPool) {
        access_flags = U2.read(inputStream);
        name_index = U2.read(inputStream);
        descriptor_index = U2.read(inputStream);
        name = ((ConstantUTF8Info) constantPool.getConstantBaseInfos()[name_index - 1]).getValue();
        descriptor = ((ConstantUTF8Info) constantPool.getConstantBaseInfos()[descriptor_index - 1]).getValue();

        //读取字段属性信息
        attributes_count = U2.read(inputStream);
        attributeInfos = new AttributeInfo[attributes_count];
        for (int i = 0; i < attributes_count; i++) {
            AttributeInfo attributeInfo = new AttributeInfo();
            attributeInfo.read(inputStream,constantPool);
            attributeInfos[i] = attributeInfo;
        }

    }
}
