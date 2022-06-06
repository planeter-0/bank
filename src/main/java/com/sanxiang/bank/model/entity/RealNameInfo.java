package com.sanxiang.bank.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class RealNameInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long uid;
    private String realName;
    private String idCard;
    private String positiveIdCard;
    private String reverseIdCard;
    private String selfie;
    private boolean pass;

}
