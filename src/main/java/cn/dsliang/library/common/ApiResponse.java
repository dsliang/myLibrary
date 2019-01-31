package cn.dsliang.library.common;

import cn.dsliang.library.entity.User;

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
        return new ApiResponse(0, "success", data);
    }

    static public ApiResponse success() {
        return new ApiResponse(0, "success", null);
    }

    public static ApiResponse<User> error(Integer code, String msg) {
        return new ApiResponse(code, msg, null);
    }

    public static ApiResponse<User> error(String msg) {
        return new ApiResponse(1, msg, null);
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
