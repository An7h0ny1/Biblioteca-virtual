package com.anthony.biblioteca_virtual.history;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookTransactionnHistoryRepository extends JpaRepository<BookTransactionHistory, Integer> {

    @Query("""
            SELECT history FROM BookTransactionHistory history
            WHERE history.user.id = id
            """)

    Page<BookTransactionHistory> findAllBorrowedBooks(Integer id, Pageable pageable);

    @Query("""
            SELECT history FROM BookTransactionHistory history
            WHERE history.book.owner.id = id
            """)
    Page<BookTransactionHistory> findAllReturnedBooks(Integer id, Pageable pageable);
}
