package io.eddie.productservice.products.infrastructure.model.persistence;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "products")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    @Setter
    private String name;

    @Setter
    private String description;

    @Setter
    private Long price;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Builder
    public ProductEntity(String code, String name, String description, Long price) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public void updateClock() {
        this.updatedAt = LocalDateTime.now();
    }

}
