package br.com.java8.capitulo4;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;

import org.junit.Test;

import br.com.java8.model.Usuario;
import br.com.java8.utils.ListUtils;

public class DefaultMethodTest {

	@Test
	public void sucessoForEachAndThen() {
		
		List<Usuario> usuarios = criarUsuarios();
		
		Consumer<Usuario> usuariosAntes = u -> System.out.println("Antes da mensagem");
		
		Consumer<Usuario> usuariosApos = u -> System.out.println(u.getNome());
		
		usuarios.forEach(usuariosAntes.andThen(usuariosApos) );

		assertThat("Valor esperado: ", usuarios.get(0).getNome(), containsString("Michel"));
	}

	
	@Test
	public void sucessoForEachRemoveIf() {
		
		List<Usuario> usuarios = criarUsuarios();

		/*
		 * Forma declarando o predicate
		 */
		//		Predicate<Usuario> predicateUser = new Predicate<Usuario>() {
		//			@Override
		//			public boolean test(Usuario u) {
		//				return u.getPontos() > 100;
		//			}
		//			
		//		};
		//		usuarios.removeIf(predicateUser);
		
		/*
		 * Forma usando Lambda diretamente
		 */
		usuarios.removeIf(u -> u.getPontos() > 100);
		usuarios.forEach(u -> System.out.println(u.getNome())); 
		assertThat("Valor esperado: ", usuarios.size(), is(2));
	}
	
	@Test
	public void sucessoAoOrdenarUsuariosMetodoAntigo(){
		List<Usuario> usuarios = criarUsuarios();
		Comparator<Usuario> comparatorUser = new Comparator<Usuario>() {
			
			@Override
			public int compare(Usuario u1, Usuario u2) {
				return u1.getNome().compareTo(u2.getNome());
			}
		};
		
		usuarios.sort(comparatorUser);
		List<String> nomes = ListUtils.extrairListaPropriedades(usuarios, "nome", false);
		assertThat(nomes, is(containsInAnyOrder("Grace", "Michel", "Ana")));
		assertThat(nomes, contains("Ana", "Grace", "Michel"));
		
	}
	
	@Test
	public void sucessoAoOrdenarUsuariosMetodoLambda(){
		List<Usuario> usuarios = criarUsuarios();

		//Usando a variavel comparator
		Comparator<Usuario> comparatorUser  = (u1, u2) -> u1.getNome().compareTo(u2.getNome()); 
		usuarios.sort(comparatorUser);
		
		//Ou numa unica linha
		usuarios.sort((u1, u2) -> u1.getNome().compareTo(u2.getNome()));
		
		//Omitindo a comparacao
		usuarios.sort(comparing(u -> u.getNome()));
		
		List<String> nomes = ListUtils.extrairListaPropriedades(usuarios, "nome", false);
		assertThat(nomes, is(containsInAnyOrder("Grace", "Michel", "Ana")));
		assertThat(nomes, contains("Ana", "Grace", "Michel"));
		
	}
	
	@Test
	public void sucessoAoOrdenarUsuariosPontosMetodoLambda(){
		List<Usuario> usuarios = criarUsuarios();

		//Usando a variavel comparator
		ToIntFunction<Usuario> extraiPontos = u -> u.getPontos();
		Comparator<Usuario> comparatorUser  = comparingInt(extraiPontos); 
		usuarios.sort(comparatorUser);
		
		//Omitindo a comparacao
		usuarios.sort(comparingInt(u -> u.getPontos()));
		
		List<Integer> pontos = ListUtils.extrairListaPropriedades(usuarios, "pontos", false);
		pontos.sort(Comparator.reverseOrder());
		assertThat(pontos, is(containsInAnyOrder(pontos.toArray())));
		pontos.sort(Comparator.naturalOrder());
		assertThat(pontos, contains(pontos.toArray()));
		
	}

	
	@Test
	public void sucessoAoOrdenarUsuariosMetodoLambdaIgnoreCaseSensitive(){
		
		Usuario userMichel = new Usuario("MICHEL", 136);
		Usuario userGrace = new Usuario("grace", 33);
		Usuario userAna = new Usuario("Ana", 1);
		
		List<Usuario> usuarios = Arrays.asList(userMichel, userGrace, userAna);
		

		//Usando a variavel comparator
		Comparator<Usuario> comparatorUser  = (u1, u2) -> String.CASE_INSENSITIVE_ORDER.compare(u1.getNome(), u2.getNome());
		usuarios.sort(comparatorUser);
		
		//Ou numa unica linha
		usuarios.sort((u1, u2) -> String.CASE_INSENSITIVE_ORDER.compare(u1.getNome(), u2.getNome()));
		
		List<String> nomesOrdenados = ListUtils.extrairListaPropriedades(usuarios, "nome", false);
		List<String> nomesInversos = ListUtils.extrairListaPropriedades(usuarios, "nome", false);
		
		nomesOrdenados.sort(Comparator.naturalOrder());
		nomesInversos.sort(Comparator.reverseOrder());
		
		assertThat(nomesOrdenados, is(containsInAnyOrder(nomesInversos.toArray())));
		assertThat(nomesOrdenados, contains(nomesOrdenados.toArray()));
		System.out.println("Nomes ordenados: " + nomesOrdenados);
		System.out.println("Nomes inversos: " + nomesInversos);
		
	}

	
	private List<Usuario> criarUsuarios() {
		Usuario userMichel = new Usuario("Michel", 136);
		Usuario userGrace = new Usuario("Grace", 33);
		Usuario userAna = new Usuario("Ana", 1);
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(userMichel);
		usuarios.add(userGrace);
		usuarios.add(userAna);
		
		return usuarios;
	}

}
