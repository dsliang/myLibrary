package cn.dsliang.library.enums;

public enum ResultEnum {

    USER_NOT_EXIST(10, "用户不存在"),
    UP_TO_BORROW_LIMIT(11, "达到借阅上限"),
    COLLECTION_STATUS_ERROR(12, "馆藏状态不正确"),
    UP_TO_RENEW_LIMIT(13, "达到续借上限"),
    RULE_NOT_EXIST(14, "借阅规则不存在"),
    LOCATION_NOT_EXIST(15, "馆藏地址不存在"),
    BIBLIO_NOT_EXIST(16, "书目不存在"),
    COLLECTION_NOT_EXIST(17, "馆藏不存在"),
    READER_TYPE_NOT_EXIST(18, "读者类型不存在"),
    READER_NOT_EXIST(19, "读者不存在"),
    USER_STATUS_INVALID(20, "用户状态无效"),
    USER_PASSWORD_ERROR(21,"用户密码错误")


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
