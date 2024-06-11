package com.anthony.biblioteca_virtual.book;

import com.anthony.biblioteca_virtual.User.User;
import com.anthony.biblioteca_virtual.common.BaseEntity;
import com.anthony.biblioteca_virtual.feedback.FeedBack;
import com.anthony.biblioteca_virtual.history.BookTransactionHistory;
import jakarta.persistence.*;
import jdk.dynalink.linker.LinkerServices;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book  extends BaseEntity {


    private String title;
    private String author;
    private String isbn;
    private String synopsis;
    private String bookCover;
    private boolean archived;
    private boolean shareable;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @OneToMany(targetEntity = FeedBack.class, mappedBy = "book")
    private List<FeedBack> feedbacks;

    @OneToMany(targetEntity = BookTransactionHistory.class, mappedBy = "book")
    private List<BookTransactionHistory> bookTransactionHistories;

    @Transient
    public double getRating() {
        if(feedbacks == null || feedbacks.isEmpty()) {
            return 0;
        }
        return feedbacks.stream().mapToDouble(FeedBack::getRating).average().orElse(0);
    }


}