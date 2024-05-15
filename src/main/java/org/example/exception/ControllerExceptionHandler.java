package org.example.exception;

import org.example.controller.request.UserRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ModelAndView validationConflict(Model model,MethodArgumentNotValidException exception) {
        final List<Violation> violations = exception.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(error.getField(),error.getDefaultMessage())).toList();
                model.addAttribute("errorMessage",new ValidationErrorResponse(violations).getViolationList().toString());
        return new ModelAndView("exceptionPage");
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ModelAndView dataValidationConflict(Model model,DataIntegrityViolationException exception) {
    model.addAttribute("errorMessage",exception.getCause().getCause());
        return new ModelAndView("exceptionPage");
    }
}