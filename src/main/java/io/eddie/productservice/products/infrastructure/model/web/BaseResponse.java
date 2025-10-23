package io.eddie.productservice.products.infrastructure.model.web;

public record BaseResponse<T>(
        T data,
        String message
) {
}
