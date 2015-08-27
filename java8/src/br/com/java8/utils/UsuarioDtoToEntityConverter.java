package br.com.java8.utils;

import br.com.java8.model.Usuario;

public class UsuarioDtoToEntityConverter implements ConverterObject<UsuarioDTO,Usuario>{
	
	@Override
	public Usuario apply(final UsuarioDTO dto) {
		final Usuario usuario = new Usuario();
		usuario.setNome(dto.getNome());
		usuario.setPontos(dto.getPontos());
        return usuario;
	}

}
