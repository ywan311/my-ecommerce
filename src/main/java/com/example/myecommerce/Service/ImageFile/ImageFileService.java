package com.example.myecommerce.Service.ImageFile;

import com.example.myecommerce.Domain.ImageFile.ImageFile;
import com.example.myecommerce.Domain.ImageFile.ImageFileRepository;
import com.example.myecommerce.config.FileUploadProperties;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageFileService{
    @Autowired
    private ImageFileRepository imageFileRepository;
    private final Path fileLocation;
    @Autowired
    public ImageFileService(FileUploadProperties prop) throws Exception{
        System.out.println(prop);
        this.fileLocation = Paths.get(prop.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileLocation);
        }catch(Exception e) {
            throw new FileUploadException("파일을 업로드할 디렉토리를 생성하지 못했습니다.", e);
        }
    }

    public Long save(MultipartFile file) throws FileUploadException {
        String fileName = StringUtils.getFilename(file.getOriginalFilename());
        try {
            if(fileName.contains(".."))throw new FileUploadException();

            Path targetLocation  = this.fileLocation.resolve(fileName);
            Files.copy(file.getInputStream(),targetLocation, StandardCopyOption.REPLACE_EXISTING);
            ImageFile imageFile = ImageFile.builder().fileName(fileName).size(file.getSize()).mimeType(file.getContentType()).build();
            return imageFileRepository.save(imageFile).getId();
        }catch (Exception err){
            throw new FileUploadException(err);
        }
    }
    public List<ImageFile> getList(){
        return imageFileRepository.findAll();
    }
//    public List<Long> saveFiles(List<MultipartFile> files){
//         return
//
//    }



}
