package com.kkk.entity.fieldinfo;

import com.kkk.ReadInfo;
import com.kkk.entity.U2;
import com.kkk.entity.attributeinfo.AttributeInfo;
import lombok.Data;

import java.io.InputStream;

/**
 * 字段
 * Created by z3jjlzt on 2018/5/15.
 */
@Data
public class FieldInfo implements ReadInfo {
    private int access_flags;
    private int name_index;
    private int descriptor_index;
    private int attributes_count;
    private String name, descriptor;
    private AttributeInfo[] attributeInfos;

    @Override
    public void read(InputStream inputStream) {
        access_flags = U2.read(inputStream);
        name_index = U2.read(inputStream);
        descriptor_index = U2.read(inputStream);

        //读取字段属性信息
        attributes_count = U2.read(inputStream);
        attributeInfos = new AttributeInfo[attributes_count];
        for (int i = 0; i < attributes_count; i++) {
            AttributeInfo attributeInfo = new AttributeInfo();
            attributeInfo.read(inputStream);
            attributeInfos[i] = attributeInfo;
        }
    }
}
