package cn.dsliang.library.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_renew")
public class Renew {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "renew_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "circulation_record_id")
    private CirculationRecord circulationRecord;

    @Column(name = "renewal_days")
    private Integer renewalDays;

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

    public Integer getRenewalDays() {
        return renewalDays;
    }

    public void setRenewalDays(Integer renewalDays) {
        this.renewalDays = renewalDays;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
