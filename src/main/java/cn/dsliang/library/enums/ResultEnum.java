package cn.dsliang.library.enums;

public enum ResultEnum {

    USER_NOT_EXIST(10, "用户不存在"),
    UP_TO_BORROW_LIMIT(11, "达到借阅上限"),
    COLLECTION_STATUS_ERROR(12, "馆藏状态不正确"),
    UP_TO_RENEW_LIMIT(13, "达到续借上限"),
    RULE_NOT_EXIST(14, "借阅规则不存在"),
    LOCATION_NOT_EXIST(15,"馆藏地址不存在"),
    BIBLIO_NOT_EXIST(16,"书目不存在")
    ;

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
