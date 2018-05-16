package com.kkk.entity.fieldinfo;

import com.kkk.ReadInfo;
import com.kkk.entity.U2;
import com.kkk.entity.attributeinfo.AttributeBaseInfo;
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
    private AttributeBaseInfo[] attributeInfos;

    @Override
    public void read(InputStream inputStream) {
        setAccess_flags(U2.read(inputStream));
        setName_index(U2.read(inputStream));
        setDescriptor_index(U2.read(inputStream));

        //读取字段属性信息
        setAttributes_count(U2.read(inputStream));
        attributeInfos = new AttributeBaseInfo[attributes_count];
        for (int i = 0; i < attributes_count; i++) {
            AttributeBaseInfo attributeInfo = new AttributeBaseInfo();
            attributeInfo.read(inputStream);
            attributeInfos[i] = attributeInfo;
        }
    }

}
