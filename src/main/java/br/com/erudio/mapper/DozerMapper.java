package br.com.erudio.mapper;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DozerMapper {

	// Instância compartilhada do Mapper do DozerBeanMapperBuilder
	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

	// Método para mapear um único objeto de origem para um objeto de destino
	public static <O, D> D parseObject(O origin, Class<D> destination) {
		return mapper.map(origin, destination);
	}

	// Método para mapear uma lista de objetos de origem para uma lista de objetos de destino
	public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
		List<D> destinationObjects = new ArrayList<D>();
		for (O o : origin) {
			destinationObjects.add(mapper.map(o, destination));
		}
		return destinationObjects;
	}

}
