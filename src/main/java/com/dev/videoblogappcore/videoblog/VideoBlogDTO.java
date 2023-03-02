package com.dev.videoblogappcore.videoblog;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VideoBlogDTO {
    private String title;
    private String description;
    private String urlVideo;


    public VideoBlog toEntity(String user){
        return VideoBlog.builder()
                .title(this.title)
                .description(this.description)
                .username(user)
                .urlVideo(this.urlVideo)
                .build();
    }

}
