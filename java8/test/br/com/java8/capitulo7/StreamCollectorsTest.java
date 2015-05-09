package br.com.java8.capitulo7;

import static java.util.Comparator.comparing;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import org.junit.Test;

import br.com.java8.model.Usuario;

public class StreamCollectorsTest {

	@Test
	public void sucessoVaidarOrdenacaoDeListas() {

		List<Usuario> usuarios = criarUsuarios();

		// Dez primeiros usu�rios com mais pontos
		usuarios.sort(comparing(Usuario::getPontos).reversed());
		usuarios.subList(0, 10).forEach(Usuario::tornaModerador);
		usuarios.forEach(System.out::println);
		assertThat("Usu�rio com mais pontos: Junior",
				usuarios.get(0).getNome(), is(equalTo("Junior")));
		assertThat("Maior pontua��o: 199", usuarios.get(0).getPontos(), is(199));

	}

	@Test
	public void sucessoAoUtilizarStream() {
		List<Usuario> usuarios = criarUsuarios();
		// Todos usu�rios com mais de 100 pontos torna-se moderador
		Stream<Usuario> stremUser =  usuarios.stream()
		.filter(u -> u.getPontos() > 100);
		stremUser.forEach(System.out :: println);
		
	}

	private List<Usuario> criarUsuarios() {

		BiFunction<String, Integer, Usuario> fabricaUsuarios = Usuario::new;

		Usuario userMichel = fabricaUsuarios.apply("Michel", 136);
		Usuario userGrace = fabricaUsuarios.apply("Grace", 33);
		Usuario userAna = fabricaUsuarios.apply("Ana", 100);
		Usuario userMaria = fabricaUsuarios.apply("Maria", 11);
		Usuario userPaulo = fabricaUsuarios.apply("Paulo", 15);
		Usuario userFatima = fabricaUsuarios.apply("Fatima", 177);
		Usuario userJunior = fabricaUsuarios.apply("Junior", 199);
		Usuario userPedro = fabricaUsuarios.apply("Pedro", 155);
		Usuario userJuliana = fabricaUsuarios.apply("Juliana", 144);
		Usuario userJoana = fabricaUsuarios.apply("Joana", 133);
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(userMichel);
		usuarios.add(userGrace);
		usuarios.add(userAna);
		usuarios.add(userMaria);
		usuarios.add(userPaulo);
		usuarios.add(userFatima);
		usuarios.add(userJunior);
		usuarios.add(userPedro);
		usuarios.add(userJuliana);
		usuarios.add(userJoana);

		return usuarios;
	}

}
