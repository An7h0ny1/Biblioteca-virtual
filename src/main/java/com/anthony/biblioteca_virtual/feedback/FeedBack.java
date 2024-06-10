package com.anthony.biblioteca_virtual.feedback;

import com.anthony.biblioteca_virtual.book.Book;
import com.anthony.biblioteca_virtual.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FeedBack  extends BaseEntity {

    private Double rating; // 1 to 5 stars

    private String comment;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

}
