package com.imooc.miaosha.controller;

import com.imooc.miaosha.enums.VerifyCodeEnum;
import com.imooc.miaosha.utils.ResultVOUtil;
import com.imooc.miaosha.utils.VerifyCodeUtil;
import com.imooc.miaosha.viewobject.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author DateBro
 * @Date 2021/2/16 17:44
 */
@RestController
@RequestMapping("/buyer")
@Slf4j
@CrossOrigin
public class BuyerController {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @PostMapping(value = "/getotp")
    public ResultVO getOtp(@RequestParam(value = "telephone", required = true) String telephone) {
        //需要按照一定的规则生成OTP验证码
        String otp = VerifyCodeUtil.generateCode(4, VerifyCodeEnum.NUM_CODE);

        //将OTP验证码同对应用户的手机号关联，使用httpsession的方式绑定他的手机号与OTPCODE
        httpServletRequest.getSession().setAttribute(telephone, otp);

        //将OTP验证码通过短信通道发送给用户,省略
        log.info("手机号为 " + telephone + " 的用户生成的短信验证码为：" + otp);

        return ResultVOUtil.success();
    }
}
