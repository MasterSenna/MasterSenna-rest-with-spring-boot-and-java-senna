package br.com.erudio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Anotação para definir o status HTTP 400 (BAD_REQUEST) quando essa exceção é lançada
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredObjectIsNullException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	// Construtor padrão que define a mensagem de erro padrão
	public RequiredObjectIsNullException() {
		super("It is not allowed to persist a null object!");
	}

	// Construtor alternativo que permite definir uma mensagem de erro personalizada
	public RequiredObjectIsNullException(String ex) {
		super(ex);
	}

}