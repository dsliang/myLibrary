package cn.dsliang.library.enums;

public enum ReaderStatusEnum implements CodeEnum {
    Valid(0, "有效"),
    Invalid(1, "无效");

    private Integer code;

    private String message;

    ReaderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
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