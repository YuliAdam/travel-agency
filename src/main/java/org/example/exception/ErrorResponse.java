package org.example.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {
    private final String fieldName;
    private final String message;
    private final ErrorCode errorCode;
    @Override
    public String toString(){
        return "Field Name: "+fieldName+". Message: "+message;
    }
}
