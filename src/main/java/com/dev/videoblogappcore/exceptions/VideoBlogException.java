package com.dev.videoblogappcore.exceptions;

import lombok.Data;

@Data
public class VideoBlogException extends RuntimeException{

    private final int code;
    private final String message;

    public VideoBlogException(int codeResponse,String message){
        this.code = codeResponse;
        this.message= message;
    }
}
