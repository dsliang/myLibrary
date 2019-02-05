package cn.dsliang.library.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_collection")
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collection_id")
    private Integer collectionId;

    @ManyToOne
    @JoinColumn(name = "biblio_id")
    private Biblio biblio;

    @Column(name = "barcode")
    private String barcode;

    @Column(name = "category_number")
    private String categoryNumber;

    @Column(name = "serial_number")
    private Integer serialNumber;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "create_time", insertable = false, updatable = false,
            columnDefinition = "datetime NULL DEFAULT CURRENT_TIMESTAMP")
    private Date createTime;

    @Column(name = "update_time", insertable = false, updatable = false,
            columnDefinition = "datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateTime;

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public Biblio getBiblio() {
        return biblio;
    }

    public void setBiblio(Biblio biblio) {
        this.biblio = biblio;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getCategoryNumber() {
        return categoryNumber;
    }

    public void setCategoryNumber(String categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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