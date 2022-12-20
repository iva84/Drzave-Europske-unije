package or.lv.rest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.HttpConstraint;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// sami formiramo odgovor
// svaki kontroler koji naleti na Exception provjerava ima li definiran exception handler za određenu iznimku

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice 
public class RestExceptionHandler {
	
	// http odgovor kada se dogodi IllegalArgumentException
	@ExceptionHandler(IllegalArgumentException.class)
	protected ResponseEntity<?> handleIllegalArgument(Exception e, WebRequest req) {
		Map<String, String> props = new HashMap<>();
		props.put("message", e.getMessage());
		props.put("status", "400");
		props.put("error", "Bad Request");
		return new ResponseEntity<>(props, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	protected ResponseEntity<?> handleNotFound(Exception e, WebRequest req) {
		Map<String, String> props = new HashMap<>();
		props.put("message", "Metoda za taj zahtjev nije implementirana! " + e.getMessage());
		props.put("status", "501");
		props.put("error", "Not Implemented");
		return new ResponseEntity<>(props, HttpStatus.NOT_IMPLEMENTED);
	}
	
}