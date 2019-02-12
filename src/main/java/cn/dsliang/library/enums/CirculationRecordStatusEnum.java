package cn.dsliang.library.enums;

public enum CirculationRecordStatusEnum implements CodeEnum {
    Punctual(0, "守时"),
    Overdue(1, "逾期");


    private Integer code;

    private String message;

    CirculationRecordStatusEnum(Integer code, String message) {
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
