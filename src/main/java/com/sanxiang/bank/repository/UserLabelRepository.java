package com.sanxiang.bank.repository;


import com.sanxiang.bank.model.entity.UserLabel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLabelRepository extends JpaRepository<UserLabel, Long> {
}
