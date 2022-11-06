package com.example.srpingdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class BookController {
    @Autowired
    BookRepository bookRepository;


    //http://localhost:8081/addbook
    @GetMapping("/addbook")
    public void insertBook(){
        HashMap<String,String> desp=new HashMap();
        desp.put("chapeter01","oop");
        desp.put("chapeter02","generic");
        bookRepository.save(new Book());
        bookRepository.save(new Book("Thinking in Java", "013659723128"));
        bookRepository.save(new Book("Thinking in Java", "0136597238"));
        bookRepository.save(new Book("Thinking in Java", "0136597237"));
        bookRepository.save(new Book("Beginning Java2", "1861002238"));
        bookRepository.save(new Book("Java Gently", "0201342979"));
        bookRepository.save(new Book("Java2 Platform Unleashed", "0672316315123",desp));
    }

    @PostMapping("/addbook2")
    public Book saveBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }


    //curl -X DELETE http://localhost:8081/deleteByTitle
    @DeleteMapping("/deleteByTitle")
    public List<Book>  deleteByTitle() {
        bookRepository.deleteByTitle("Thinking in Java");
       return bookRepository.findAll();
    }


    @GetMapping("/findByIsbn")
    public Book findByIsbn(){
        Book springbootBook=bookRepository.findByIsbn("0136597238xx").orElse(new Book("springboot in action","0136597222"));
        System.out.println("springbootBook: "+springbootBook);
        Book book=bookRepository.findByIsbn("0672316315123").orElse(new Book("springboot in action","0136597222"));
        System.out.println("book: "+book);
        System.out.println("book.getDesp().get(chapeter01): "+book.getDesp().get("chapeter01"));
        return book;
    }



    @GetMapping("/getAllBook")
    public List<Book> getAllBook(){
        List<Book> allBook = bookRepository.findAll();
        System.out.println("allBook: "+allBook);
        System.out.println("allBookCount: "+bookRepository.count());
        return allBook;
    }


    @GetMapping("/findBookByTitle")
    public List<Book> findBookByTitle(){
        List<Book> Books = bookRepository.findByTitle("Thinking in Java");
        System.out.println(Books);
        return Books;
    }

    @GetMapping("/findAllBookTitle")
    public List<Book> findAllBookTitle(){
        List<Book> Books = bookRepository.findAllByTitle("Thinking in Java");
        System.out.println(Books);
        return Books;
    }


    @GetMapping("/findByTitleContaining")
    public List<Book> findByTitleContaining(){
        List<Book> Books = bookRepository.findByTitleContaining("Java2");
        System.out.println(Books);
        return Books;
    }

    @GetMapping("/findByTitleAndIsbn")
    public List<Book> findByTitleAndIsbn(){
        List<Book> Books = bookRepository.findByTitleAndIsbn("Thinking in Java","0136597238");
        System.out.println(Books);
        return Books;
    }


    @GetMapping("/findAllByTitleOrderByIdDesc")
    public List<Book> findAllByTitleOrderByIdDesc(){
        List<Book> Books = bookRepository.findAllByTitleOrderByIdDesc("Thinking in Java");
        System.out.println(Books);
        return Books;
    }

    @GetMapping("/findAllOrderByIdDesc")
    public List<Book> findAllOrderByIdDesc(){
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Book> Books = bookRepository.findAll(sort);
        System.out.println(Books);
        return Books;
    }



    @GetMapping("/pageQueryBook")
    public List<Book> pageQuery(){
        System.out.println("allBook: "+bookRepository.findAll());
       /* PageRequest pageRequest = new PageRequest(1, 2); //每页2个，查看第2页*/
        PageRequest pageRequest = PageRequest.of(1, 2); //每页2个，查看第2页
        Page<Book> Books = bookRepository.findAll(pageRequest);
        System.out.println("Books.getContent(): "+Books.getContent());
        return Books.getContent();
    }


    @GetMapping("/pageQueryByTitle")
    public List<Book> pageQueryByTitle(){
        System.out.println("allBook: "+bookRepository.findAll());
        PageRequest pageRequest = PageRequest.of(1, 2);  //每页2个，查看第2页
        Page<Book> Books = bookRepository.findAllByTitle("Thinking in Java",pageRequest);
        System.out.println("Books.getContent(): "+Books.getContent());
        return Books.getContent();
    }




    @GetMapping("/UpdateMybookTitleByIsbn")
    public void UpdateMybookTitleByIsbn(){
        System.out.println("beforeupdate: "+bookRepository.findAll());
        bookRepository.UpdateMybookTitleByIsbn("pythonxxxxxx","013659723128");
        System.out.println("afterupdate: "+bookRepository.findAll());
    }


    /**
     * 根据store_id和isbn查询， 由于这两个字段分布在两个实体类当中，所以不能字节根据方法名查询
     *
     */
    @GetMapping("/findBystoreIdAndIsbn")
    public void findBystoreIdAndIsbn(){
        List<Book> books = bookRepository.findBystoreIdAndIsbn(1, "013659723128");
        System.out.println("bookStores======: "+books);
    }








/*
    @GetMapping("/findAll")
    public List<Bllionaires> getAllBllionaires(){
        List<Bllionaires> users = bllionariesRepository.findAll();
        System.out.println(users);
        return users;
    }


    @GetMapping("/findByFirstName")
    public Bllionaires getBllionaires(){
        Bllionaires aliko = bllionariesRepository.findByFirstName("Aliko");
        System.out.println(aliko.getFirstName());
        return aliko;
    }*/
}
