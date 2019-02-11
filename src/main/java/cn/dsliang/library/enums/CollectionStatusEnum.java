package cn.dsliang.library.enums;

public enum CollectionStatusEnum implements CodeEnum {
    NORMAL(0,"在馆"),
    BORROWED(1,"借出");

    private Integer code;

    private String message;

    CollectionStatusEnum(Integer code, String message) {
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
