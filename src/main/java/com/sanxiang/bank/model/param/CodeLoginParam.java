package com.sanxiang.bank.model.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
@Data
public class CodeLoginParam {
    private String username;
    private String code;
}
