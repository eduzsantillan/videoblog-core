package com.dev.videoblogappcore.videoblog;

import com.dev.videoblogappcore.rating.Rating;
import com.dev.videoblogappcore.review.Review;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Data
@Document(collection="video-blog")
public class VideoBlog {
    @Id
    private ObjectId id;
    private String tittle;
    private String description;
    private String username;
    private String urlVideo;
    @DocumentReference
    private List<Review> reviewIds;
    @DocumentReference
    private List<Rating> ratingsIds;

}
