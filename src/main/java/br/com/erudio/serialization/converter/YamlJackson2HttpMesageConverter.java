package br.com.erudio.serialization.converter;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

// Classe que converte objetos Java para YAML usando Jackson
public class YamlJackson2HttpMesageConverter extends AbstractJackson2HttpMessageConverter {

	// Construtor da classe
	public YamlJackson2HttpMesageConverter() {
		// Chama o construtor da superclasse AbstractJackson2HttpMessageConverter
		// Passa um novo YAMLMapper como argumento, configurado para incluir propriedades nulas
		// Define o tipo de mídia como "application/x-yaml"
		super(new YAMLMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL),
				MediaType.parseMediaType("application/x-yaml"));
	}
}
