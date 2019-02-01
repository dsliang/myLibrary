package cn.dsliang.library.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_rule")
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rule_id")
    private Integer ruleId;

    @Column(name = "rule_name")
    private String ruleName;

    @Column(name = "borrow_number")
    private Integer borrowNumber;

    @Column(name = "borrow_days")
    private Integer borrowDays;

    @Column(name = "renewal_times")
    private Integer renewalTimes;

    @Column(name = "renewal_days")
    private Integer renewalDays;

    @Column(name = "status", columnDefinition = "tinyint(1)")
    private Integer status;

    @Column(name = "create_time", insertable = false, updatable = false,
            columnDefinition = "datetime NULL DEFAULT CURRENT_TIMESTAMP")
    private Date createTime;

    @Column(name = "update_time", insertable = false, updatable = false,
            columnDefinition = "datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateTime;

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public Integer getBorrowNumber() {
        return borrowNumber;
    }

    public void setBorrowNumber(Integer borrowNumber) {
        this.borrowNumber = borrowNumber;
    }

    public Integer getBorrowDays() {
        return borrowDays;
    }

    public void setBorrowDays(Integer borrowDays) {
        this.borrowDays = borrowDays;
    }

    public Integer getRenewalTimes() {
        return renewalTimes;
    }

    public void setRenewalTimes(Integer renewalTimes) {
        this.renewalTimes = renewalTimes;
    }

    public Integer getRenewalDays() {
        return renewalDays;
    }

    public void setRenewalDays(Integer renewalDays) {
        this.renewalDays = renewalDays;
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
