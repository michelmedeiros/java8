package br.com.java8.utils;

import br.com.java8.model.Grupo;

public class GrupoDtoToEntityConverter implements ConverterObject<GrupoDTO, Grupo>{
	
	@Override
	public Grupo apply(final GrupoDTO dto) {
		final Grupo grupo = new Grupo();
		grupo.setId(dto.getId());
		grupo.setDescricao(dto.getDescricao());
        return grupo;
	}

}
