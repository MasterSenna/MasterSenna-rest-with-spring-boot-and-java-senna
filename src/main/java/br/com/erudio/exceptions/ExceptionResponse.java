package br.com.erudio.exceptions;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date timestamp;
	private String message;
	private String details;

	// Construtor para inicializar os campos da classe
	public ExceptionResponse(Date timestamp, String message, String details) {
		this.timestamp = timestamp; // Data e hora em que ocorreu a exceção
		this.message = message;    // Mensagem de erro da exceção
		this.details = details;    // Detalhes adicionais sobre a exceção
	}

	// Métodos getters para acessar os campos privados
	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
}
