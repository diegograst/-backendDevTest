package es.diegogr.similarProducts.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionGlobalResponse {

	private static final Logger LOG = LoggerFactory.getLogger(ExceptionGlobalResponse.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception exception) {

		return this.manageException(exception);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException exception) {

		return this.manageException(exception);
	}

	private ResponseEntity<ErrorResponse> manageException(Exception exception) {

		LOG.error("Uncontrolled error", exception);

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
		errorResponse.setErrorDescription(exception.getMessage());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}

}