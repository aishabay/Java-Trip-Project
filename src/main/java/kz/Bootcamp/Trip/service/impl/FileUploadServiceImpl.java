package kz.Bootcamp.Trip.service.impl;

import kz.Bootcamp.Trip.service.FileUploadService;
import kz.Bootcamp.Trip.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private UserService userService;

    @Value("${uploadURL}")
    private String uploadURL;

    @Value("${loadURL}")
    private String loadURL;

    @Override
    public boolean uploadAva(MultipartFile file) {
        try{
            if(file.getContentType().equals("image/png") || file.getContentType().equals("image/jpeg")){
                String fileName = DigestUtils.sha1Hex(userService.getCurrentUser().getId() + "_avatar");
                byte bytes[] = file.getBytes();
                Path path = null;
                if(file.getContentType().equals("image/png")){
                    path = Paths.get(uploadURL + fileName + ".png");
                } else if (file.getContentType().equals("image/jpeg")) {
                    path = Paths.get(uploadURL + fileName + ".jpg");
                }
                Files.write(path, bytes);
                userService.setUserAvatar(fileName);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public byte[] getAvatar(String token) throws IOException {

        String picURL = loadURL + "default.jpg";

        if(token!=null&&!token.equals("null")){
            picURL = loadURL + token + ".jpg";
        }

        InputStream in;

        try{

            ClassPathResource classPathResource =
                    new ClassPathResource(picURL);
            in = classPathResource.getInputStream();

        }catch(Exception e){

            ClassPathResource classPathResource =
                    new ClassPathResource(loadURL + "default.jpg");
            in = classPathResource.getInputStream();

            e.printStackTrace();
        }

        return IOUtils.toByteArray(in);
    }
}
