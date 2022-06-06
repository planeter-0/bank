package com.sanxiang.bank.controller.api;

import com.sanxiang.bank.model.vo.UserLabelVO;
import com.sanxiang.bank.service.UserLabelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@Slf4j
@RestController
public class UserLabelController {
    @Resource
    private UserLabelService userLabelService;
    @PostMapping("/user/label/create")
    public String createLabel(@RequestBody UserLabelVO u){
        try {
            userLabelService.create(u);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "创建标签成功";
    }
    @GetMapping("/user/label/getByUid")
    public UserLabelVO getByUid(@RequestParam Long uid){
        try{
           return new UserLabelVO(userLabelService.getByUid(uid));
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @PutMapping("/user/label/update")
    public String update(@RequestBody UserLabelVO u){
        userLabelService.update(u.toEntity());
        return "成功";
    }
}
