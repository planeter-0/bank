package com.sanxiang.bank.service;


import com.sanxiang.bank.model.entity.Deposit;
import com.sanxiang.bank.model.entity.UserLabel;
import com.sanxiang.bank.model.vo.DepositVO;
import com.sanxiang.bank.model.vo.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface DepositService {
    Deposit create(Deposit d);
    DepositVO getById(Long id);
    Page<Deposit> getByUidAndType(Long uid, String type, PageRequest pageRequest);
    Deposit update(Deposit d);
}
