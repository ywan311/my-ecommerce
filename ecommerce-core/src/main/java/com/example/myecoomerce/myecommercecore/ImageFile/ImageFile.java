package com.example.myecoomerce.myecommercecore.ImageFile;

import com.example.myecoomerce.myecommercecore.BaseTimeEntity;
import com.example.myecoomerce.myecommercecore.Product.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "product_id", nullable = false,foreignKey = @ForeignKey(name = "FK_IMAGE_FILE_PRODUCT"))
    private Product product;

    @Builder
    public ImageFile(String fileName, long size, String mimeType,Product product) {
        this.fileName = fileName;
        this.size = size;
        this.mimeType = mimeType;
        this.product = product;
    }
}
