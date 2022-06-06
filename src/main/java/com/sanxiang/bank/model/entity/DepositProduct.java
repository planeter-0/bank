package com.sanxiang.bank.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
@JsonIgnoreProperties( value = { "hibernateLazyInitializer", "handler"})
public class DepositProduct implements Serializable {
    /**
     * 产品编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    /**
     * 产品名称
     */
    private String name;
    /**
     * A.大额存单
     * B.活期+
     * C.定期+
     * D.定期存款
     */
    private String type;
    /**
     * 产品期限
     */
    private Long hours;
    /**
     * 年利率
     */
    private float annualInterestRate;
    /**
     * 起存金额
     */
    private double initialAmount;
    /**
     * 递增金额
     */
    private double incrementalAmount;
    /**
     * 单人限额
     */
    private double singlePersonLimit;
    /**
     * 单日限额
     */
    private double singleDayLimit;
    /**
     * 结息方式
     */
    private String interestSettlementMethod;
    /**
     * 风险等级
     */
    private String riskLevel;
    /**
     * 描述
     */
    private String description;
    /**
     * 库存
     */
    private int stock;
    /**
     * 锁定的库存
     */
    private int locking;

}
