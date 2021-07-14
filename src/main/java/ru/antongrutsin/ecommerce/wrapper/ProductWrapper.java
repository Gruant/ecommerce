package ru.antongrutsin.ecommerce.wrapper;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import ru.antongrutsin.ecommerce.domain.Category;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ProductWrapper implements Serializable {
    private MultipartFile image;
    private String name;
    private String description;
    private Double cost;
    private String category;
}
