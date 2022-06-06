package com.sanxiang.bank.controller.api;

import com.sanxiang.bank.model.entity.DepositProduct;
import com.sanxiang.bank.model.vo.ResultVO;
import com.sanxiang.bank.service.DepositProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
public class DepositProductController {
    @Resource
    private DepositProductService service;

    @PostMapping("/deposit/product/create")
    public ResultVO<String> create(@RequestBody DepositProduct d) {
        try {
            service.create(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("产品创建成功，详情:[{}]", d.toString());
        return new ResultVO<>("创建成功");
    }

    @GetMapping("/deposit/product/getById")
    public DepositProduct getById(@RequestParam Long id) {
        try {
            DepositProduct d = service.getById(id);
            return d;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/deposit/product/getAll")
    public com.sanxiang.bank.model.vo.Page<DepositProduct> getAll(@RequestParam int page_size, @RequestParam int page_num, @RequestParam(required = false) String type) {
        try {
            DepositProduct d = new DepositProduct();
            d.setType(type);
            PageRequest pageRequest = PageRequest.of(page_num - 1, page_size);
            Page<DepositProduct> page = null;
            com.sanxiang.bank.model.vo.Page<DepositProduct> page1 = new com.sanxiang.bank.model.vo.Page<DepositProduct>();
            if (StringUtils.isEmpty(type)) {
                page = service.findAll(pageRequest);
                page1.setContent(page.getContent());
                page1.setTotalPages(page.getTotalPages());
            } else {
                page = service.findByType(type, pageRequest);
                page1.setContent(page.getContent());
                page1.setTotalPages(page.getTotalPages());
            }
            return page1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("/deposit/product/update")
    public ResultVO<String> update(@RequestBody DepositProduct d) {
        service.update(d);
        return new ResultVO<>("更新成功");
    }

    @PutMapping("/deposit/product/lock")
    public DepositProduct lock(@RequestParam Long productId, @RequestParam Integer amount) {
        log.info("库存锁定，产品ID:[{}], 数量:[{}]", productId, amount);
        return service.lock(productId, amount);
    }

    @PutMapping("/deposit/product/unlock")
    public DepositProduct unlockAndReduceStock(@RequestParam Long productId, @RequestParam Integer amount) {
        log.info("库存解锁，产品ID:[{}], 数量:[{}]", productId, amount);
        return service.unlock(productId, amount);
    }
}
