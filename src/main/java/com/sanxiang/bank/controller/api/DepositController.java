package com.sanxiang.bank.controller.api;

import com.sanxiang.bank.model.entity.Deposit;
import com.sanxiang.bank.model.vo.DepositVO;
import com.sanxiang.bank.model.vo.Page;
import com.sanxiang.bank.model.vo.ResultVO;
import com.sanxiang.bank.service.DepositProductService;
import com.sanxiang.bank.service.DepositService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
public class DepositController {
    @Resource
    DepositService service;

    @PostMapping("/deposit/create")
    public Deposit create(@RequestBody Deposit d) {
        Deposit deposit = service.create(d);
        log.info("存款创建成功:[{}]", deposit.toString());
        return deposit;
    }

    @GetMapping("/deposit/getById")
    public DepositVO getById(@RequestParam Long id) {
        DepositVO vo = service.getById(id);
        return vo;
    }

    @GetMapping("/deposit/getByUid")
    public Page<Deposit> getByUidAndType(@RequestParam int page_size, @RequestParam int page_num, @RequestParam Long uid, @RequestParam(required = false) String type) {
        PageRequest pageRequest = PageRequest.of(page_num - 1, page_size);
        log.info("分页获取用户([{}])购买的存款, page_size:[{}], page_size[{}]", uid, page_size, page_num);
        return service.getByUidAndType(uid, type, pageRequest);
    }

    @PutMapping("/deposit/update")
    public Deposit update(@RequestBody Deposit d) {
        Deposit deposit = service.update(d);
        log.info("存款更新成功，详情:[{}]",d.toString());
        return deposit;
    }
}
