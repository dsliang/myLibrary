package cn.dsliang.library.handler;

import cn.dsliang.library.common.ApiResponse;
import cn.dsliang.library.exception.BusinessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    ApiResponse handleException(Exception e) {
        e.printStackTrace();
        return ApiResponse.error(1, e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    ApiResponse handleBusinessException(BusinessException e) {
        e.printStackTrace();
        return ApiResponse.error(1, e.getMessage());
    }
}
