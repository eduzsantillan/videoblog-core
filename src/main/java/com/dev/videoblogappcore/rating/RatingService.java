package com.dev.videoblogappcore.rating;

import com.dev.videoblogappcore.review.Review;
import com.dev.videoblogappcore.videoblog.VideoBlog;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    private final MongoTemplate mongoTemplate;

    public void rateVideoBlog(RatingDTO dto,String username){
        ObjectId objectId = new ObjectId(dto.getVideoBlogId());
        Rating rating = ratingRepository.insert(dto.toEntity(username));

        mongoTemplate.update(VideoBlog.class)
                .matching(Criteria.where("_id").is(objectId))
                .apply(new Update().push("ratingsIds",rating.getId()))
                .first();
    }

}
