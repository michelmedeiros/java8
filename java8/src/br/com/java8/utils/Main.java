package br.com.java8.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.java8.model.Grupo;
import br.com.java8.model.Usuario;

public class Main {

	 
	public static void main(String[] args) {
		
		List<UsuarioDTO> dtosUsuario = obterUsuariosDTO();
		List<Usuario> usuarios = testUsuarioConverters(dtosUsuario);
		
		
		List<GrupoDTO> dtosGrupo = dtosUsuario.stream().map(UsuarioDTO :: getGrupo).collect(Collectors.toList());
		List<Grupo> grupos = testGrupoConverters(dtosGrupo);
		
		usuarios.forEach(System.out::println);
		grupos.forEach(System.out::println);
	}
	
	private static List<UsuarioDTO> obterUsuariosDTO() {

		List<UsuarioDTO> usuariosDTO =  new ArrayList<UsuarioDTO>();
		UsuarioDTO usuarioDto = new UsuarioDTO();
		usuarioDto.setNome("Michel");
		usuarioDto.setPontos(100);
		GrupoDTO grupoDto = new GrupoDTO();
		grupoDto.setId(1);
		grupoDto.setDescricao("GrupoA");
		usuarioDto.setGrupo(grupoDto);
		usuariosDTO.add(usuarioDto);
		
		return usuariosDTO;
	}

	public static List<Usuario> testUsuarioConverters(final List<UsuarioDTO> dtos) {
		 UsuarioDtoToEntityConverter converterUser = new UsuarioDtoToEntityConverter();
         return converterUser.convertToList(dtos);
	}
	
	public static List<Grupo> testGrupoConverters(final List<GrupoDTO> dtos) {
		 GrupoDtoToEntityConverter converterGrupo = new GrupoDtoToEntityConverter();
        return converterGrupo.convertToList(dtos);
	}
	
}
