package com.dev.videoblogappcore.videoblog;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VideoBlogDTO {
    private String title;
    private String description;
    private String username;
    private String urlVideo;


    public VideoBlog toEntity(){
        return VideoBlog.builder()
                .title(this.title)
                .description(this.description)
                .username(this.username)
                .urlVideo(this.urlVideo)
                .build();
    }

}
