package io.github.g4lowy.error;


public class DomainValidationException extends Exception{

    public DomainValidationException(String message) {
        super(message);
    }
}
