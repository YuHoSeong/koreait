package com.koreait.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.koreait.board.common.constant.ApiMappingPattern;
import com.koreait.board.service.FileService;

@RestController
@RequestMapping(ApiMappingPattern.FILE)
public class FileController {
    
    private static final String UPLOAD = "/upload";
    private static final String GET_IMAGE_FILE = "/image/{imageName}";
     

    @Autowired
    private FileService fileService;

    //^ File upload(로컬저장소)
    @PostMapping(UPLOAD)
    public String upload(@RequestParam("file") MultipartFile file){
        String fileUrl= fileService.upload(file);
        return fileUrl;
    }

    //^ IMAGE 출력
    @GetMapping(value=GET_IMAGE_FILE,produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public Resource getImageFile(@PathVariable("imageName") String imageName){
        Resource resource = fileService.getImageFile(imageName);
        return resource;
    }
}
