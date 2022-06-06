package com.sanxiang.bank.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Page<T> implements Serializable {
    private List<T> content;
    private Integer totalPages;
    public Page(org.springframework.data.domain.Page page){

    }

    public Page() {

    }
}
