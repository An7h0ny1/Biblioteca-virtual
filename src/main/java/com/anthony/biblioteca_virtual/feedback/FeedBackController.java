package com.anthony.biblioteca_virtual.feedback;

import com.anthony.biblioteca_virtual.common.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feedbacks")
@Tag(name = "FeedBack")
public class FeedBackController {
    private final FeedBackService service;

    @PostMapping
    public ResponseEntity<Integer> saveFeedback(@Valid @RequestBody FeedBackRequest request, Authentication connectedUser) {
        return ResponseEntity.ok(service.saveFeedback(request, connectedUser));
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<PageResponse<FeedBackResponse>> findAllFeedbacksByBook(@PathVariable Integer bookId, @RequestParam(name = "page", defaultValue = "0", required = false) Integer page, @RequestParam(name = "size", defaultValue = "10", required = false) Integer size, Authentication connectedUser) {
        return ResponseEntity.ok(service.findAllFeedbacksByBook(bookId, page, size, connectedUser));
    }
}
