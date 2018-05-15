package com.kkk.entity.attributeinfo;

import com.kkk.entity.U2;
import com.kkk.entity.U4;
import com.kkk.entity.constantinfo.ConstantPool;
import com.kkk.entity.constantinfo.ConstantUTF8Info;
import lombok.Data;

import java.io.IOException;
import java.io.InputStream;

/**
 * ${DESCRIPTION}
 * Created by z3jjlzt on 2018/5/15.
 */
@Data
public class AttributeInfo {
    protected int attr_name_index;
    protected long attrs_length;
    protected String attr_name;

    /**
     * attribute_length 项的值给出了跟随其后的字节的长度，这个长度不包括
     attribute_name_index 和 attribute_name_index 项的 6 个字节
     */
    private byte[] bytes;

    public void read(InputStream inputStream, ConstantPool constantPool) {
        attr_name_index = U2.read(inputStream);
        attr_name = ((ConstantUTF8Info) constantPool.getConstantBaseInfos()[attr_name_index - 1]).getValue();
        attrs_length = U4.read(inputStream);
        bytes = new byte[((int) attrs_length)];
        try {
            inputStream.read(bytes, 0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
