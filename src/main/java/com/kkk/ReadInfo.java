package com.kkk;

import java.io.InputStream;

/**
 * classfile中索引_info的表读取byte字节的统一接口.
 * Created by z3jjlzt on 2018/5/15.
 */
public interface ReadInfo {
    public void read(InputStream inputStream);
}
