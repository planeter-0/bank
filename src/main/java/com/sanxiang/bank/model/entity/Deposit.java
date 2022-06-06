package com.sanxiang.bank.model.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Deposit {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 产品id
     */
    private Long productId;
    private String name;
    /**
     * 类型
     */
    private String type;
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
}
