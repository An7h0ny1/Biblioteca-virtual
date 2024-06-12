package com.anthony.biblioteca_virtual.book;

import org.springframework.stereotype.Service;

@Service
public class BookMapper {

    public Book toBook(BookRequest request) {
        return Book.builder()
                .id(request.id())
                .title(request.title())
                .author(request.author())
                .isbn(request.isbn())
                .archived(false)
                .synopsis(request.synopsis())
                .shareable(request.shareable())
                .build();
    }

    public BookResponse toBookresponse(Book book) {
        BookResponse build = BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .synopsis(book.getSynopsis())
                .shareable(book.isShareable())
                .archived(book.isArchived())
                .rating(book.getRating())
                .owner(book.getOwner().fullName())
                //.bookCover(book.getBookCover())
                .build();
        return build;
    }
}
