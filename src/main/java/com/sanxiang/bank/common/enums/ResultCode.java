package com.sanxiang.bank.common.enums;

import lombok.Getter;

/**
 * 响应码枚举
 */
@Getter
public enum ResultCode {

    SUCCESS(0000, "操作成功"),

    UNAUTHORIZED(1001, "认证异常"),

    FORBIDDEN(1002, "没有相关权限"),

    VALIDATE_FAILED(1003, "参数校验失败"),

    FAILED(1004, "接口异常"),

    ERROR(5000, "未知错误"),
    NULL_POINTER(1006,"空指针"),
    NO_SUCH_ENTITY(1005,"没有该实体");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
