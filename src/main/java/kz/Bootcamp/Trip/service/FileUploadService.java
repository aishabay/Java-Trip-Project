package kz.Bootcamp.Trip.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {

    boolean uploadAva(MultipartFile file);
    byte[] getAvatar(String token) throws IOException;

}
