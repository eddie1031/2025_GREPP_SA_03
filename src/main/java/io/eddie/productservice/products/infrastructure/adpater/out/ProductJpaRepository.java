package io.eddie.productservice.products.infrastructure.adpater.out;

import io.eddie.productservice.products.infrastructure.model.persistence.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByCode(String code);

}
