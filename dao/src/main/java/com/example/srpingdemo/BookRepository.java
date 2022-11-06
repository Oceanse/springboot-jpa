package com.example.srpingdemo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {//Book是要管理的实体，Long是主键的数据类型。

    //若含有相同isbn的两本书会报错
    Optional<Book> findByIsbn(String isbn);

    List<Book> findByTitle(String title);
    //等价于
    List<Book> findAllByTitle(String title);

    //包含查询
    List<Book> findByTitleContaining(String title);

    //多字段查询
    List<Book> findByTitleAndIsbn(String title,String isbn);

    //按照某个字段排序
    List<Book> findAllByTitleOrderByIdDesc(String title);


    //按字段+分页查询
    Page<Book> findAllByTitle(String title, Pageable pageable);

    //更新字段
    @Modifying
    @Transactional//Make sure not to use the the annotation from javax.transaction, instead use org.springframework.transaction.annotation.Transactional.
    @Query("update Book set title = :title where isbn = :isbn")
    void UpdateMybookTitleByIsbn(String title, String isbn);



    //按照字段删除
    //Apparently, Spring only marks the default methods of the CrudRepository interface as transactional, so any custom methods that you define yourself won't be managed by the EntityManager. The fix for this is quite simple. Just mark the custom method with @Transactional and the EntityManager will recognize it as a transaction.
    @Transactional
    long deleteByTitle(String title);


    //方法名查询是根据实体类属性名查询，虽然mybook表中含有外键字段store_id, 但是book实体类中没有，所以会产生No property storeId found for type Book!
    //List<BookStore> findBystoreIdAndIsbn(long storeId, String isbn);

    @Query(nativeQuery = true, value = "select * from mybook book where book.store_id=:storeId and book.isbn=:isbn")
    List<Book> findBystoreIdAndIsbn(@Param("storeId")long storeId, @Param("isbn")String isbn);




}