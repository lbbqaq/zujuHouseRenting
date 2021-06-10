package com.lbb.vuehousespringboot.common.exception;

import com.lbb.vuehousespringboot.common.lang.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 通过使用@ControllerAdvice来进行统一异常处理，@ExceptionHandler(value = RuntimeException.class)来指定捕获的Exception各个类型异常 ，这个异常的处理，是全局的，所有类似的异常，都会跑到这个地方处理。
 *
 */
//异步的
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandle {

    //shiro异常,登录不成功401
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = ShiroException.class)
    public Result handle(ShiroException e){
        log.error("shiro异常---------{}",e);
        return Result.fail(401,e.getMessage(),null);
    }

    //大概的异常
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handle(MethodArgumentNotValidException e){
        log.error("实体校验时异常----------{}",e);
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        return Result.fail(objectError.getDefaultMessage());
    }

    //大概的异常
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result handle(IllegalArgumentException e){
        log.error("Assert断言时时异常----------{}",e);
        return Result.fail(e.getMessage());
    }

    //大概的异常
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public Result handle(RuntimeException e){
        log.error("运行时异常----------{}",e);
        return Result.fail(e.getMessage());
    }

}
