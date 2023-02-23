package com.dev.videoblogappcore.videoblog;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoBlogRepository extends MongoRepository<VideoBlog, ObjectId> {

    public List<VideoBlog> getVideoBlogsByTitleContaining(String title);
    public List<VideoBlog> getVideoBlogByUsername(String username);


}
