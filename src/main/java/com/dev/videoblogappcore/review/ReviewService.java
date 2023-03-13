package com.dev.videoblogappcore.review;

import com.dev.videoblogappcore.configuration.JwtService;
import com.dev.videoblogappcore.exceptions.VideoBlogException;
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
    private final JwtService jwtService;

    public void createReview(ReviewDTO dto,String authorization){

        try{
            String username = jwtService.getUserNameFromToken(authorization);
            Review review = reviewRepository.insert(dto.toEntity(username));

            mongoTemplate.update(VideoBlog.class)
                    .matching(Criteria.where("_id").is(dto.getVideoBlogId()))
                    .apply(new Update().push("reviewIds",review.getId()))
                    .first();
        }catch (Exception e) {
            throw new VideoBlogException(511, e.getMessage());
        }

    }

}
