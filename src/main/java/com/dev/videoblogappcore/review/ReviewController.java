package com.dev.videoblogappcore.review;

import com.dev.videoblogappcore.configuration.JwtService;
import com.dev.videoblogappcore.exceptions.VideoBlogException;
import com.dev.videoblogappcore.videoblog.VideoBlogDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService service;
    private final JwtService jwtService;



    @PostMapping("/create")
    public ResponseEntity<Object> createReview(
            @RequestBody ReviewDTO request,
            @RequestHeader("Authorization") String authorization
    ){
        try{
            service.createReview(request,authorization);
            return ResponseEntity.status(201).body("Review creada satisfactoriamente");
        }catch (VideoBlogException e){
            return ResponseEntity.status(e.getCode()).body(e.getMessage());
        }
    }


}
