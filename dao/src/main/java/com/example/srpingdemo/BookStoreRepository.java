package com.example.srpingdemo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookStoreRepository extends JpaRepository<BookStore, Long> {//Book是要管理的实体，Long是主键的数据类型。

    List<BookStore> findByStoreName(String storeName);
}