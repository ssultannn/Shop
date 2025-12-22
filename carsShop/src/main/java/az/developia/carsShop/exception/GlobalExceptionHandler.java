package az.developia.carsShop.exception;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<?> handleResponseStatus(ResponseStatusException ex) {
		return ResponseEntity.status(ex.getStatusCode()).body(Map.of("timestamp", LocalDateTime.now(), "status",
				ex.getStatusCode().value(), "error", ex.getStatusCode().toString(), "message", ex.getReason()));
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handleRuntime(RuntimeException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("timestamp", LocalDateTime.now(), "status",
				400, "error", "Bad Request", "message", ex.getMessage()));
	}
	
	
}
