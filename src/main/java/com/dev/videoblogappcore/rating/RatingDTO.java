package com.dev.videoblogappcore.rating;

import com.dev.videoblogappcore.review.Review;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RatingDTO {
    private String videoBlogId;
    private int rating;

    public Rating toEntity(String user){
        return Rating.builder()
                .rating(this.rating)
                .username(user)
                .build();
    }
}