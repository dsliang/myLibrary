package cn.dsliang.library.enums;

public enum ReaderGenderEnum implements CodeEnum {
    Male(0, "男"),
    Female(1, "女");

    private Integer code;

    private String message;

    ReaderGenderEnum(Integer code, String message) {
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