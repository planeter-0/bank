package com.sanxiang.bank.controller;

import com.sanxiang.bank.common.enums.ResultCode;
import com.sanxiang.bank.common.exception.ApiException;
import com.sanxiang.bank.model.vo.ResultVO;
import org.apache.shiro.ShiroException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

/**
 * 全局异常处理
 *
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {
    /**
     * 自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(ApiException.class)
    public ResultVO<String> apiExceptionHandler(ApiException e) {
        return new ResultVO<>(e.getResultCode(), e.getMsg());
    }

    /**
     * 参数校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 参数校验失败，提取其余错误提示信息进行返回
        return new ResultVO<>(ResultCode.VALIDATE_FAILED, objectError.getDefaultMessage());
    }
    /**
     * Shiro异常
     * @param e
     * @return
     */
    @ExceptionHandler(ShiroException.class)
    public ResultVO<String> ShiroExceptionHandler(ShiroException e) {
        // 参数校验失败，提取其余错误提示信息进行返回
        return new ResultVO<>(ResultCode.UNAUTHORIZED, e.getMessage());
    }
    /**
     * JPA
     */
    @ExceptionHandler(PersistenceException.class)
    public ResultVO<Object> doHandlePersistenceException(
            PersistenceException e) {
        ResultVO<Object> r = new ResultVO<>();
        if (e instanceof EntityNotFoundException) {
            r = new ResultVO<>(ResultCode.NO_SUCH_ENTITY,e.getMessage());
        } else {
            e.printStackTrace();
        }
        return r;
    }
    /**
     * NullPointerException
     */
    @ExceptionHandler(NullPointerException.class)
    public ResultVO<Object> doHandleNullPointerException(NullPointerException e) {
        return new ResultVO<>(ResultCode.NULL_POINTER, e.getMessage());
    }
}
