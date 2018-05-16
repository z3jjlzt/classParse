package com.kkk.entity.constantinfo;

import com.kkk.ReadInfo;
import com.kkk.entity.U2;
import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ${DESCRIPTION}
 * Created by z3jjlzt on 2018/5/14.
 */
@SuppressWarnings("ALL")
@Data
public class ConstantUTF8Info implements ReadInfo {
    private int length;
    private byte[] bytes;

    private String value;

    @Override
    public void read(InputStream ins) {
        length = U2.read(ins);
        bytes = new byte[length];
        try {
            ins.read(bytes, 0, length);
            value = readUTF8(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readUTF8(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        int current = 0;
        int length = bytes.length;
        while (current < length) {
            boolean b1 = (bytes[current] >> 7) == 0;
            if (b1) {
                sb.append(((char) bytes[current]));
                current++;
                continue;
            }
            boolean b2 = (Integer.toHexString(bytes[current]).substring(6,7).equalsIgnoreCase("d"));
            boolean b3 = (Integer.toHexString(bytes[current]).substring(6,7).equalsIgnoreCase("e"));
            if (b2) {
                sb.append(((bytes[current] & 0x1f) << 6) + (bytes[++current] & 0x3f));
                current++;
                continue;
            }
            if (b3) {
                int i = (((bytes[current] & 0xf) << 12) + ((bytes[++current] & 0x3f) << 6) + (bytes[++current] & 0x3f));
                sb.append(unicodeToString("\\u" + (Integer.toHexString(i))));
                current++;
            }
        }
        return sb.toString();
    }
    /**
     * unicode转中文
     * @param str
     * @return
     * @author yutao
     * @date 2017年1月24日上午10:33:25
     */
    private static String unicodeToString(String str) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch+"" );
        }
        return str;
    }

    @Override
    public String toString() {
        return "  =utf8  " + value;
    }
}
