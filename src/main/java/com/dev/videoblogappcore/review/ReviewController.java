package com.dev.videoblogappcore.review;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService service;

    @PostMapping("/create")
    public ResponseEntity<Object> createReview(
            @RequestBody ReviewDTO request
    ){
        service.createReview(request);
        return ResponseEntity.status(201).body("Review creada satisfactoriamente");
    }
}
