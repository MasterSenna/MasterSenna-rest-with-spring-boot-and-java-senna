package br.com.erudio.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import br.com.erudio.controllers.PersonController;
import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;

@Service
public class PersonServices {

	// Logger para registrar mensagens de log
	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	// Injeção de dependência do repositório de pessoas
	@Autowired
	PersonRepository repository;

	// Método para buscar todas as pessoas
	public List<PersonVO> findAll() {
		// Registro de mensagem de log
		logger.info("Finding all people!");

		// Busca todas as pessoas do banco de dados e mapeia para a lista de objetos PersonVO
		var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);

		// Adiciona links HATEOAS para cada objeto PersonVO
		persons.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));

		return persons;
	}

	// Método para buscar uma pessoa pelo ID
	public PersonVO findById(Long id) {
		// Registro de mensagem de log
		logger.info("Finding one person!");

		// Busca uma pessoa pelo ID no banco de dados
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

		// Mapeia a entidade Person para um objeto PersonVO
		var vo = DozerMapper.parseObject(entity, PersonVO.class);

		// Adiciona link HATEOAS para o objeto PersonVO
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());

		return vo;
	}

	// Método para criar uma nova pessoa
	public PersonVO create(PersonVO person) {
		// Verifica se o objeto PersonVO é nulo e lança uma exceção, se necessário
		if (person == null) throw new RequiredObjectIsNullException();

		// Registro de mensagem de log
		logger.info("Creating one person!");

		// Converte o objeto PersonVO para a entidade Person e salva no banco de dados
		var entity = DozerMapper.parseObject(person, Person.class);
		var vo =  DozerMapper.parseObject(repository.save(entity), PersonVO.class);

		// Adiciona link HATEOAS para o objeto PersonVO criado
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());

		return vo;
	}

	// Método para atualizar uma pessoa existente
	public PersonVO update(PersonVO person) {
		// Verifica se o objeto PersonVO é nulo e lança uma exceção, se necessário
		if (person == null) throw new RequiredObjectIsNullException();

		// Registro de mensagem de log
		logger.info("Updating one person!");

		// Busca a pessoa pelo ID no banco de dados
		var entity = repository.findById(person.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

		// Atualiza os dados da pessoa com os valores do objeto PersonVO recebido
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());

		// Salva a entidade Person atualizada no banco de dados
		var vo =  DozerMapper.parseObject(repository.save(entity), PersonVO.class);

		// Adiciona link HATEOAS para o objeto PersonVO atualizado
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());

		return vo;
	}

	// Método para excluir uma pessoa pelo ID
	public void delete(Long id) {
		// Registro de mensagem de log
		logger.info("Deleting one person!");

		// Busca a pessoa pelo ID no banco de dados e a exclui
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		repository.delete(entity);
	}
}
