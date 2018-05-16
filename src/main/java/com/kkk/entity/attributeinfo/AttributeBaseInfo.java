package com.kkk.entity.attributeinfo;

import com.kkk.ReadInfo;
import com.kkk.entity.U2;
import com.kkk.entity.U4;
import lombok.Data;

import java.io.IOException;
import java.io.InputStream;

/**
 * 属性表公共父类
 * Created by z3jjlzt on 2018/5/16.
 */
@SuppressWarnings("ALL")
@Data
public class AttributeBaseInfo implements ReadInfo {
    protected int attr_name_index;
    protected long attrs_length;
    protected String attr_name;

    /**
     * attribute_length 项的值给出了跟随其后的字节的长度，这个长度不包括
     * attribute_name_index 和 attribute_name_index 项的 6 个字节
     */
    private byte[] bytes;

    @Override
    public void read(InputStream inputStream) {
        attr_name_index = U2.read(inputStream);
        attrs_length = U4.read(inputStream);
        bytes = new byte[((int) attrs_length)];
        try {
            inputStream.read(bytes, 0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
