package com.imooc.miaosha.controller;

import com.alibaba.druid.util.StringUtils;
import com.imooc.miaosha.constant.CookieConstant;
import com.imooc.miaosha.constant.RedisConstant;
import com.imooc.miaosha.converter.BuyerForm2BuyerDTOConverter;
import com.imooc.miaosha.dataobject.BuyerInfo;
import com.imooc.miaosha.dto.BuyerDTO;
import com.imooc.miaosha.enums.ResultEnum;
import com.imooc.miaosha.enums.VerifyCodeEnum;
import com.imooc.miaosha.exception.MiaoshaException;
import com.imooc.miaosha.form.BuyerForm;
import com.imooc.miaosha.service.Impl.BuyerServiceImpl;
import com.imooc.miaosha.utils.CookieUtil;
import com.imooc.miaosha.utils.PasswordUtil;
import com.imooc.miaosha.utils.ResultVOUtil;
import com.imooc.miaosha.utils.OtpCodeUtil;
import com.imooc.miaosha.viewobject.BuyerVO;
import com.imooc.miaosha.viewobject.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author DateBro
 * @Date 2021/2/16 17:44
 */
@RestController
@RequestMapping("/buyer")
@Slf4j
public class BuyerController {

    @Autowired
    private BuyerServiceImpl buyerService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping(value = "/register")
    public ResultVO register(@Valid BuyerForm buyerForm, BindingResult bindingResult) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        // 先判断表单是否有错误
        if (bindingResult.hasErrors()) {
            log.error("【买家注册】表单错误");
            throw new MiaoshaException(ResultEnum.PARAMETER_VALIDATION_ERROR);
        }

        // 验证手机号与短信验证码是否对应
        String inSessionOtpCode = (String) httpServletRequest.getSession().getAttribute(buyerForm.getTelephone());
        if (!StringUtils.equals(inSessionOtpCode, buyerForm.getOtpCode())) {
            log.error("【买家注册】短信验证码有误");
            throw new MiaoshaException(ResultEnum.PARAMETER_VALIDATION_ERROR);
        }

        // 执行注册操作
        BuyerDTO buyerDTO = BuyerForm2BuyerDTOConverter.convert(buyerForm);
        BuyerInfo buyerInfo = buyerService.register(buyerDTO);

        // 返回前端viewobject
        BuyerVO buyerVO = new BuyerVO();
        buyerVO.setBuyerId(buyerInfo.getBuyerId());
        buyerVO.setUsername(buyerInfo.getUsername());
        buyerVO.setTelephone(buyerInfo.getTelephone());
        buyerVO.setAge(buyerInfo.getAge());
        buyerVO.setGender(buyerInfo.getGender());

        return ResultVOUtil.success(buyerVO);
    }

    @PostMapping(value = "/login")
    public ResultVO login(@RequestParam(value = "telephone", required = true) String telephone,
                          @RequestParam(value = "password", required = true) String password,
                          HttpServletResponse response) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        // 入参检验
        if (StringUtils.isEmpty(telephone) || StringUtils.isEmpty(password)) {
            throw new MiaoshaException(ResultEnum.PARAMETER_VALIDATION_ERROR);
        }

        // 通过用户手机号和密码检查登录是否合法
        BuyerDTO buyerDTO = buyerService.validateLogin(telephone, PasswordUtil.EncodeByMd5(password));

        // 设置token到redis
        String token = UUID.randomUUID().toString();
        token = token.replace("-", "");
        Integer expire = RedisConstant.EXPIRE;
        redisTemplate.opsForValue().set(token, buyerDTO, expire, TimeUnit.SECONDS);

        // 设置token到cookie
        CookieUtil.set(response, CookieConstant.TOKEN, token, expire);

        return ResultVOUtil.success();
    }

    @PostMapping(value = "/getotp")
    public ResultVO getOtp(@RequestParam(value = "telephone", required = true) String telephone) {
        //需要按照一定的规则生成OTP验证码
        String otp = OtpCodeUtil.generateCode(4, VerifyCodeEnum.NUM_CODE);

        //将OTP验证码同对应用户的手机号关联，使用httpsession的方式绑定他的手机号与OTPCODE
        httpServletRequest.getSession().setAttribute(telephone, otp);

        //将OTP验证码通过短信通道发送给用户,省略
        log.info("手机号为 " + telephone + " 的用户生成的短信验证码为：" + otp);

        return ResultVOUtil.success();
    }
}
