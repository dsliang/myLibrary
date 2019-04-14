package cn.dsliang.library.from;

import java.math.BigDecimal;
import java.util.List;

public class BiblioForm {
    private Integer id;

    private String isbn;

    private String title;

    private String author;

    private String press;

    private String categoryNumber;

    private List<Integer> serialNumbers;

    private String language;

    private BigDecimal price;

    private String introduction;

    private Integer page;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getCategoryNumber() {
        return categoryNumber;
    }

    public void setCategoryNumber(String categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    public List<Integer> getSerialNumbers() {
        return serialNumbers;
    }

    public void setSerialNumbers(List<Integer> serialNumbers) {
        this.serialNumbers = serialNumbers;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
