package com.sanxiang.bank.service.imp;

import com.sanxiang.bank.model.entity.Deposit;
import com.sanxiang.bank.model.entity.DepositProduct;
import com.sanxiang.bank.model.vo.DepositVO;
import com.sanxiang.bank.model.vo.Page;
import com.sanxiang.bank.repository.DepositProductRepository;
import com.sanxiang.bank.repository.DepositRepository;
import com.sanxiang.bank.service.DepositService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DepositServiceImp implements DepositService {
    @Resource
    DepositRepository repository;
    @Resource
    DepositProductRepository productRepository;

    @Override
    public Deposit create(Deposit d) {
        return repository.save(d);
    }

    @Override
    public DepositVO getById(Long id) {

        Deposit d = repository.getById(id);
        DepositProduct p = productRepository.getById(d.getProductId());
        DepositVO vo = new DepositVO(
                d.getId(),
                d.getProductId(),
                p.getName(),
                p.getType(),
                p.getHours(),
                p.getAnnualInterestRate(),
                p.getInitialAmount(),
                p.getIncrementalAmount(),
                p.getSinglePersonLimit(),
                p.getSingleDayLimit(),
                p.getInterestSettlementMethod(),
                p.getRiskLevel(),
                p.getDescription(),
                d.getValueDate(),
                d.getDueDate(),
                d.getUid());
        return vo;
    }

    @Override
    public Page<Deposit> getByUidAndType(Long uid, String type, PageRequest pageRequest) {
        org.springframework.data.domain.Page<Deposit> page;
        Page<Deposit> page1 = new Page<>();
        if (StringUtils.isEmpty(type)) {
            page = repository.findByUid(uid, pageRequest);
        } else {
            page = repository.findByUidAndType(uid, type, pageRequest);
        }
        page1.setContent(page.getContent());
        page1.setTotalPages(page.getTotalPages());
        return page1;
    }

    @Override
    public Deposit update(Deposit d) {
        return repository.save(d);
    }
}
