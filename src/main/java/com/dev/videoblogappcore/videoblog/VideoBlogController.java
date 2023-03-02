package com.dev.videoblogappcore.videoblog;

import com.dev.videoblogappcore.configuration.JwtService;
import com.dev.videoblogappcore.exceptions.VideoBlogException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/videoblog")
@RequiredArgsConstructor
public class VideoBlogController {

    private final VideoBlogService service;
    private final JwtService jwtService;


    @GetMapping({"/",""})
    public ResponseEntity<List<VideoBlog>> getAllVideoBlog(){
        return ResponseEntity.ok().body(service.getAllVideoBlog());
    }

    @GetMapping("/by-title")
    public ResponseEntity<Object> getVideoBlogByTitle(
            @RequestParam(name = "title", defaultValue = "", required = true) String title
    ){
        try{
            return ResponseEntity.ok().body(service.getVideoBlogByTitle(title));
        }catch (VideoBlogException e){
            return ResponseEntity.status(e.getCode()).body(e.getMessage());
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<Object> getVideosBlogByUsername(
            @PathVariable String username
    ){
        try{
            return ResponseEntity.ok().body(service.getVideosBlogByUsername(username));
        }catch (VideoBlogException e){
            return ResponseEntity.status(e.getCode()).body(e.getMessage());
        }
    }


    @PostMapping("/create")
    public ResponseEntity<String> createVideoBlog(
            @RequestBody VideoBlogDTO request,
            @RequestHeader("Authorization") String authorization
    ){
        try{
            String username = jwtService.validateToken(authorization);
            service.createVideoBlog(request,username);
            return ResponseEntity.status(201).body("Video blog creado satisfactoriamente");
        }catch (VideoBlogException e){
            return ResponseEntity.status(e.getCode()).body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVideoBlog(
            @PathVariable String id,
            @RequestHeader("Authorization") String authorization
    ){
        try{
            String username = jwtService.validateToken(authorization);
            service.deleteVideoBlog(id,username);
            return ResponseEntity.ok().body("Video blog borrado satisfactoriamente");
        }catch (VideoBlogException e){
            return ResponseEntity.status(e.getCode()).body(e.getMessage());
        }
    }

}
