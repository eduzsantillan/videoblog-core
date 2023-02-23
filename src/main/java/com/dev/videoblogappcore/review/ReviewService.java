package com.dev.videoblogappcore.review;

import com.dev.videoblogappcore.videoblog.VideoBlog;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MongoTemplate mongoTemplate;

    public void createReview(ReviewDTO dto){

        ObjectId objectId = new ObjectId(dto.getVideoBlogId());
        Review review = reviewRepository.insert(dto.toEntity());

        mongoTemplate.update(VideoBlog.class)
                .matching(Criteria.where("_id").is(objectId))
                .apply(new Update().push("reviewIds",review.getId()))
                .first();
    }

}
