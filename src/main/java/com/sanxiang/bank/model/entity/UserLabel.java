package com.sanxiang.bank.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class UserLabel {
    @Id
    private Long uid;
    private String labelStr;

    public UserLabel(Long uid, String labelStr) {
        this.uid = uid;
        this.labelStr = labelStr;
    }

    public UserLabel() {

    }
}
