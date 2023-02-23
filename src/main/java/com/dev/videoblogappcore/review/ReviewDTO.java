package com.dev.videoblogappcore.review;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewDTO {
    private String videoBlogId;
    private String body;
    private String username;

    public Review toEntity(){
        return Review.builder()
                .body(this.body)
                .username(this.username)
                .build();
    }
}
