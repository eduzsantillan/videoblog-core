package com.dev.videoblogappcore.videoblog;

import com.dev.videoblogappcore.configuration.JwtService;
import com.dev.videoblogappcore.exceptions.VideoBlogException;
import com.google.firebase.auth.FirebaseAuthException;
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
    private final JwtService jwtService;

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



    public void createVideoBlog(VideoBlogDTO dto,String authorization){
        if(!isRequestCompleted(dto)){
            throw new VideoBlogException(400,"Request no contiene los datos necesarios");
        }
        try{
            String username = jwtService.getUserNameFromToken(authorization);
            videoBlogRepository.save(dto.toEntity(username));
        }catch (Exception e)
        {
            throw new VideoBlogException(511,e.getMessage());
        }
    }


    public void deleteVideoBlog(String id,String authorization){
        try {
            String username = jwtService.getUserNameFromToken(authorization);
            ObjectId objId = new ObjectId(id);
            Optional<VideoBlog> videoBlogToDelete = videoBlogRepository.findById(objId);
            if(videoBlogToDelete.isPresent()){

                if(videoBlogToDelete.get().getUsername().equals(username)){
                    videoBlogRepository.delete(videoBlogToDelete.get());
                }else{
                    throw  new VideoBlogException(403,
                            String.format("El usuario %s no tiene permisos para borrar el video %s. El video blog fue creado por %s",
                                    username,
                                    videoBlogToDelete.get().getTitle(),
                                    videoBlogToDelete.get().getUsername()));
                }
            }else{
                throw new VideoBlogException(400,"No se encontro un videoBlog con el id :" + id);
            }
        }catch (Exception e){
            throw new VideoBlogException(511,e.getMessage());
        }

    }


    private boolean isRequestCompleted(VideoBlogDTO request){
        return !request.getTitle().isBlank() &&
                !request.getDescription().isBlank() &&
                !request.getUrlVideo().isBlank();
    }







}
