package com.dev.videoblogappcore.rating;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "rating")
public class Rating {
    @Id
    private ObjectId id;
    private int rating;
    private String username;
}
