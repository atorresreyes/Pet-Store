package pet.store.controller.error;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;


@RestControllerAdvice
@Slf4j
public class GlobalErrorHandler {
	
	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public Map<String, String> handleNoSuchElementException(NoSuchElementException ex) {
		log.info("No such element found.");
		
		Map<String, String> exceptionMap = new HashMap<String, String>();
		
		exceptionMap.put("message", ex.toString());
		
		return exceptionMap;
	}
	
	@ExceptionHandler(IllegalStateException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public Map<String, String> handleIllegalStateException(IllegalStateException ex) {
		log.info("Input does not match.");
		
		Map<String, String> exceptionMap = new HashMap<String, String>();
		
		exceptionMap.put("message", ex.toString());
		
		return exceptionMap;
	}
	
}
