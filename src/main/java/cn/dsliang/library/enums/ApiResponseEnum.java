package cn.dsliang.library.enums;

public enum ApiResponseEnum {

    SUCCESS(0, "成功"),
    ERROR(1, "错误"),
    UNAUTHORIZED(401, "用户未登录");

    private Integer code;
    private String message;

    ApiResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
