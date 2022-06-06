package com.sanxiang.bank.service.imp;


import com.sanxiang.bank.common.annotation.Cache;
import com.sanxiang.bank.common.exception.ApiException;
import com.sanxiang.bank.model.entity.UserEntity;
import com.sanxiang.bank.model.param.RegisterParam;
import com.sanxiang.bank.model.vo.UserVO;
import com.sanxiang.bank.repository.UserRepository;
import com.sanxiang.bank.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;
    @Resource
    RedisTemplate<String, Object> redisTemplate;
    @Resource
    JavaMailSender javaMailSender;

    @Override
    public void verifyEmail(String email) {
        if (!email.matches("^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$"))
            throw new ApiException("邮箱格式错误");
        // 生成6位数字验证码
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            int randNumber = r.nextInt(10);
            sb.append(randNumber);
        }
        // 生成邮件
        String code = sb.toString();
        // 发送邮件
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message,true,"utf-8");
            messageHelper.setFrom("planeter@126.com","三湘银行");
            messageHelper.setTo(email);
            messageHelper.setSubject("三湘银行注册邮箱验证");
            messageHelper.setText("验证码:" + code+", 五分钟后失效");
            javaMailSender.send(message);
            log.info("Send success!");
            // 将验证码存入Redis, 5min后失效
            redisTemplate.opsForValue().set("Reg-" + email, code, 5, TimeUnit.MINUTES);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Long register(@Validated RegisterParam param) {
        if (userRepository.existsByUsername(param.getUsername()))
            throw new ApiException("邮箱已被使用");
        if (!param.getVerifyCode().equals(redisTemplate.opsForValue().get("Reg-" + param.getUsername())))
            throw new ApiException("验证码错误");
        UserEntity user = new UserEntity();
        user.setUsername(param.getUsername());
        user.setPassword(BCrypt.hashpw(param.getPassword(), BCrypt.gensalt()));
        return userRepository.save(user).getId();
    }

    @Override
    public String getVerifyCode(String username) {
        return Objects.requireNonNull(redisTemplate.opsForValue().get("Reg-" + username)).toString();
    }

    @Override
    @CachePut(value = {"User"}, key = "#user.username")
    public UserEntity update(UserVO user) {
        UserEntity userEntity;
        try {
            userEntity = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        } catch (Exception e){
            throw new ApiException("未登录");
        }
        if(user.getPassword()!=null)
            userEntity.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        if(user.getNickname()!=null)
            userEntity.setNickname(user.getNickname());
        if(user.getIcon()!=null)
            userEntity.setIcon(user.getIcon());
        return userRepository.save(userEntity);
    }
    @Override
    @Cache(value = "User", key = "#username")
    public UserEntity getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).get();
    }

}
