package cn.dsliang.library.entity;

import org.hibernate.annotations.Columns;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Integer locationId;

    @Column(name = "location_name")
    private String locationName;

    @Column(name = "comment")
    private String comment;

    @Column(name = "create_time", updatable = false, insertable = false,
            columnDefinition = "datetime NULL DEFAULT CURRENT_TIMESTAMP")
    private Date createTime;

    @Column(name = "update_time", updatable = false, insertable = false,
            columnDefinition = "datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateTime;

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
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