package com.dev.videoblogappcore.videoblog;

import com.dev.videoblogappcore.exceptions.VideoBlogException;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoBlogService {


    private final VideoBlogRepository videoBlogRepository;

    public List<VideoBlog> getAllVideoBlog(){
        return videoBlogRepository.findAll();
    }

    public List<VideoBlog> getVideoBlogByTitle(String title){
        List<VideoBlog> videoBlogToFind = videoBlogRepository.getVideoBlogsByTitleContaining(title);

        if(videoBlogToFind.isEmpty()){
            throw new VideoBlogException(404,"No se encontro el video con el titulo :"+title);
        }else{
            return videoBlogToFind;
        }
        //Simplify code with Java Lambdas ()->
        //return videoBlogRepository.getVideoBlogByTitle(title)
        //                .orElseThrow(() -> new VideoBlogException(404, "No se encontro el video con el titulo :" + title));
    }

    public List<VideoBlog> getVideosBlogByUsername(String username){
        List<VideoBlog> videoBlogToFind = videoBlogRepository.getVideoBlogByUsername(username);
        if(videoBlogToFind.isEmpty()){
            throw new VideoBlogException(404,"No se encontro videos creados por el usuario :"+username);
        }else{
            return videoBlogToFind;
        }
    }



    public void createVideoBlog(VideoBlogDTO dto){
        if(!isRequestCompleted(dto)){
            throw new VideoBlogException(400,"Request no contiene los datos necesarios");
        }
        try{
            videoBlogRepository.save(dto.toEntity());
        }catch (Exception e)
        {
            throw new VideoBlogException(511,e.getMessage());
        }
    }


    public void deleteVideoBlog(String id){
        ObjectId objId = new ObjectId(id);
        Optional<VideoBlog> videoBlogToDelete = videoBlogRepository.findById(objId);
        if(videoBlogToDelete.isPresent()){
            videoBlogRepository.delete(videoBlogToDelete.get());
        }else{
            throw new VideoBlogException(400,"No se encontro un videoBlog con el id :" + id);
        }
    }


    private boolean isRequestCompleted(VideoBlogDTO request){
        return !request.getTitle().isBlank() &&
                !request.getDescription().isBlank() &&
                !request.getUrlVideo().isBlank();
    }







}
