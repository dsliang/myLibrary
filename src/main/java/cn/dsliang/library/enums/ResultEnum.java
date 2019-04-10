package cn.dsliang.library.enums;

public enum ResultEnum {

    USER_NOT_EXIST(10, "用户不存在"),
    USER_IS_EXIST(11, "用户已存在"),
    UP_TO_BORROW_LIMIT(12, "达到借阅上限"),
    COLLECTION_IS_LENT_OUT(13, "馆藏已借出"),
    UP_TO_RENEW_LIMIT(14, "达到续借上限"),
    RULE_NOT_EXIST(15, "借阅规则不存在"),
    RULE_IS_EXIST(16, "借阅规则已存在"),
    RULE_IN_USE(16, "借阅规则使用中"),
    LOCATION_NOT_EXIST(17, "馆藏地址不存在"),
    LOCATION_IS_EXIST(18, "馆藏地址已存在"),
    LOCATION_IN_USE(18, "馆藏地址使用中"),
    BIBLIO_NOT_EXIST(19, "书目不存在"),
    BIBLIO_IN_USE(19, "书目使用中"),
    COLLECTION_NOT_EXIST(20, "馆藏不存在"),
    BARCODE_IS_EXIST(21, "条形码已存在"),
    READER_TYPE_NOT_EXIST(22, "读者类型不存在"),
    READER_TYPE_IS_EXIST(23, "读者类型已存在"),
    READER_TYPE_IN_USE(23, "读者类型使用中"),
    READER_NOT_EXIST(24, "读者不存在"),
    READER_IN_USE(24, "读者使用中"),
    CARD_IS_EXIST(25, "读者证已存在"),
    USER_STATUS_INVALID(26, "用户状态无效"),
    USER_PASSWORD_ERROR(27, "用户密码错误"),
    CALL_NUMBER_FORMAT_ERROR(28, "索书号格式错误"),
    CIRCULATING_NOT_EXIST(29, "流通记录不存在");

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
