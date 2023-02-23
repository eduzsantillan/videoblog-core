package com.dev.videoblogappcore.videoblog;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoBlogRepository extends MongoRepository<VideoBlog, ObjectId> {
}
