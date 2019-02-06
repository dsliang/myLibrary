package cn.dsliang.library.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_reader")
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reader_id")
    private Integer readerId;

    @Column(name = "reader_card")
    private String readerCard;

    @Column(name = "reader_name")
    private String readerName;

    @Column(name = "gender")
    private Integer gender;

    @ManyToOne
    @JoinColumn(name = "reader_type_id")
    private ReaderType readerType;

    @Column(name = "status", columnDefinition = "tinyint(1)")
    private Integer status;

    @Column(name = "create_time", updatable = false, insertable = false,
            columnDefinition = "datetime NULL DEFAULT CURRENT_TIMESTAMP")
    private Date createTime;


    @Column(name = "update_time", updatable = false, insertable = false,
            columnDefinition = "datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateTime;

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

    public ReaderType getReaderType() {
        return readerType;
    }

    public void setReaderType(ReaderType readerType) {
        this.readerType = readerType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
