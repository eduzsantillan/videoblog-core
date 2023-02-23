package com.dev.videoblogappcore.review;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "review")
public class Review {
    @Id
    private ObjectId id;
    private String body;
    private String username;
}
