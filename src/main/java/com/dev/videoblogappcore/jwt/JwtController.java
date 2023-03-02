package com.dev.videoblogappcore.jwt;


import com.dev.videoblogappcore.configuration.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class JwtController {


    private final  JwtService jwtService;






}
