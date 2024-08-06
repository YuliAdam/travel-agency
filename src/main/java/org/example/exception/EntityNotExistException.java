package org.example.exception;

public class EntityNotExistException extends RuntimeException{
    public EntityNotExistException(String message){
        super(message);
    }
}
