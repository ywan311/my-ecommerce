package com.example.myecommerce.Web.Dto.File;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class ProductFileUploadReq {
    private Long id;
    private MultipartFile file;
}
