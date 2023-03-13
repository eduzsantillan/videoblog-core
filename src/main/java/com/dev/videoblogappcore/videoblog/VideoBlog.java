package com.dev.videoblogappcore.videoblog;

import com.dev.videoblogappcore.rating.Rating;
import com.dev.videoblogappcore.review.Review;
import lombok.Builder;
import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.List;

@Builder
@Data
@Document(collection="video-blog")
public class VideoBlog {

    @Id
    private String id;

    private String title;
    private String description;
    private String username;
    private String urlVideo;
    @DocumentReference
    private List<Review> reviewIds;
    @DocumentReference
    private List<Rating> ratingsIds;

}
