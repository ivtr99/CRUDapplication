package com.example.CRUDapplication.controller;

import com.example.CRUDapplication.model.Book;
import com.example.CRUDapplication.repo.Bookrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
//sample
    @Autowired
    private Bookrepo bookRepo;

    //Get All books API @Vishnu
    @GetMapping("/getAllBooks")
    public ResponseEntity<List<Book>> getAllBooks() {
        try {
            List<Book> bookList = new ArrayList<>();
            bookRepo.findAll().forEach(bookList::add);

            if (bookList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(bookList, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/getBookBYId/{id}")
    public ResponseEntity<Book> getBookBYId(@PathVariable Long id){

        Optional<Book> bookData =bookRepo.findById(id);
        if (bookData.isPresent()){
            return new ResponseEntity<>(bookData.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addBook")
    public ResponseEntity<Book>  addBook(@RequestBody Book book){

        Book bookObj=bookRepo.save(book);

        return new ResponseEntity<>(bookObj, HttpStatus.OK);

    }


    @PostMapping("/updateBookById/{id}")
    public ResponseEntity<Book>  UpdateBookById(@PathVariable Long id,@RequestBody Book newBookData){
        Optional<Book> oldBookData = bookRepo.findById(id);

        if (oldBookData.isPresent()){
            Book updatedBookData = oldBookData.get();
            updatedBookData.setTitle(newBookData.getTitle());
            updatedBookData.setAuthor(newBookData.getAuthor());

            Book bookObj=bookRepo.save(updatedBookData);
            return new ResponseEntity<>(bookObj, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteBookById/{id}")
    public ResponseEntity<HttpStatus> deleteBookBYId(@PathVariable Long id){

        bookRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
