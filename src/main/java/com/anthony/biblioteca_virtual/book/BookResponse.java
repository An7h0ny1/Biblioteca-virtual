package com.anthony.biblioteca_virtual.book;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponse {

    private Integer id;
    private String title;
    private String author;
    private String isbn;
    private String synopsis;
    private Boolean shareable;
    private  String owner;
    private  byte[] bookCover;
    private double archived;
    private double rating;

}
