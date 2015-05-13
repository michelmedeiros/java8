package br.com.java8.capitulo7;

import static java.util.Comparator.comparing;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import org.junit.Test;

import br.com.java8.model.Usuario;

public class StreamCollectorsTest {

	@Test
	public void sucessoVaidarOrdenacaoDeListas() {

		List<Usuario> usuarios = criarUsuarios();

		// Dez primeiros usuários com mais pontos
		usuarios.sort(comparing(Usuario::getPontos).reversed());
		usuarios.subList(0, 10).forEach(Usuario::tornaModerador);
		usuarios.forEach(System.out::println);
		assertThat("Usuário com mais pontos: Junior",
				usuarios.get(0).getNome(), is(equalTo("Junior")));
		assertThat("Maior pontuação: 199", usuarios.get(0).getPontos(), is(199));

	}

	@Test
	public void sucessoAoUtilizarStream() {
		List<Usuario> usuarios = criarUsuarios();
		// Todos usuários com mais de 100 pontos torna-se moderador
		usuarios.stream().filter(u -> u.getPontos() > 100).forEach(Usuario::tornaModerador);
		usuarios.stream().filter(Usuario :: isModerador);
		
		assertThat("Usuario com mais de 100 pontos", usuarios.get(0)
				.getPontos(), is(136));
		assertThat("Usuario com mais de 100 pontos", usuarios.get(0)
				.isModerador(), is(equalTo(Boolean.TRUE)));

	}

	
	@Test
	public void sucessoAoUtilizarCollector() {
		List<Usuario> usuarios = criarUsuarios();
		// Todos usuários com mais de 100 pontos torna-se moderador
		List<Usuario> usuariosMaioresQue100 =  usuarios.stream().filter(u -> u.getPontos() > 100).collect(Collectors.toList());
		
		assertThat("Usuario com mais de 100 pontos", usuariosMaioresQue100.get(0)
				.getPontos(), is(136));
		List<Integer> pontosUsuario = usuarios.stream().map(Usuario :: getPontos).collect(Collectors.toList());
		assertThat("Pontos dos Usuarios", pontosUsuario.get(0), is(equalTo(136)));
		
		Integer minPontos = usuarios.stream().mapToInt(Usuario::getPontos).min().getAsInt();
		assertThat("Menor Ponto dos Usuarios", minPontos, is(equalTo(11)));
		
		Integer maxPontos = usuarios.stream().mapToInt(Usuario::getPontos).max().getAsInt();
		assertThat("Maior Ponto dos Usuarios", maxPontos, is(equalTo(199)));
		
		Double mediaPontos = usuarios.stream().mapToInt(Usuario::getPontos).average().orElse(0.0);
		assertThat("Média dos pontos dos Usuarios", mediaPontos, is(equalTo(110.3)));
		
		Integer somaPontos = usuarios.stream().mapToInt(Usuario::getPontos).sum();
		assertThat("Soma dos Pontos dos Usuarios", somaPontos, is(equalTo(1103)));
		
		Integer multiplicaPontos = usuarios.stream().mapToInt(Usuario::getPontos).reduce(1, (a,b) -> a * b);
		assertThat("Multiplicação dos Pontos dos Usuarios", multiplicaPontos, is(equalTo(1614162432)));
		
		Integer subtraiPontos = usuarios.stream().mapToInt(Usuario::getPontos).reduce(1, (a,b) -> a - b);
		assertThat("Soma dos Pontos dos Usuarios", subtraiPontos, is(equalTo(-1102)));
		
		Optional<String> maxNome = usuarios
				.stream()
				.max(Comparator.comparingInt(Usuario::getPontos))
				.map(Usuario::getNome);
		assertThat("Nome do maior pontuador", maxNome.get(), is(equalTo("Junior")));

		
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
