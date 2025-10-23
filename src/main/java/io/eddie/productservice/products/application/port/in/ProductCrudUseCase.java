package io.eddie.productservice.products.application.port.in;

import io.eddie.productservice.products.domain.model.Product;

import io.eddie.productservice.products.domain.vo.ProductSpecification;
import io.eddie.productservice.products.domain.vo.pagination.PageQuery;
import io.eddie.productservice.products.domain.vo.pagination.PageResult;

import java.util.Optional;

public interface ProductCrudUseCase {

    Product enroll(ProductSpecification specification);

    Optional<Product> getProductByCode(String code);

    PageResult<Product> findAll(PageQuery pageQuery);

    Product updateSpecification(String code, ProductSpecification specification);

}
