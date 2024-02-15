package br.com.erudio.data.vo.v1;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

// Indica a ordem das propriedades ao serializar o objeto JSON
@JsonPropertyOrder({"id", "firstName", "lastName", "address", "gender"})
public class PersonVO extends RepresentationModel<PersonVO> implements Serializable {

	private static final long serialVersionUID = 1L;

	// Anotação para serializar a propriedade 'key' como 'id'
	@JsonProperty("id")
	// Anotação do Dozer para mapeamento de objetos
	@Mapping("id")
	private Long key;

	private String firstName;
	private String lastName;
	private String address;
	private String gender;

	// Construtor padrão
	public PersonVO() {}

	// Getters e setters para todas as propriedades

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	// Sobrescrita dos métodos hashCode() e equals() para comparação de objetos

}
