package br.com.erudio.exceptions.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.erudio.exceptions.ExceptionResponse;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.exceptions.ResourceNotFoundException;

@ControllerAdvice // Marca esta classe como um manipulador de exceções global
@RestController // Todos os métodos desta classe retornarão objetos ResponseEntity
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	// Método para lidar com exceções genéricas
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
		// Cria um objeto ExceptionResponse com detalhes da exceção
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), // Data e hora da ocorrência da exceção
				ex.getMessage(), // Mensagem de erro da exceção
				request.getDescription(false) // Descrição da solicitação que causou a exceção
		);
		// Retorna uma resposta HTTP 500 (Internal Server Error) com os detalhes da exceção
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// Método para lidar com exceções ResourceNotFoundException
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleNotFoundExceptions(Exception ex, WebRequest request) {
		// Cria um objeto ExceptionResponse com detalhes da exceção
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), // Data e hora da ocorrência da exceção
				ex.getMessage(), // Mensagem de erro da exceção
				request.getDescription(false) // Descrição da solicitação que causou a exceção
		);
		// Retorna uma resposta HTTP 404 (Not Found) com os detalhes da exceção
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	// Método para lidar com exceções RequiredObjectIsNullException
	@ExceptionHandler(RequiredObjectIsNullException.class)
	public final ResponseEntity<ExceptionResponse> handleBadRequestExceptions(Exception ex, WebRequest request) {
		// Cria um objeto ExceptionResponse com detalhes da exceção
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), // Data e hora da ocorrência da exceção
				ex.getMessage(), // Mensagem de erro da exceção
				request.getDescription(false) // Descrição da solicitação que causou a exceção
		);
		// Retorna uma resposta HTTP 400 (Bad Request) com os detalhes da exceção
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}
