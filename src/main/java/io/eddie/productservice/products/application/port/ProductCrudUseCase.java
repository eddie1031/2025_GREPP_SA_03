package io.eddie.productservice.products.application.port;

import io.eddie.productservice.products.domain.model.Product;
import io.eddie.productservice.products.domain.vo.ProductSpecification;

import java.util.List;
import java.util.Optional;

public interface ProductCrudUseCase {

    Product enroll(ProductSpecification specification);

    Optional<Product> getProductByCode(String code);

    List<Product> findAll();

    Product updateSpecification(String code, ProductSpecification specification);

}
