package com.online.edu.eduservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    String uploadFile(MultipartFile file,String host) throws IOException;
}
