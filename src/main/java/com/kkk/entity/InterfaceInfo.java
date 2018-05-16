package com.kkk.entity;

import com.kkk.ReadInfo;
import lombok.Data;

import java.io.InputStream;

/**
 * 接口类型
 */
@Data
public class InterfaceInfo implements ReadInfo {
    private int name_index;
    private String name;

    @Override
    public void read(InputStream inputStream) {
        name_index = U2.read(inputStream);
    }

    @Override
    public String toString() {
        return "  =interface    #" + name_index + "    // " + name;
    }
}
