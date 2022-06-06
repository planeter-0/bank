package com.sanxiang.bank.model.vo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
public class DepositVO {
    /**
     * id
     */

    private Long id;
    /**
     * 产品编号
     */
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
     * 起息日
     */
    private Date valueDate;
    /**
     * 到期日
     */
    private Date dueDate;
    /**
     * 用户id
     */
    private Long uid;

    public DepositVO(Long id, Long productId, String name, String type, Long hours, float annualInterestRate, double initialAmount, double incrementalAmount, double singlePersonLimit, double singleDayLimit, String interestSettlementMethod, String riskLevel, String description, Date valueDate, Date dueDate, Long uid) {
        this.id = id;
        this.productId = productId;
        this.name = name;
        this.type = type;
        this.hours = hours;
        this.annualInterestRate = annualInterestRate;
        this.initialAmount = initialAmount;
        this.incrementalAmount = incrementalAmount;
        this.singlePersonLimit = singlePersonLimit;
        this.singleDayLimit = singleDayLimit;
        this.interestSettlementMethod = interestSettlementMethod;
        this.riskLevel = riskLevel;
        this.description = description;
        this.valueDate = valueDate;
        this.dueDate = dueDate;
        this.uid = uid;
    }
}
