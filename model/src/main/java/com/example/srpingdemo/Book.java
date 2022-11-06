package com.example.srpingdemo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)//返回java对象给前端时候(序列化)，会只返回非null属性
@Entity
@Data
@Table(name = "mybook")
public class Book {

    @Id//主键
    @GeneratedValue(strategy = GenerationType.AUTO)//自增主键
    @Column(name="bookID")//对应的表字段是bookID
    private Long id;

    private String title;

    @Column(unique = true)
    private String isbn;

    //集合类属性统一使用@ElementCollection注释
    @ElementCollection
    private Map<String,String> desp;


    public Book() {
    }

    public Book(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }

    public Book(String title, String isbn, Map<String, String> desp) {
        this(title,isbn);
        this.desp = desp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Map<String, String> getDesp() {
        return desp;
    }

    public void setDesp(Map<String, String> desp) {
        this.desp = desp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", desp=" + desp +
                '}';
    }
}
