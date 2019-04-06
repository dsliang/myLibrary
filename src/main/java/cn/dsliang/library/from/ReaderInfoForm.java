package cn.dsliang.library.from;

public class ReaderInfoForm {
    private Integer readerId;
    private String name;
    private String statusName;
    private Integer borrowNumber;
    private Integer borrowedNumber;
    private Integer ExtendedNumber;

    public Integer getReaderId() {
        return readerId;
    }

    public void setReaderId(Integer readerId) {
        this.readerId = readerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Integer getBorrowNumber() {
        return borrowNumber;
    }

    public void setBorrowNumber(Integer borrowNumber) {
        this.borrowNumber = borrowNumber;
    }

    public Integer getBorrowedNumber() {
        return borrowedNumber;
    }

    public void setBorrowedNumber(Integer borrowedNumber) {
        this.borrowedNumber = borrowedNumber;
    }

    public Integer getExtendedNumber() {
        return ExtendedNumber;
    }

    public void setExtendedNumber(Integer extendedNumber) {
        ExtendedNumber = extendedNumber;
    }
}
