package com.sanxiang.bank.repository;

import com.sanxiang.bank.model.entity.RealNameInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealNameInfoRepository extends JpaRepository<RealNameInfo, Long> {
    RealNameInfo findByUid(Long uid);
}
