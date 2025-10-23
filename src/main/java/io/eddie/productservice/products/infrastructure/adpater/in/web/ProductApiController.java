package io.eddie.productservice.products.infrastructure.adpater.in.web;

import io.eddie.productservice.products.application.port.in.ProductCrudUseCase;
import io.eddie.productservice.products.domain.vo.ProductSpecification;
import io.eddie.productservice.products.infrastructure.mapper.ProductMapper;
import io.eddie.productservice.products.infrastructure.model.dto.ProductDescription;
import io.eddie.productservice.products.infrastructure.model.web.BaseResponse;
import io.eddie.productservice.products.infrastructure.model.web.ProductSpecificationRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductApiController {

    private final ProductCrudUseCase productCrudUseCase;

    @PostMapping
    public BaseResponse<ProductDescription> createProduct(
        @RequestBody @Valid ProductSpecificationRequest request
    ) {

        ProductSpecification specification = ProductMapper.toSpecification(request);

        return new BaseResponse<>(
                ProductMapper.toDescription(
                        productCrudUseCase.enroll(specification)
                )
                , "");
    }



}
