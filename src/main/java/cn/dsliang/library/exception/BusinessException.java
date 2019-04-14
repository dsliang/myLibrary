package cn.dsliang.library.exception;

import cn.dsliang.library.enums.ResultEnum;

public class BusinessException extends RuntimeException {
    private Integer code;

    public BusinessException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
