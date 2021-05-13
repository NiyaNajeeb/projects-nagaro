package com.nagaro.code.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice

public class ControllerAdvisor extends ResponseEntityExceptionHandler{
	 @ExceptionHandler(AccountIDNotFoundException.class)
	    public ResponseEntity<Object> handleAccountIDNotFoundException(
	    		AccountIDNotFoundException ex, WebRequest request) {

	        Map<String, Object> body = new LinkedHashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("message", "AccountID  not found");

	        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	    }
	 @ExceptionHandler(AccessDeniedException.class)
	    public @ResponseBody ResponseEntity<Object> handlerAccessDeniedException(final Exception ex,
	            final HttpServletRequest request, final HttpServletResponse response) {

	       
	        Map<String, Object> body = new LinkedHashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("message", "Access denied");

	        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
	 }
	 @ExceptionHandler(MandatoryDataNotPresent.class)
	    public @ResponseBody ResponseEntity<Object> handlerMandatoryDataNotPresentException(final Exception ex,
	            final HttpServletRequest request, final HttpServletResponse response) {

	       
	        Map<String, Object> body = new LinkedHashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("message", ex.getMessage());

	        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
	 }
}
