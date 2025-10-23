package io.eddie.productservice.products.application.port.out;

import io.eddie.productservice.products.domain.model.Product;
import io.eddie.productservice.products.domain.vo.pagination.PageQuery;
import io.eddie.productservice.products.domain.vo.pagination.PageResult;

import java.util.Optional;

public interface ProductPersistencePort {

    Product save(Product product);

    Optional<Product> getProductByCode(String code);

    PageResult<Product> findAll(PageQuery pageQuery);

}
