package com.imooc.miaosha.utils;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author DateBro
 * @Date 2021/2/17 12:46
 */
public class PasswordUtil {
    public static String EncodeByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();

        //加密字符串
        String newstr = base64en.encode(md5.digest(str.getBytes(StandardCharsets.UTF_8)));
        return newstr;
    }
}
