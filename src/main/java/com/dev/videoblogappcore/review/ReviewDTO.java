package com.dev.videoblogappcore.review;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewDTO {
    private String videoBlogId;
    private String body;

    public Review toEntity(String user){
        return Review.builder()
                .body(this.body)
                .username(user)
                .build();
    }
}
