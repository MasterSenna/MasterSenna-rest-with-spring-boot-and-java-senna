		package br.com.erudio.config;

		import java.util.List;

		import org.springframework.context.annotation.Configuration;
		import org.springframework.http.MediaType;
		import org.springframework.http.converter.HttpMessageConverter;
		import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
		import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

		import br.com.erudio.serialization.converter.YamlJackson2HttpMesageConverter;

		@Configuration
		public class WebConfig implements WebMvcConfigurer{

			private static final MediaType MEDIA_TYPE_APPLICATION_YML = MediaType.valueOf("application/x-yaml");

			@Override
			public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
				converters.add(new YamlJackson2HttpMesageConverter());
			}

			// Configuração de negociação de conteúdo para determinar como o Spring MVC escolherá o tipo de mídia
			// para a resposta com base nas preferências do cliente.
			// Aqui estamos configurando para aceitar JSON, XML e YAML.
			@Override
			public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
				// https://www.baeldung.com/spring-mvc-content-negotiation-json-xml
				// Via EXTENSION. http://localhost:8080/api/person/v1.xml DEPRECATED on SpringBoot 2.6

				// Via QUERY PARAM. http://localhost:8080/api/person/v1?mediaType=xml
				/*
				configurer.favorParameter(true)
					.parameterName("mediaType").ignoreAcceptHeader(true)
					.useRegisteredExtensionsOnly(false)
					.defaultContentType(MediaType.APPLICATION_JSON)
						.mediaType("json", MediaType.APPLICATION_JSON)
						.mediaType("xml", MediaType.APPLICATION_XML);
				*/

				// Via HEADER PARAM. http://localhost:8080/api/person/v1

				configurer.favorParameter(false) // Não prioriza o parâmetro
						.ignoreAcceptHeader(false) // Não ignora o cabeçalho de aceitação
						.useRegisteredExtensionsOnly(false) // Permite o uso de extensões registradas
						.defaultContentType(MediaType.APPLICATION_JSON) // Define JSON como o tipo de conteúdo padrão
						.mediaType("json", MediaType.APPLICATION_JSON) // Define JSON como tipo de mídia para a extensão "json"
						.mediaType("xml", MediaType.APPLICATION_XML) // Define XML como tipo de mídia para a extensão "xml"
						.mediaType("x-yaml", MEDIA_TYPE_APPLICATION_YML); // Define YAML como tipo de mídia para a extensão "x-yaml"
			}

		}
