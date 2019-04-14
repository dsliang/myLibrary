package cn.dsliang.library.from;

public class RuleForm {
    private Integer id;

    private String name;

    private Integer borrowNumber;

    private Integer borrowDays;

    private Integer renewalTimes;

    private Integer renewalDays;

    private Integer status;
    private String statusName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
