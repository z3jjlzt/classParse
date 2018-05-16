package com.kkk.entity.attributeinfo;

import com.kkk.entity.U2;
import lombok.Data;

import java.io.InputStream;

/**
 * ${DESCRIPTION}
 * Created by z3jjlzt on 2018/5/15.
 */
@Data
class ExceptionTableInfo {
    private int start_pc,end_pc,handler_pc,catch_type;

    public void read(InputStream inputStream) {
        start_pc = U2.read(inputStream);
        end_pc = U2.read(inputStream);
        handler_pc = U2.read(inputStream);
        catch_type = U2.read(inputStream);
    }
}
