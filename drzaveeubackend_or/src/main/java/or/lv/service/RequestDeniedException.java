package or.lv.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// ne vraćamo 500 nego 400 jer je greska klijenta
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequestDeniedException extends RuntimeException {
	public RequestDeniedException(String message) {
		super(message);
	}
}
