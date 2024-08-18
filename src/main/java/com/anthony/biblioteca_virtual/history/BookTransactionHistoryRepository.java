package com.anthony.biblioteca_virtual.history;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookTransactionHistoryRepository extends JpaRepository<BookTransactionHistory, Integer> {

    @Query("""
            SELECT history FROM BookTransactionHistory history
            WHERE history.user.id = :id
            """)
    Page<BookTransactionHistory> findAllBorrowedBooks(Integer id, Pageable pageable);

    @Query("""
            SELECT history FROM BookTransactionHistory history
            WHERE history.book.owner.id = :id
            """)
    Page<BookTransactionHistory> findAllReturnedBooks(Integer id, Pageable pageable);

    @Query("""
            SELECT (COUNT(*) > 0) AS isBorrowed FROM BookTransactionHistory history
            WHERE history.user.id = :userId
            AND history.book.id = :id
            AND history.returnApproved = false
            """)
    boolean isBookAlreadyBorrowedByUser(Integer id, Integer userId);

    @Query("""
            SELECT transaction 
            FROM BookTransactionHistory transaction
            WHERE transaction.user.id = :userid
            AND transaction.book.id = :bookid
            AND transaction.returned = false
            AND transaction.returnApproved = false
            """)
    Optional<BookTransactionHistory> findByBookIdAndUserId(@Param("bookid") Integer bookid, @Param("userid") Integer userid);

    @Query("""
            SELECT transaction 
            FROM BookTransactionHistory transaction
            WHERE transaction.book.owner.id = :userid
            AND transaction.book.id = :bookid
            AND transaction.returned = true
            AND transaction.returnApproved = false
            """)
    Optional<BookTransactionHistory> findByBookIdAndOwnerId(@Param("bookid") Integer bookid,@Param("userid") Integer id);
}
