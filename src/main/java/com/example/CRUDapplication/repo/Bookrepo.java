package com.example.CRUDapplication.repo;

import com.example.CRUDapplication.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Bookrepo extends JpaRepository<Book, Long> {


}
