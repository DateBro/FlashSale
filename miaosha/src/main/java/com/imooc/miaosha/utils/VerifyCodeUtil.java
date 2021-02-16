package com.imooc.miaosha.utils;

import com.imooc.miaosha.enums.VerifyCodeEnum;

import java.util.Random;

/**
 * @Author DateBro
 * @Date 2021/2/16 18:03
 */
public class VerifyCodeUtil {

    static Random random = new Random();

    static char[] blendCodes = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };
    static char[] numCodes = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static String generateCode(int length, VerifyCodeEnum codeEnum) {
        char[] codeChars = new char[length];

        for (int i = 0; i < length; ++i) {
            if(codeEnum.getCode().equals(VerifyCodeEnum.NUM_CODE.getCode())) {
                codeChars[i] = numCodes[random.nextInt(numCodes.length)];
            } else if (codeEnum.getCode().equals(VerifyCodeEnum.BLEND_CODE.getCode())) {
                codeChars[i] = blendCodes[random.nextInt(blendCodes.length)];
            } else {
                // 默认使用纯数字的验证码
                codeChars[i] = numCodes[(random.nextInt(numCodes.length))];
            }
        }

        return new String(codeChars);
    }
}
