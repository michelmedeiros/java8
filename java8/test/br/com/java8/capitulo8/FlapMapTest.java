package br.com.java8.capitulo8;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import br.com.java8.model.Grupo;
import br.com.java8.model.Usuario;
import br.com.java8.util.UsuarioTestUtil;

public class FlapMapTest {

	
	private List<Usuario> usuarios;
	
	@Before
	public void setup() {
		usuarios = UsuarioTestUtil.criarUsuarios();
	}
	
	@Test
	public void sucessoFlapMapTest() {

		usuarios = UsuarioTestUtil.criarUsuarios();
		
		List<Usuario> usuariosIngles = usuarios.subList(0, 4);
		List<Usuario> usuarioEspanhol = usuarios.subList(3, 6); 
		
		Grupo grupoIngles = new Grupo();
		grupoIngles.addUsuariosGrupo(usuariosIngles);
		
		Grupo grupoEspanhol = new Grupo();
		grupoIngles.addUsuariosGrupo(usuarioEspanhol);
		
		List<Grupo> grupos = Arrays.asList(grupoEspanhol, grupoIngles);
		
		
		grupos.stream().flatMap(g -> g.getUsuarios().stream()).distinct().forEach(System.out :: println);
		
		
		Stream<Usuario> usuariosUnicos = grupos.stream().flatMap(g -> g.getUsuarios().stream()).distinct();
		
		
		assertThat("Total de elementos: ", usuariosUnicos.count(), is(6L));
		
		
	}

}
