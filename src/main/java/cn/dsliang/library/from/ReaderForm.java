package cn.dsliang.library.from;

import cn.dsliang.library.entity.Reader;
import cn.dsliang.library.entity.ReaderType;

import javax.persistence.*;
import java.util.Date;

public class ReaderForm {
    private Integer readerId;

    private String readerCard;

    private String readerName;

    private Integer gender;

    private Integer readerTypeId;

    private Integer status;

    public Integer getReaderId() {
        return readerId;
    }

    public void setReaderId(Integer readerId) {
        this.readerId = readerId;
    }

    public String getReaderCard() {
        return readerCard;
    }

    public void setReaderCard(String readerCard) {
        this.readerCard = readerCard;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getReaderTypeId() {
        return readerTypeId;
    }

    public void setReaderTypeId(Integer readerTypeId) {
        this.readerTypeId = readerTypeId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Reader convert() {
        Reader reader = new Reader();
        reader.setId(readerId);
        reader.setCard(readerCard);
        reader.setName(readerName);
        reader.setGender(gender);
        reader.setStatus(status);

        return reader;
    }
}