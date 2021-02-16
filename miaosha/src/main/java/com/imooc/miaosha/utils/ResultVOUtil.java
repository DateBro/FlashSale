package com.imooc.miaosha.utils;

import com.imooc.miaosha.viewobject.ResultVO;

/**
 * @Author DateBro
 * @Date 2021/2/16 17:21
 */
public class ResultVOUtil {

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO success(Object data) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(data);
        return resultVO;
    }

    public static ResultVO error(Integer code, String message) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(message);
        return resultVO;
    }
}
