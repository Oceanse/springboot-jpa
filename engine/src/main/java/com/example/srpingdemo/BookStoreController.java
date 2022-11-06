package com.example.srpingdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class BookStoreController {

    @Autowired
    BookStoreRepository bookStoreRepository;


    //http://localhost:8081/addbookStore
    @GetMapping("/addbookStore")
    public void insertBookStore(){
        List<Book> books=Arrays.asList(new Book("Thinking in Java", "013659723128"),new Book("Beginning Java2", "1861002238"));
        List<Book> books2=Arrays.asList(new Book("Thinking in python", "345ee6789"),new Book("Beginning pathy2", "345678"));
        bookStoreRepository.save(new BookStore("storename1", books));
        bookStoreRepository.save(new BookStore("storename2", books2));

    }

    @GetMapping("/getAllBookStore")
    public List<BookStore> getAllBookStore(){
        List<BookStore> bookStores = bookStoreRepository.findAll();
        System.out.println("bookStores: "+bookStores);
        return bookStores;
    }


    @GetMapping("/findByStoreName")
    public List<BookStore> findByStoreName(){
        List<BookStore> bookStores =bookStoreRepository.findByStoreName("storename1");
        System.out.println("bookStores===: "+bookStores);
        return bookStores;
    }


}
