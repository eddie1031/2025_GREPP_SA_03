package io.eddie.productservice.products.domain.exception;

public class CouldNotFindProductException extends RuntimeException {

    public CouldNotFindProductException() {
        super();
    }

    public CouldNotFindProductException(String message) {
        super(message);
    }

    public CouldNotFindProductException(String message, Throwable cause) {
        super(message, cause);
    }

}
