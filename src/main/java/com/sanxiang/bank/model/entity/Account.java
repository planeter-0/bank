package com.sanxiang.bank.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@JsonIgnoreProperties( value = { "hibernateLazyInitializer", "handler"})
public class Account {
    @Id
    private Long uid;
    public double balance;
    public double product;
    public double income;
    public double expenditure;

    public Account(Long uid) {
        this.uid = uid;
    }

    public Account() {

    }

    /**
     * 获取总资产
     *
     * @return
     */
    public double getAssets() {
        return balance + product;
    }
}
