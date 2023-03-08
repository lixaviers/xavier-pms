package com.xavier.pms.controller;

import cn.hutool.core.collection.CollUtil;
import com.wf.captcha.ArithmeticCaptcha;
import com.xavier.pms.constants.Constant;
import com.xavier.pms.dto.LoginDto;
import com.xavier.pms.result.Result;
import com.xavier.pms.service.IUserService;
import com.xavier.pms.service.IUserTokenService;
import com.xavier.pms.vo.UserInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * 请求处理层-登录管理类
 *
 * @author Xavier
 * @version 1.0
 */
@Slf4j
@RequestMapping("")
@RestController
@Api(tags = "登录管理接口")
public class LoginController extends CommonController {

    @Resource
    private IUserService userService;
    @Resource
    private IUserTokenService userTokenService;

    @ApiOperation(value = "获取图形验证码", notes = "获取图形验证码")
    @PostMapping("getCaptchaImage")
    public Result<Map<String, String>> getCaptchaImage() {
        Map<String, String> data = new HashMap<>();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(120, 40);
        // 获取运算的公式
        captcha.getArithmeticString();
        // 获取运算的结果
        String text = captcha.text();
        data.put("uuid", uuid);
        data.put("codeUrl", captcha.toBase64());

        getSession().setAttribute(Constant.CAPTCHA_KEY + uuid, text);

        return Result.ok(data);
    }

    @ApiOperation(value = "用户登录", notes = "用户登录")
    @PostMapping("login")
    public Result<String> login(@Validated @RequestBody LoginDto dto) {
        // 验证验证码
        Object object = getSession().getAttribute(Constant.CAPTCHA_KEY + dto.getUuid());
        if (Objects.isNull(object)) {
            return Result.error("验证码失效");
        }
        if (!Objects.equals(object.toString(), dto.getVerCode())) {
            return Result.error("验证码错误");
        }
        return Result.ok(userService.login(dto));
    }

    @ApiOperation(value = "用户退出", notes = "用户退出")
    @PostMapping("logout")
    public Result logout() {
        UserInfoVo loginUser = getLoginUser();
        if (Objects.nonNull(loginUser)) {
            userTokenService.deleteByToken(loginUser.getToken());
        }
        return Result.ok();
    }

    @ApiOperation(value = "获取登录用户信息", notes = "获取登录用户信息")
    @GetMapping("getInfo")
    public Result<Map<String, Object>> getInfo() {
        Map<String, Object> map = new HashMap<>();
        UserInfoVo user = super.getLoginUser();
        map.put("user", user);
        if (user.getIsAdmin()) {
            map.put("permissions", CollUtil.newArrayList("*:*:*"));
        }
        // todo 非管理员权限
        return Result.ok(map);
    }


}
