package com.anthony.biblioteca_virtual.feedback;

import com.anthony.biblioteca_virtual.User.User;
import com.anthony.biblioteca_virtual.book.Book;
import com.anthony.biblioteca_virtual.book.BookRepository;
import com.anthony.biblioteca_virtual.exception.OperationNotPermittedException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FeedBackService {

    private final FeedBackMapper feedBackMapper;
    private final FeedBackRepository feedBackRepository;
    private final BookRepository bookRepository;
    public Integer saveFeedback(FeedBackRequest request, Authentication connectedUser) {
        Book book = bookRepository.findById(request.bookId())
                .orElseThrow(() -> new EntityNotFoundException("Book not found with he ID: " + request.bookId()));

        if(book.isArchived() ||!book.isShareable()) {
            throw new OperationNotPermittedException("You cannot give feedback to this book");
        }
        User user = ((User) connectedUser.getPrincipal());

        if (Objects.equals(book.getOwner().getId(),user.getId())) {
            throw new OperationNotPermittedException("You cannot give feedback to your own book");
        }
        FeedBack feedBack = feedBackMapper.toFeedBack(request);
        return feedBackRepository.save(feedBack).getId();
        //TODO
    }
}
