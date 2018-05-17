package com.kkk.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileInputStream;

import static org.junit.Assert.assertTrue;

/**
 * Created by z3jjlzt on 2018/5/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class ClassFileTest {

    @Autowired
    private ClassFile cf;

    @Test
    public void init() throws Exception {
        FileInputStream ins = new FileInputStream("F:\\classParse\\src\\main\\App.class");
        cf.init(ins);
        assertTrue(ins.available() == 0);
        assertTrue("cafebabe".equalsIgnoreCase(cf.getMagic()));
        System.out.println("戈旭是sb");
    }
}