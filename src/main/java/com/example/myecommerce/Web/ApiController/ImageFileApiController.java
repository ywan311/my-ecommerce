package com.example.myecommerce.Web.ApiController;

import com.example.myecommerce.Domain.ImageFile.ImageFile;
import com.example.myecommerce.Service.ImageFile.ImageFileService;
import com.example.myecommerce.Web.Dto.File.ProductFileUploadReq;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ImageFileApiController {
    private final ImageFileService imageFileService;

    @PostMapping("/api/v1/file")
    public Long save(@RequestParam("file") MultipartFile file) throws Exception {
        return imageFileService.save(file);
    }

    @GetMapping("/api/v1/files")
    public List<ImageFile> getList() {
        return imageFileService.getList();
    }

    @PostMapping("/api/v1/files")
    public List<Serializable> saveFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> {
                    try {
                        return imageFileService.save(file);
                    } catch (FileUploadException e) {
                        return e;
                    }
                }).collect(Collectors.toList());
    }

    @PostMapping("/api/v1/file/{product_id}/product")
    public Long productFile(@RequestParam("file") MultipartFile file, @PathVariable("product_id") Long id) throws Exception {
        return imageFileService.saveProductFile(id,file);
    }
}
