package com.kkk.entity.methodinfo;

import com.kkk.ReadInfo;
import com.kkk.entity.U2;
import com.kkk.entity.attributeinfo.AttributeBaseInfo;
import lombok.Data;

import java.io.InputStream;

/**
 * classFile中的method表
 * Created by z3jjlzt on 2018/5/15.
 */
@Data
public class MethodInfo implements ReadInfo {
    private int access_flags;
    private int name_index;
    private int descriptor_index;
    private int attributes_count;
    private String name, descriptor;
    private AttributeBaseInfo[] attributeInfos;

    @Override
    public void read(InputStream inputStream) {
        access_flags = U2.read(inputStream);
        name_index = U2.read(inputStream);
        descriptor_index = U2.read(inputStream);

        //读取方法属性信息
        attributes_count = U2.read(inputStream);
        attributeInfos = new AttributeBaseInfo[attributes_count];
        for (int i = 0; i < attributes_count; i++) {
            AttributeBaseInfo attributeInfo = new AttributeBaseInfo();
            attributeInfo.read(inputStream);
            attributeInfos[i] = attributeInfo;
        }
    }
}
