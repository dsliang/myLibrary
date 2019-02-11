package cn.dsliang.library.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_circulating")
public class Circulating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "circulating_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "reader_id")
    private Reader reader;

    @ManyToOne
    @JoinColumn(name = "collection_id")
    private Collection collection;

    @ManyToOne
    @JoinColumn(name = "biblio_id")
    private Biblio biblio;

    @Column(name = "borrow_date", columnDefinition = "date")
    private Date borrowDate;


    @Column(name = "return_date", columnDefinition = "date")
    private Date returnDate;

    @Column(name = "renewal_times")
    private Integer renewalTimes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public Biblio getBiblio() {
        return biblio;
    }

    public void setBiblio(Biblio biblio) {
        this.biblio = biblio;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Integer getRenewalTimes() {
        return renewalTimes;
    }

    public void setRenewalTimes(Integer renewalTimes) {
        this.renewalTimes = renewalTimes;
    }
}

