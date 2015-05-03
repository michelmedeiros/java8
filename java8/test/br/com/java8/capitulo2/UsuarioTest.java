package br.com.java8.capitulo2;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.junit.Test;

import br.com.java8.consumer.MostradorConsumer;
import br.com.java8.model.Usuario;

public class UsuarioTest {

	@Test
	public void sucessoAoDefinirUsuarioModoConvencional() {
		
		List<Usuario> usuarios = criarUsuarios();
		
		for (Usuario u : usuarios) {
			System.out.println(u.getNome());
		}
		
		assertThat("3 elementos esperados", usuarios.size(), is(equalTo(3)));
	}
	
	@Test
	public void sucessoAoPercorrerUsuarioModoNovoForEach() {
		
		List<Usuario> usuarios = criarUsuarios();
		Usuario michel = usuarios.iterator().next();
		usuarios.forEach(new MostradorConsumer());
		assertThat("3 elementos esperados não nulos", usuarios.size(), is(allOf(notNullValue(), equalTo(3))));
		assertThat("O elemento peso deve ser número inteiro", michel.getPontos(), is(any(Integer.class)));
		assertThat("Valida um dos asserts", michel.getPontos(), is(anyOf(nullValue(), instanceOf(Integer.class))));
		assertThat("Valida qualquer assert pra true", michel.getPontos(), is(anything()));
		assertThat("O valor não deve ser 3", michel.getPontos(), is(not(3)));
		assertThat("Possui item Michel", usuarios, hasItem(michel));
		
	}
	
	@Test
	public void sucessoAoPercorrerUsuarioModoNovoForEachComClasseAnonima() {
		
		List<Usuario> usuarios = criarUsuarios();
		usuarios.forEach(new Consumer<Usuario>() {
			@Override
			public void accept(Usuario u) { 
				System.out.println(u.getNome());
			}
		});
		
		assertThat("3 elementos esperados", usuarios.size(), is(equalTo(3)));
	}
	
	@Test
	public void sucessoAoPercorrerUsuarioModoNovoForEachUsandoLambda() {
		
		List<Usuario> usuarios = criarUsuarios();
		usuarios.forEach(u -> System.out.println(u.getNome()));
		assertThat("3 elementos esperados", usuarios.size(), is(equalTo(3)));
	}
	
	@Test
	public void sucessoAoTornarModeradorUsuariosForEachUsandoLambda() {
		
		List<Usuario> usuarios = criarUsuarios();
		usuarios.forEach(u -> u.tornaModerador());
		usuarios.forEach(u -> {
			System.out.println(u.getNome());
			System.out.println(u.isModerador());
		});
		
		assertThat("3 elementos esperados", usuarios.size(), is(equalTo(3)));
		assertThat("Usuarios moderadores", usuarios.get(0).isModerador(), is(Boolean.TRUE));
	}

	private List<Usuario> criarUsuarios() {
		Usuario userMichel = new Usuario("Michel", 36);
		Usuario userGrace = new Usuario("Grace", 33);
		Usuario userAna = new Usuario("Ana", 1);
		
		return Arrays.asList(userMichel, userGrace, userAna);
	}

}
