package br.com.erudio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.services.PersonServices;
import br.com.erudio.util.MediaType;

@RestController // Indica que esta classe é um controlador REST
@RequestMapping("/api/person/v1") // Mapeia o caminho base para todas as requisições feitas a este controlador
public class PersonController {

	@Autowired // Injeção de dependência da classe PersonServices
	private PersonServices service;

	// Mapeia requisições GET para o método findAll(), que retorna todos os registros de pessoas
	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
	public List<PersonVO> findAll() {
		return service.findAll();
	}

	// Mapeia requisições GET com um parâmetro de caminho (id)
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  })
	public PersonVO findById(@PathVariable(value = "id") Long id) {
		return service.findById(id);
	}

	// Mapeia requisições POST para o método create(), que cria um novo registro de pessoa
	@PostMapping(consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML },
			produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
	public PersonVO create(@RequestBody PersonVO person) {
		return service.create(person);
	}

	// Mapeia requisições PUT para o método update(), que atualiza um registro de pessoa existente
	@PutMapping(consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML },
			produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
	public PersonVO update(@RequestBody PersonVO person) {
		return service.update(person);
	}

	// Mapeia requisições DELETE para o método delete(), que exclui um registro de pessoa existente
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build(); // Retorna uma resposta HTTP com status 204 (No Content)
	}
}
