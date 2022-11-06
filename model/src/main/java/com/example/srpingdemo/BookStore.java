package com.example.srpingdemo;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)//返回java对象给前端时候(序列化)，会只返回非null属性
@Entity
@Data
@Table(name="bookstore")
public class BookStore {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "store_id")
    long storeId;//表中对应的字段名是store_id
    String storeName;//表中对应的字段名是store_name


    ///这里从表中会插入一个新的字段，也就是外键字段;
    //name属性是用来标识从表中所对应的外键字段的名称(是数据库中的字段名，不是成员变量名)
    //referencedColumnName属性是参考或引用主表中的字段名称，默认引用的是主键名称
    @OneToMany(targetEntity = Book.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id",referencedColumnName = "store_id")
    List<Book> books;

    public BookStore() {
    }

    public BookStore(String storeName, List<Book> books) {
        this.storeName = storeName;
        this.books = books;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    @Override
    public String toString() {
        return "BookStore{" +
                "storeId=" + storeId +
                ", storeName='" + storeName + '\'' +
                ", books=" + books +
                '}';
    }
}
