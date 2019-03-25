package cn.dsliang.library.common;

import cn.dsliang.library.enums.ApiResponseEnum;

public class ApiResponse<T> {
    private Integer code;
    private String msg;
    private T data;

    public ApiResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    static public <T> ApiResponse<T> success(T data) {
        return new ApiResponse<T>(ApiResponseEnum.SUCCESS.getCode(), ApiResponseEnum.SUCCESS.getMessage(), data);
    }

    static public <T> ApiResponse<T> success() {
        return new ApiResponse<T>(ApiResponseEnum.SUCCESS.getCode(), ApiResponseEnum.SUCCESS.getMessage(), null);
    }

    public static <T> ApiResponse<T> error(Integer code, String msg) {
        return new ApiResponse<T>(code, msg, null);
    }

    public static <T> ApiResponse<T> error(String msg) {
        return new ApiResponse<T>(ApiResponseEnum.ERROR.getCode(), ApiResponseEnum.ERROR.getMessage(), null);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
