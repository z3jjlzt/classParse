package com.kkk.entity.constantinfo;

import com.kkk.ReadInfo;
import com.kkk.entity.U2;
import lombok.Data;

import java.io.InputStream;

@Data
public class ConstantInterfaceInfo implements ReadInfo {
    private int name_index;
    private String name;

    @Override
    public void read(InputStream inputStream) {
        name_index = U2.read(inputStream);
    }
}
