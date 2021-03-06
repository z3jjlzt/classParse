package com.kkk.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * jvm指令集映射关系
 * Created by z3jjlzt on 2018/5/15.
 */
@SuppressWarnings("ALL")
public class Instruction {
    public static final Map<Object,String> INSTRUCTION_SET = new HashMap();
    public static final Set<String> SET1 = new HashSet<>();
    public static final Set<String> SET2 = new HashSet<>();

    static {
        SET1.add("ldc");//index
        SET1.add("iload");//index
        SET1.add("lload");//index
        SET1.add("fload");//index
        SET1.add("dload");//index
        SET1.add("aload");//index
        SET1.add("istore");//index
        SET1.add("lstore");//index
        SET1.add("fstore");//index
        SET1.add("dstore");//index
        SET1.add("aastore");//index
        SET1.add("ret");//index
        SET1.add("bipush");//byte
    }

    static {
        SET2.add("sipush");//byte1 byte2
        SET2.add("ldc_w");//index1 index2
        SET2.add("ldc2_w");//index1 index2
        SET2.add("iinc");//index const
        SET2.add("ifeq");//index1 index2
        SET2.add("ifne");//index1 index2
        SET2.add("iflt");//index1 index2
        SET2.add("ifge");//index1 index2
        SET2.add("ifgt");//index1 index2
        SET2.add("ifle");//index1 index2
        SET2.add("if_icmpeq");//index1 index2
        SET2.add("if_icmpne");//index1 index2
        SET2.add("if_icmplt");//index1 index2
        SET2.add("if_icmpge");//index1 index2
        SET2.add("if_icmpgt");//index1 index2
        SET2.add("if_icmple");//index1 index2
        SET2.add("if_acmpeq");//index1 index2
        SET2.add("if_acmpne");//index1 index2
        SET2.add("goto");//index1 index2
        SET2.add("jsr");//index1 index2
        SET2.add("tableswitch");//变长
        SET2.add("lookupswitch");//变长
        SET2.add("getstatic");//indexi index2
        SET2.add("putstatic");//index1 index2
        SET2.add("getfield");//index1 index2
        SET2.add("putfield");//index1 index2
        SET2.add("invokevirtual");//index1 index2
        SET2.add("invokespecial");//index1 index2
        SET2.add("invokestatic");//index1 index2
        SET2.add("invokeinterface");//index1 index2 count 0
        SET2.add("invokedynamic");//index1 index2 0 0
        SET2.add("new");//index1 index2
        SET2.add("newarray");//atype(int)
        SET2.add("anewarray");//index1 index2
        SET2.add("checkcast");//index1 index2
        SET2.add("instanceof");//index1 index2
        SET2.add("wide");//如果接下来操作符为iinc i1 i2 i3 i4 否则index1 index2
        SET2.add("multianewarray");//b1 b2 dimension
        SET2.add("ifnull");//index1 index2
        SET2.add("ifnonnull");//index1 index2
        SET2.add("goto_w");//index1 index2 index3 index4
        SET2.add("jsr_w");//index1 index2 index3 index4
    }

    static {
        INSTRUCTION_SET.put(Integer.parseInt("0x00".substring(2, 4), 16), "nop");
        INSTRUCTION_SET.put(Integer.parseInt("0x01".substring(2, 4), 16), "aconst_null");
        INSTRUCTION_SET.put(Integer.parseInt("0x02".substring(2, 4), 16), "iconst_m1");
        INSTRUCTION_SET.put(Integer.parseInt("0x03".substring(2, 4), 16), "iconst_0");
        INSTRUCTION_SET.put(Integer.parseInt("0x04".substring(2, 4), 16), "iconst_1");
        INSTRUCTION_SET.put(Integer.parseInt("0x05".substring(2, 4), 16), "iconst_2");
        INSTRUCTION_SET.put(Integer.parseInt("0x06".substring(2, 4), 16), "iconst_3");
        INSTRUCTION_SET.put(Integer.parseInt("0x07".substring(2, 4), 16), "iconst_4");
        INSTRUCTION_SET.put(Integer.parseInt("0x08".substring(2, 4), 16), "iconst_5");
        INSTRUCTION_SET.put(Integer.parseInt("0x09".substring(2, 4), 16), "lconst_0");
        INSTRUCTION_SET.put(Integer.parseInt("0x0a".substring(2, 4), 16), "lconst_1");
        INSTRUCTION_SET.put(Integer.parseInt("0x0b".substring(2, 4), 16), "fconst_0");
        INSTRUCTION_SET.put(Integer.parseInt("0x0c".substring(2, 4), 16), "fconst_1");
        INSTRUCTION_SET.put(Integer.parseInt("0x0d".substring(2, 4), 16), "fconst_2");
        INSTRUCTION_SET.put(Integer.parseInt("0x0e".substring(2, 4), 16), "dconst_0");
        INSTRUCTION_SET.put(Integer.parseInt("0x0f".substring(2, 4), 16), "dconst_1");
        INSTRUCTION_SET.put(Integer.parseInt("0x10".substring(2, 4), 16), "bipush");//byte
        INSTRUCTION_SET.put(Integer.parseInt("0x11".substring(2, 4), 16), "sipush");//byte1 byte2
        INSTRUCTION_SET.put(Integer.parseInt("0x12".substring(2, 4), 16), "ldc");//index
        INSTRUCTION_SET.put(Integer.parseInt("0x13".substring(2, 4), 16), "ldc_w");//index1 index2
        INSTRUCTION_SET.put(Integer.parseInt("0x14".substring(2, 4), 16), "ldc2_w");//index1 index2
        INSTRUCTION_SET.put(Integer.parseInt("0x15".substring(2, 4), 16), "iload");//index
        INSTRUCTION_SET.put(Integer.parseInt("0x16".substring(2, 4), 16), "lload");//index
        INSTRUCTION_SET.put(Integer.parseInt("0x17".substring(2, 4), 16), "fload");//index
        INSTRUCTION_SET.put(Integer.parseInt("0x18".substring(2, 4), 16), "dload");//index
        INSTRUCTION_SET.put(Integer.parseInt("0x19".substring(2, 4), 16), "aload");//index
        INSTRUCTION_SET.put(Integer.parseInt("0x1a".substring(2, 4), 16), "iload_0");
        INSTRUCTION_SET.put(Integer.parseInt("0x1b".substring(2, 4), 16), "iload_1");
        INSTRUCTION_SET.put(Integer.parseInt("0x1c".substring(2, 4), 16), "iload_2");
        INSTRUCTION_SET.put(Integer.parseInt("0x1d".substring(2, 4), 16), "iload_3");
        INSTRUCTION_SET.put(Integer.parseInt("0x1e".substring(2, 4), 16), "lload_0");
        INSTRUCTION_SET.put(Integer.parseInt("0x1f".substring(2, 4), 16), "lload_1");
        INSTRUCTION_SET.put(Integer.parseInt("0x20".substring(2, 4), 16), "lload_2");
        INSTRUCTION_SET.put(Integer.parseInt("0x21".substring(2, 4), 16), "lload_3");
        INSTRUCTION_SET.put(Integer.parseInt("0x22".substring(2, 4), 16), "fload_0");
        INSTRUCTION_SET.put(Integer.parseInt("0x23".substring(2, 4), 16), "fload_1");
        INSTRUCTION_SET.put(Integer.parseInt("0x24".substring(2, 4), 16), "fload_2");
        INSTRUCTION_SET.put(Integer.parseInt("0x25".substring(2, 4), 16), "fload_3");
        INSTRUCTION_SET.put(Integer.parseInt("0x26".substring(2, 4), 16), "dload_0");
        INSTRUCTION_SET.put(Integer.parseInt("0x27".substring(2, 4), 16), "dload_1");
        INSTRUCTION_SET.put(Integer.parseInt("0x28".substring(2, 4), 16), "dload_2");
        INSTRUCTION_SET.put(Integer.parseInt("0x29".substring(2, 4), 16), "dload_3");
        INSTRUCTION_SET.put(Integer.parseInt("0x2a".substring(2, 4), 16), "aload_0");
        INSTRUCTION_SET.put(Integer.parseInt("0x2b".substring(2, 4), 16), "aload_1");
        INSTRUCTION_SET.put(Integer.parseInt("0x2c".substring(2, 4), 16), "aload_2");
        INSTRUCTION_SET.put(Integer.parseInt("0x2d".substring(2, 4), 16), "aload_3");
        INSTRUCTION_SET.put(Integer.parseInt("0x2e".substring(2, 4), 16), "iaload");
        INSTRUCTION_SET.put(Integer.parseInt("0x2f".substring(2, 4), 16), "laload");
        INSTRUCTION_SET.put(Integer.parseInt("0x30".substring(2, 4), 16), "faload");
        INSTRUCTION_SET.put(Integer.parseInt("0x31".substring(2, 4), 16), "daload");
        INSTRUCTION_SET.put(Integer.parseInt("0x32".substring(2, 4), 16), "aaload");
        INSTRUCTION_SET.put(Integer.parseInt("0x33".substring(2, 4), 16), "baload");
        INSTRUCTION_SET.put(Integer.parseInt("0x34".substring(2, 4), 16), "caload");
        INSTRUCTION_SET.put(Integer.parseInt("0x35".substring(2, 4), 16), "saload");
        INSTRUCTION_SET.put(Integer.parseInt("0x36".substring(2, 4), 16), "istore");//index
        INSTRUCTION_SET.put(Integer.parseInt("0x37".substring(2, 4), 16), "lstore");//index
        INSTRUCTION_SET.put(Integer.parseInt("0x38".substring(2, 4), 16), "fstore");//index
        INSTRUCTION_SET.put(Integer.parseInt("0x39".substring(2, 4), 16), "dstore");//index
        INSTRUCTION_SET.put(Integer.parseInt("0x3a".substring(2, 4), 16), "astore");
        INSTRUCTION_SET.put(Integer.parseInt("0x3b".substring(2, 4), 16), "istore_0");
        INSTRUCTION_SET.put(Integer.parseInt("0x3c".substring(2, 4), 16), "istore_1");
        INSTRUCTION_SET.put(Integer.parseInt("0x3d".substring(2, 4), 16), "istore_2");
        INSTRUCTION_SET.put(Integer.parseInt("0x3e".substring(2, 4), 16), "istore_3");
        INSTRUCTION_SET.put(Integer.parseInt("0x3f".substring(2, 4), 16), "lstore_0");
        INSTRUCTION_SET.put(Integer.parseInt("0x40".substring(2, 4), 16), "lstore_1");
        INSTRUCTION_SET.put(Integer.parseInt("0x41".substring(2, 4), 16), "lstore_2");
        INSTRUCTION_SET.put(Integer.parseInt("0x42".substring(2, 4), 16), "lstore_3");
        INSTRUCTION_SET.put(Integer.parseInt("0x43".substring(2, 4), 16), "fstore_0");
        INSTRUCTION_SET.put(Integer.parseInt("0x44".substring(2, 4), 16), "fstore_1");
        INSTRUCTION_SET.put(Integer.parseInt("0x45".substring(2, 4), 16), "fstore_2");
        INSTRUCTION_SET.put(Integer.parseInt("0x46".substring(2, 4), 16), "fstore_3");
        INSTRUCTION_SET.put(Integer.parseInt("0x47".substring(2, 4), 16), "dstore_0");
        INSTRUCTION_SET.put(Integer.parseInt("0x48".substring(2, 4), 16), "dstore_1");
        INSTRUCTION_SET.put(Integer.parseInt("0x49".substring(2, 4), 16), "dstore_2");
        INSTRUCTION_SET.put(Integer.parseInt("0x4a".substring(2, 4), 16), "dstore_3");
        INSTRUCTION_SET.put(Integer.parseInt("0x4b".substring(2, 4), 16), "astore_0");
        INSTRUCTION_SET.put(Integer.parseInt("0x4c".substring(2, 4), 16), "astore_1");
        INSTRUCTION_SET.put(Integer.parseInt("0x4d".substring(2, 4), 16), "astore_2");
        INSTRUCTION_SET.put(Integer.parseInt("0x4e".substring(2, 4), 16), "astore_3");
        INSTRUCTION_SET.put(Integer.parseInt("0x4f".substring(2, 4), 16), "iastore");
        INSTRUCTION_SET.put(Integer.parseInt("0x50".substring(2, 4), 16), "lastore");
        INSTRUCTION_SET.put(Integer.parseInt("0x51".substring(2, 4), 16), "fastore");
        INSTRUCTION_SET.put(Integer.parseInt("0x52".substring(2, 4), 16), "dastore");
        INSTRUCTION_SET.put(Integer.parseInt("0x53".substring(2, 4), 16), "aastore");//index
        INSTRUCTION_SET.put(Integer.parseInt("0x54".substring(2, 4), 16), "bastore");
        INSTRUCTION_SET.put(Integer.parseInt("0x55".substring(2, 4), 16), "castore");
        INSTRUCTION_SET.put(Integer.parseInt("0x56".substring(2, 4), 16), "sastore");
        INSTRUCTION_SET.put(Integer.parseInt("0x57".substring(2, 4), 16), "pop");
        INSTRUCTION_SET.put(Integer.parseInt("0x58".substring(2, 4), 16), "pop2");
        INSTRUCTION_SET.put(Integer.parseInt("0x59".substring(2, 4), 16), "dup");
        INSTRUCTION_SET.put(Integer.parseInt("0x5a".substring(2, 4), 16), "dup_x1");
        INSTRUCTION_SET.put(Integer.parseInt("0x5b".substring(2, 4), 16), "dup_x2");
        INSTRUCTION_SET.put(Integer.parseInt("0x5c".substring(2, 4), 16), "dup2");
        INSTRUCTION_SET.put(Integer.parseInt("0x5d".substring(2, 4), 16), "dup2_x1");
        INSTRUCTION_SET.put(Integer.parseInt("0x5e".substring(2, 4), 16), "dup2_x2");
        INSTRUCTION_SET.put(Integer.parseInt("0x5f".substring(2, 4), 16), "swap");
        INSTRUCTION_SET.put(Integer.parseInt("0x60".substring(2, 4), 16), "iadd");
        INSTRUCTION_SET.put(Integer.parseInt("0x61".substring(2, 4), 16), "ladd");
        INSTRUCTION_SET.put(Integer.parseInt("0x62".substring(2, 4), 16), "fadd");
        INSTRUCTION_SET.put(Integer.parseInt("0x63".substring(2, 4), 16), "dadd");
        INSTRUCTION_SET.put(Integer.parseInt("0x64".substring(2, 4), 16), "isub");
        INSTRUCTION_SET.put(Integer.parseInt("0x65".substring(2, 4), 16), "lsub");
        INSTRUCTION_SET.put(Integer.parseInt("0x66".substring(2, 4), 16), "fsub");
        INSTRUCTION_SET.put(Integer.parseInt("0x67".substring(2, 4), 16), "dsub");
        INSTRUCTION_SET.put(Integer.parseInt("0x68".substring(2, 4), 16), "imul");
        INSTRUCTION_SET.put(Integer.parseInt("0x69".substring(2, 4), 16), "lmul");
        INSTRUCTION_SET.put(Integer.parseInt("0x6a".substring(2, 4), 16), "fmul");
        INSTRUCTION_SET.put(Integer.parseInt("0x6b".substring(2, 4), 16), "dmul");
        INSTRUCTION_SET.put(Integer.parseInt("0x6c".substring(2, 4), 16), "idiv");
        INSTRUCTION_SET.put(Integer.parseInt("0x6d".substring(2, 4), 16), "ldiv");
        INSTRUCTION_SET.put(Integer.parseInt("0x6e".substring(2, 4), 16), "fdiv");
        INSTRUCTION_SET.put(Integer.parseInt("0x6f".substring(2, 4), 16), "ddiv");
        INSTRUCTION_SET.put(Integer.parseInt("0x70".substring(2, 4), 16), "irem");
        INSTRUCTION_SET.put(Integer.parseInt("0x71".substring(2, 4), 16), "lrem");
        INSTRUCTION_SET.put(Integer.parseInt("0x72".substring(2, 4), 16), "frem");
        INSTRUCTION_SET.put(Integer.parseInt("0x73".substring(2, 4), 16), "drem");
        INSTRUCTION_SET.put(Integer.parseInt("0x74".substring(2, 4), 16), "ineg");
        INSTRUCTION_SET.put(Integer.parseInt("0x75".substring(2, 4), 16), "lneg");
        INSTRUCTION_SET.put(Integer.parseInt("0x76".substring(2, 4), 16), "fneg");
        INSTRUCTION_SET.put(Integer.parseInt("0x77".substring(2, 4), 16), "dneg");
        INSTRUCTION_SET.put(Integer.parseInt("0x78".substring(2, 4), 16), "ishl");
        INSTRUCTION_SET.put(Integer.parseInt("0x79".substring(2, 4), 16), "lshl");
        INSTRUCTION_SET.put(Integer.parseInt("0x7a".substring(2, 4), 16), "ishr");
        INSTRUCTION_SET.put(Integer.parseInt("0x7b".substring(2, 4), 16), "lshr");
        INSTRUCTION_SET.put(Integer.parseInt("0x7c".substring(2, 4), 16), "iushr");
        INSTRUCTION_SET.put(Integer.parseInt("0x7d".substring(2, 4), 16), "lushr");
        INSTRUCTION_SET.put(Integer.parseInt("0x7e".substring(2, 4), 16), "iand");
        INSTRUCTION_SET.put(Integer.parseInt("0x7f".substring(2, 4), 16), "land");
        INSTRUCTION_SET.put(Integer.parseInt("0x80".substring(2, 4), 16), "ior");
        INSTRUCTION_SET.put(Integer.parseInt("0x81".substring(2, 4), 16), "lor");
        INSTRUCTION_SET.put(Integer.parseInt("0x82".substring(2, 4), 16), "ixor");
        INSTRUCTION_SET.put(Integer.parseInt("0x83".substring(2, 4), 16), "lxor");
        INSTRUCTION_SET.put(Integer.parseInt("0x84".substring(2, 4), 16), "iinc");//index const
        INSTRUCTION_SET.put(Integer.parseInt("0x85".substring(2, 4), 16), "i2l");
        INSTRUCTION_SET.put(Integer.parseInt("0x86".substring(2, 4), 16), "i2f");
        INSTRUCTION_SET.put(Integer.parseInt("0x87".substring(2, 4), 16), "i2d");
        INSTRUCTION_SET.put(Integer.parseInt("0x88".substring(2, 4), 16), "l2i");
        INSTRUCTION_SET.put(Integer.parseInt("0x89".substring(2, 4), 16), "l2f");
        INSTRUCTION_SET.put(Integer.parseInt("0x8a".substring(2, 4), 16), "l2d");
        INSTRUCTION_SET.put(Integer.parseInt("0x8b".substring(2, 4), 16), "f2i");
        INSTRUCTION_SET.put(Integer.parseInt("0x8c".substring(2, 4), 16), "f2l");
        INSTRUCTION_SET.put(Integer.parseInt("0x8d".substring(2, 4), 16), "f2d");
        INSTRUCTION_SET.put(Integer.parseInt("0x8e".substring(2, 4), 16), "d2i");
        INSTRUCTION_SET.put(Integer.parseInt("0x8f".substring(2, 4), 16), "d2l");
        INSTRUCTION_SET.put(Integer.parseInt("0x90".substring(2, 4), 16), "d2f");
        INSTRUCTION_SET.put(Integer.parseInt("0x91".substring(2, 4), 16), "i2b");
        INSTRUCTION_SET.put(Integer.parseInt("0x92".substring(2, 4), 16), "i2c");
        INSTRUCTION_SET.put(Integer.parseInt("0x93".substring(2, 4), 16), "i2s");
        INSTRUCTION_SET.put(Integer.parseInt("0x94".substring(2, 4), 16), "lcmp");
        INSTRUCTION_SET.put(Integer.parseInt("0x95".substring(2, 4), 16), "fcmpl");
        INSTRUCTION_SET.put(Integer.parseInt("0x96".substring(2, 4), 16), "fcmpg");
        INSTRUCTION_SET.put(Integer.parseInt("0x97".substring(2, 4), 16), "dcmpl");
        INSTRUCTION_SET.put(Integer.parseInt("0x98".substring(2, 4), 16), "dcmpg");
        INSTRUCTION_SET.put(Integer.parseInt("0x99".substring(2, 4), 16), "ifeq");//index1 index2
        INSTRUCTION_SET.put(Integer.parseInt("0x9a".substring(2, 4), 16), "ifne");//index1 index2
        INSTRUCTION_SET.put(Integer.parseInt("0x9b".substring(2, 4), 16), "iflt");//index1 index2
        INSTRUCTION_SET.put(Integer.parseInt("0x9c".substring(2, 4), 16), "ifge");//index1 index2
        INSTRUCTION_SET.put(Integer.parseInt("0x9d".substring(2, 4), 16), "ifgt");//index1 index2
        INSTRUCTION_SET.put(Integer.parseInt("0x9e".substring(2, 4), 16), "ifle");//index1 index2
        INSTRUCTION_SET.put(Integer.parseInt("0x9f".substring(2, 4), 16), "if_icmpeq");//index1 index2
        INSTRUCTION_SET.put(Integer.parseInt("0xa0".substring(2, 4), 16), "if_icmpne");//index1 index2
        INSTRUCTION_SET.put(Integer.parseInt("0xa1".substring(2, 4), 16), "if_icmplt");//index1 index2
        INSTRUCTION_SET.put(Integer.parseInt("0xa2".substring(2, 4), 16), "if_icmpge");//index1 index2
        INSTRUCTION_SET.put(Integer.parseInt("0xa3".substring(2, 4), 16), "if_icmpgt");//index1 index2
        INSTRUCTION_SET.put(Integer.parseInt("0xa4".substring(2, 4), 16), "if_icmple");//index1 index2
        INSTRUCTION_SET.put(Integer.parseInt("0xa5".substring(2, 4), 16), "if_acmpeq");//index1 index2
        INSTRUCTION_SET.put(Integer.parseInt("0xa6".substring(2, 4), 16), "if_acmpne");//index1 index2
        INSTRUCTION_SET.put(Integer.parseInt("0xa7".substring(2, 4), 16), "goto");//index1 index2
        INSTRUCTION_SET.put(Integer.parseInt("0xa8".substring(2, 4), 16), "jsr");//index1 index2
        INSTRUCTION_SET.put(Integer.parseInt("0xa9".substring(2, 4), 16), "ret");//index
        INSTRUCTION_SET.put(Integer.parseInt("0xaa".substring(2, 4), 16), "tableswitch");//变长
        INSTRUCTION_SET.put(Integer.parseInt("0xab".substring(2, 4), 16), "lookupswitch");//变长
        INSTRUCTION_SET.put(Integer.parseInt("0xac".substring(2, 4), 16), "ireturn");
        INSTRUCTION_SET.put(Integer.parseInt("0xad".substring(2, 4), 16), "lreturn");
        INSTRUCTION_SET.put(Integer.parseInt("0xae".substring(2, 4), 16), "freturn");
        INSTRUCTION_SET.put(Integer.parseInt("0xaf".substring(2, 4), 16), "dreturn");
        INSTRUCTION_SET.put(Integer.parseInt("0xb0".substring(2, 4), 16), "areturn");
        INSTRUCTION_SET.put(Integer.parseInt("0xb1".substring(2, 4), 16), "return");
        INSTRUCTION_SET.put(Integer.parseInt("0xb2".substring(2, 4), 16), "getstatic");//indexi index2
        INSTRUCTION_SET.put(Integer.parseInt("0xb3".substring(2, 4), 16), "putstatic");//index1 index2
        INSTRUCTION_SET.put(Integer.parseInt("0xb4".substring(2, 4), 16), "getfield");//index1 index2
        INSTRUCTION_SET.put(Integer.parseInt("0xb5".substring(2, 4), 16), "putfield");//index1 index2
        INSTRUCTION_SET.put(Integer.parseInt("0xb6".substring(2, 4), 16), "invokevirtual");//index1 index2
        INSTRUCTION_SET.put(Integer.parseInt("0xb7".substring(2, 4), 16), "invokespecial");//index1 index2
        INSTRUCTION_SET.put(Integer.parseInt("0xb8".substring(2, 4), 16), "invokestatic");//index1 index2
        INSTRUCTION_SET.put(Integer.parseInt("0xb9".substring(2, 4), 16), "invokeinterface");//index1 index2 count 0
        INSTRUCTION_SET.put(Integer.parseInt("0xba".substring(2, 4), 16), "invokedynamic");//index1 index2 0 0
        INSTRUCTION_SET.put(Integer.parseInt("0xbb".substring(2, 4), 16), "new");//index1 index2
        INSTRUCTION_SET.put(Integer.parseInt("0xbc".substring(2, 4), 16), "newarray");//atype(int)
        INSTRUCTION_SET.put(Integer.parseInt("0xbd".substring(2, 4), 16), "anewarray");//index1 index2
        INSTRUCTION_SET.put(Integer.parseInt("0xbe".substring(2, 4), 16), "arraylength");
        INSTRUCTION_SET.put(Integer.parseInt("0xbf".substring(2, 4), 16), "athrow");
        INSTRUCTION_SET.put(Integer.parseInt("0xc0".substring(2, 4), 16), "checkcast");//index1 index2
        INSTRUCTION_SET.put(Integer.parseInt("0xc1".substring(2, 4), 16), "instanceof");//index1 index2
        INSTRUCTION_SET.put(Integer.parseInt("0xc2".substring(2, 4), 16), "monitorenter");
        INSTRUCTION_SET.put(Integer.parseInt("0xc3".substring(2, 4), 16), "monitorexit");
        INSTRUCTION_SET.put(Integer.parseInt("0xc4".substring(2, 4), 16), "wide");//如果接下来操作符为iinc i1 i2 i3 i4 否则index1 index2
        INSTRUCTION_SET.put(Integer.parseInt("0xc5".substring(2, 4), 16), "multianewarray");//b1 b2 dimension
        INSTRUCTION_SET.put(Integer.parseInt("0xc6".substring(2, 4), 16), "ifnull");//index1 index2
        INSTRUCTION_SET.put(Integer.parseInt("0xc7".substring(2, 4), 16), "ifnonnull");//index1 index2
        INSTRUCTION_SET.put(Integer.parseInt("0xc8".substring(2, 4), 16), "goto_w");//index1 index2 index3 index4
        INSTRUCTION_SET.put(Integer.parseInt("0xc9".substring(2, 4), 16), "jsr_w");//index1 index2 index3 index4
    }
}
