package com.sanxiang.bank.controller.api;


import com.sanxiang.bank.common.enums.ResultCode;
import com.sanxiang.bank.common.exception.ApiException;
import com.sanxiang.bank.common.security.UserEmailToken;
import com.sanxiang.bank.model.entity.UserEntity;
import com.sanxiang.bank.model.param.CodeLoginParam;
import com.sanxiang.bank.model.param.LoginParam;
import com.sanxiang.bank.model.param.RegisterParam;
import com.sanxiang.bank.model.vo.ResultVO;
import com.sanxiang.bank.model.vo.UserVO;
import com.sanxiang.bank.service.UserService;
import com.sanxiang.bank.utils.JwtUtils;
import com.sanxiang.bank.utils.QiniuUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


@Slf4j
@RestController
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/register/verifyCode")
    public ResultVO<String> sendEmail(@RequestParam String email) {
        userService.verifyEmail(email);
        log.info("验证码已发送至[{}]", email);
        return new ResultVO<>("验证码发送成功");
    }

    @PostMapping("/register")
    public ResultVO<Long> register(@RequestBody @Validated RegisterParam param) {
        log.info("用户注册,邮箱:[{}]", param.getUsername());
        return new ResultVO<>(userService.register(param));
    }

//    @GetMapping("/user/{id}")
//    public ResultVO<UserEntity> getUser(@PathVariable Long id) {
//        return new ResultVO<>(userService.getUserById(id));
//    }

    @PostMapping("/login")
    public UserEntity login(@RequestBody @Validated LoginParam param, HttpServletResponse response) {
        String username = param.getUsername();
        String password = param.getPassword();
        Subject subject = SecurityUtils.getSubject();
        UserEntity user = null;
        // 登录
        subject.login(new UsernamePasswordToken(username, password));
        log.info("用户登录,邮箱:[{}]", param.getUsername());
        user = (UserEntity) subject.getPrincipal();
        // 签发Jwt,存入Redis
        String jwt = JwtUtils.sign(username, 9000);
        redisTemplate.opsForValue().set("Jwt-" + param.getUsername(), jwt, 9000, TimeUnit.SECONDS);
        // 设置token
        response.setHeader("Set-Token", jwt);
        return user;
    }

    @PostMapping("/loginByVerifyCode")
    public UserEntity loginByVerifyCode(@RequestParam String username, @RequestParam String code, HttpServletResponse response) {
        Subject subject = SecurityUtils.getSubject();
        UserEntity user = null;
        // 登录
        subject.login(new UserEmailToken(username, code));
        log.info("用户登录,邮箱:[{}]", username);
        user = (UserEntity) subject.getPrincipal();
        // 签发Jwt,存入Redis
        String jwt = JwtUtils.sign(username, 9000);
        redisTemplate.opsForValue().set("Jwt-" + username, jwt, 9000, TimeUnit.SECONDS);
        // 设置token
        response.setHeader("Set-Token", jwt);
        return user;
    }

    @PutMapping("/user/update")
    public UserEntity update(@RequestBody UserVO userVO) {
        log.info("用户更新,邮箱:[{}]", userVO.getUsername());
        return userService.update(userVO);
    }

    @GetMapping("/user/detail")
    public UserEntity detail() {
        return (UserEntity) SecurityUtils.getSubject().getPrincipal();
    }

    @PostMapping(value = "/icon/upload")
    public ResultVO<UserEntity> uploadIcon(@RequestParam("image") MultipartFile uploadFile) {
        String url = null;
        try {
            //重命名
            String newFilename = QiniuUtils.renamePic(Objects.requireNonNull(uploadFile.getOriginalFilename()));
            url = QiniuUtils.InputStreamUpload((FileInputStream) uploadFile.getInputStream(), newFilename);
            if (url.contains("error")) {
                return new ResultVO<>(ResultCode.FAILED);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("头像上传成功");
        UserVO user = new UserVO();
        user.setIcon(url);
        return new ResultVO<>(userService.update(user));
    }
}