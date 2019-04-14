package cn.dsliang.library.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_return")
public class Return {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "return_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "circulation_record_id")
    private CirculationRecord circulationRecord;

    @Column(name = "returned_date")
    private Date returnedDate;

    @Column(name = "create_time", updatable = false, insertable = false,
            columnDefinition = "datetime NULL DEFAULT CURRENT_TIMESTAMP")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CirculationRecord getCirculationRecord() {
        return circulationRecord;
    }

    public void setCirculationRecord(CirculationRecord circulationRecord) {
        this.circulationRecord = circulationRecord;
    }

    public Date getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
