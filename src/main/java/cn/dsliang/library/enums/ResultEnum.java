package cn.dsliang.library.enums;

public enum ResultEnum {

    UP_TO_BORROW_LIMIT(20, "达到借阅上限"),
    COLLECTION_STATUS_ERROR(21, "馆藏状态不正确");

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
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
