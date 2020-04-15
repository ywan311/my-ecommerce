package com.example.myecommerce.Domain.ImageFile;

import com.example.myecommerce.Domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class ImageFile extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String fileName;

    @Column
    private long size;

    @Column
    private String mimeType;

    @Builder
    public ImageFile(String fileName, long size, String mimeType) {
        this.fileName = fileName;
        this.size = size;
        this.mimeType = mimeType;
    }
}
