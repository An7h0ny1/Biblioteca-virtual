package com.anthony.biblioteca_virtual.book;

import com.anthony.biblioteca_virtual.common.PageResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@Tag(name = "Book")
public class BookController {

    private final BookService service;

    @PostMapping("/add")
    public ResponseEntity<Integer> addBook(@Valid @RequestBody BookRequest request, Authentication connectedUser) {

        return ResponseEntity.ok(service.save(request, connectedUser));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<PageResponse<BookResponse>> findAllBooks(@RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
                                                                   @RequestParam(name = "size", defaultValue = "10", required = false) Integer size, Authentication connectedUser) {
        return ResponseEntity.ok(service.findAllBooks(page, size, connectedUser));

    }

    @GetMapping("/owner")
    public ResponseEntity<PageResponse<BookResponse>> findAllBooksByOwner(@RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
                                                                         @RequestParam(name = "size", defaultValue = "10", required = false) Integer size, Authentication connectedUser) {
        return ResponseEntity.ok(service.findAllBooksByOwner(page, size, connectedUser));
    }

    @GetMapping("/borrowed")
    public ResponseEntity<PageResponse<BorrowedBookResponse>> findAllBorrowedBooks(@RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
                                                                          @RequestParam(name = "size", defaultValue = "10", required = false) Integer size, Authentication connectedUser) {
        return ResponseEntity.ok(service.findAllBorrowedBooks(page, size, connectedUser));
    }

    @GetMapping("/returned")
    public ResponseEntity<PageResponse<BorrowedBookResponse>> findAllReturnedBooks(@RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
                                                                                   @RequestParam(name = "size", defaultValue = "10", required = false) Integer size, Authentication connectedUser) {
        return ResponseEntity.ok(service.findAllReturnedBooks(page, size, connectedUser));
    }


    @PatchMapping("/shareable/{id}")
    public ResponseEntity<Integer> updateShareableStatus(@PathVariable Integer id, Authentication connectedUser) {
        return ResponseEntity.ok(service.updateShareableStatus(id, connectedUser));
    }

    @PatchMapping("/arachived/{id}")
    public ResponseEntity<Integer> updateArachivedStatus(@PathVariable Integer id, Authentication connectedUser) {
        return ResponseEntity.ok(service.updateArchivedStatus(id, connectedUser));
    }

    @PostMapping("/borrow/{id}")
    public ResponseEntity<Integer> borrowBook(@PathVariable Integer id, Authentication connectedUser) {
        return ResponseEntity.ok(service.borrowBook(id, connectedUser));
    }

    @PatchMapping("/borrow/return/{id}")
    public ResponseEntity<Integer> returnBorrowBook(@PathVariable Integer bookid, Authentication connectedUser) {
        return ResponseEntity.ok(service.returnBorrowBook(bookid, connectedUser));
    }

    @PatchMapping("/borrow/return/approved/{id}")
    public ResponseEntity<Integer> approveReturnBorrowBook(@PathVariable Integer bookid, Authentication connectedUser) {
        return ResponseEntity.ok(service.approveReturnBorrowBook(bookid, connectedUser));
    }

    @PostMapping(value = "cover/{id}", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadBookCoverPicture(@PathVariable Integer bookid, @Parameter() @RequestPart("file") MultipartFile MultipartFile, Authentication connectedUser) {
        service.uploadBookCoverPicture(bookid, MultipartFile, connectedUser);
        return ResponseEntity.accepted().build();
    }
}
