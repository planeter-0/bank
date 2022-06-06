package com.sanxiang.bank.controller.api;

import com.sanxiang.bank.model.entity.RealNameInfo;
import com.sanxiang.bank.model.vo.ResultVO;
import com.sanxiang.bank.service.RealNameInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class RealNameInfoController {
    @Resource
    private RealNameInfoService realNameInfoService;
    @PostMapping("/realNameInfo/upload")
    public ResultVO<String> upload(@RequestBody RealNameInfo info) {
        realNameInfoService.create(info);
        log.info("实名信息上传成功，产品ID:[{}]",info.toString());
        return new ResultVO<>("上传成功，预计在2个工作日内审核完毕");
    }
    @PutMapping("/realNameInfo/pass")
    public ResultVO<String> pass(@RequestParam Long id) {
        realNameInfoService.pass(id);
        return new ResultVO<>("已通过");
    }
    @GetMapping("/realNameInfo/getByUid")
    public RealNameInfo getByUid(@RequestParam Long uid) {
        return realNameInfoService.getByUid(uid);
    }
}
