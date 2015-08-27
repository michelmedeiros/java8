package br.com.java8.utils;

import java.util.ArrayList;
import java.util.List;

import br.com.java8.model.Usuario;

public class Main {

	 
	public static void main(String[] args) {
		List<UsuarioDTO> dtos = obterUsuariosDTO();
		List<Usuario> usuarios = testConverters(dtos);
		
		usuarios.forEach(System.out::println);
	}
	
	private static List<UsuarioDTO> obterUsuariosDTO() {

		List<UsuarioDTO> usuariosDTO =  new ArrayList<UsuarioDTO>();
		UsuarioDTO usuarioDto = new UsuarioDTO();
		usuarioDto.setNome("Michel");
		usuarioDto.setPontos(100);
		GrupoDTO grupoDto = new GrupoDTO();
		usuarioDto.setGrupo(grupoDto);
		usuariosDTO.add(usuarioDto);
		
		return usuariosDTO;
	}

	public static List<Usuario> testConverters(final List<UsuarioDTO> dtos) {
		 UsuarioDtoToEntityConverter converter = new UsuarioDtoToEntityConverter();
         return converter.convertToList(dtos);
	}
	
	
}
