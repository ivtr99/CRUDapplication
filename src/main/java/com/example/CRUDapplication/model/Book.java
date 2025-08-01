package com.example.CRUDapplication.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Books")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;


    private String author;


}
