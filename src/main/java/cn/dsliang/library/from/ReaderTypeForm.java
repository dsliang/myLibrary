package cn.dsliang.library.from;

import cn.dsliang.library.entity.ReaderType;
import cn.dsliang.library.entity.Rule;

import javax.persistence.*;
import java.util.Date;

public class ReaderTypeForm {
    private Integer readerTypeId;

    private String readerTypeName;

    private Integer ruleId;

    private Integer status;

    private String comment;

    public Integer getReaderTypeId() {
        return readerTypeId;
    }

    public void setReaderTypeId(Integer readerTypeId) {
        this.readerTypeId = readerTypeId;
    }

    public String getReaderTypeName() {
        return readerTypeName;
    }

    public void setReaderTypeName(String readerTypeName) {
        this.readerTypeName = readerTypeName;
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ReaderType convert() {
        ReaderType readerType = new ReaderType();
        readerType.setId(readerTypeId);
        readerType.setName(readerTypeName);
        readerType.setStatus(status);
        readerType.setComment(comment);

        return readerType;
    }

}

