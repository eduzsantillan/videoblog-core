package com.dev.videoblogappcore.rating;

import com.dev.videoblogappcore.configuration.JwtService;
import com.dev.videoblogappcore.exceptions.VideoBlogException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/rating")
@AllArgsConstructor
public class RatingController {

    private final JwtService jwtService;
    private final RatingService service;


    @PostMapping("/rate")
    public ResponseEntity<Object> rateVideoBlog(
            @RequestBody RatingDTO request,
            @RequestHeader("Authorization") String authorization
    ){
        try{
            String username = jwtService.validateToken(authorization);
            service.rateVideoBlog(request,username);
            return ResponseEntity.status(201).body("Calificación exitosa");
        }catch (VideoBlogException e){
            return ResponseEntity.status(e.getCode()).body(e.getMessage());
        }
    }


}
