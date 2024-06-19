package com.anthony.biblioteca_virtual.feedback;

import com.anthony.biblioteca_virtual.book.Book;

import java.util.Objects;

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

    public Object toFeedBackResponse(FeedBack feedBack, Integer id) {
        return FeedBackResponse.builder()
                .rating(feedBack.getRating())
                .comment(feedBack.getComment())
                .ownFeedback(Objects.equals(feedBack.getCreatedBy(), id))
                .build();
    }
}
