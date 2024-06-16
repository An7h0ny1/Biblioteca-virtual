package com.anthony.biblioteca_virtual.feedback;

import com.anthony.biblioteca_virtual.book.Book;

public class FeedBackMapper {
    public FeedBack toFeedBack(FeedBackRequest request) {

        return FeedBack.builder()
                .rating(request.note())
                .comment(request.comment())
                .book(Book.builder().
                        id(request.bookId())
                        .shareable(true)
                        .archived(false)
                        .build())
                .build();
    }
}
