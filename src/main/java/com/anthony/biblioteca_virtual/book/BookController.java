package com.anthony.biblioteca_virtual.book;

import com.anthony.biblioteca_virtual.common.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
}
