package com.dev.videoblogappcore.rating;

import com.dev.videoblogappcore.configuration.JwtService;
import com.dev.videoblogappcore.exceptions.VideoBlogException;
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
    private final JwtService jwtService;

    public void rateVideoBlog(RatingDTO dto,String authorization){
        try{
            String username = jwtService.getUserNameFromToken(authorization);
            Rating rating = ratingRepository.insert(dto.toEntity(username));

            mongoTemplate.update(VideoBlog.class)
                    .matching(Criteria.where("_id").is(dto.getVideoBlogId()))
                    .apply(new Update().push("ratingsIds",rating.getId()))
                    .first();
        }catch (Exception e) {
            throw new VideoBlogException(511, e.getMessage());
        }


    }

}
