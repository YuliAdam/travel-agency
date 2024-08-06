package org.example.exception;

public class LoginJustExistException extends RuntimeException {

    public LoginJustExistException(String message) {
        super(message);
    }
}
