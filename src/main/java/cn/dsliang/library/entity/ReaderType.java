package cn.dsliang.library.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_reader_type")
public class ReaderType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reader_type_id")
    private Integer readerTypeId;

    @Column(name = "reader_type_name")
    private String readerTypeName;

    @ManyToOne
    @JoinColumn(name = "rule_id")
    private Rule rule;

    @Column(name = "status", columnDefinition = "tinyint(1)")
    private Integer status;

    @Column(name = "comment")
    private String comment;

    @Column(name = "create_time", updatable = false, insertable = false,
            columnDefinition = "datetime NULL DEFAULT CURRENT_TIMESTAMP")
    private Date createTime;

    @Column(name = "update_time", updatable = false, insertable = false,
            columnDefinition = "datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateTime;


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

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
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