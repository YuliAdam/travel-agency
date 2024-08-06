package org.example.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ModelAndView methodArgumentNotValidException(Model model, MethodArgumentNotValidException exception) {
        final List<ErrorResponse> errorResponses = exception.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErrorResponse(error.getField(), error.getDefaultMessage(), ErrorCode.VALIDATION_EXCEPTION)).toList();
        model.addAttribute("errorMessage", errorResponses.toString());
        return new ModelAndView("exceptionPage");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ModelAndView dataIntegrityViolationException(Model model, DataIntegrityViolationException exception) {
        final List<ErrorResponse> errorResponses = List.of(new ErrorResponse("field", exception.getMessage(), ErrorCode.FIELD_EXCEPTION));
        model.addAttribute("errorMessage", errorResponses.toString());
        return new ModelAndView("exceptionPage");
    }

    @ExceptionHandler({NoSuchElementException.class})
    public ModelAndView noSuchElementException(Model model, NoSuchElementException exception) {
        final List<ErrorResponse> errorResponses = List.of(new ErrorResponse("field", exception.getMessage(), ErrorCode.ENTITY_NON_EXIST));
        model.addAttribute("errorMessage", errorResponses.toString());
        return new ModelAndView("exceptionPage");
    }

    @ExceptionHandler({EntityNotExistException.class})
    public ModelAndView noSuchElementException(Model model, EntityNotExistException exception) {
        final List<ErrorResponse> errorResponses = List.of(new ErrorResponse("field", exception.getMessage(), ErrorCode.ENTITY_NON_EXIST));
        model.addAttribute("errorMessage", errorResponses.toString());
        return new ModelAndView("exceptionPage");
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ModelAndView UsernameNotFoundException(Model model, UsernameNotFoundException exception) {
        final List<ErrorResponse> errorResponses = List.of(new ErrorResponse("field", exception.getMessage(), ErrorCode.USERNAME_NOT_FOUND));
        model.addAttribute("errorMessage", errorResponses.toString());
        return new ModelAndView("exceptionPage");
    }

    @ExceptionHandler(FileStorageException.class)
    public ModelAndView FileStorageException(Model model, FileStorageException exception) {
        final List<ErrorResponse> errorResponses = List.of(new ErrorResponse("field", exception.getMessage(), ErrorCode.FILE_IS_NOT_STORE));
        model.addAttribute("errorMessage", errorResponses.toString());
        return new ModelAndView("exceptionPage");
    }

    @ExceptionHandler(BalanceException.class)
    public ModelAndView balanceException(Model model, BalanceException exception) {
        final List<ErrorResponse> errorResponses = List.of(new ErrorResponse("field", exception.getMessage(), ErrorCode.BALANCE_NOT_UPDATED));
        model.addAttribute("errorMessage", errorResponses.toString());
        return new ModelAndView("exceptionPage");
    }
}