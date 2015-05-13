package br.com.java8.capitulo8;

import static java.util.Comparator.comparing;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;
import static org.hamcrest.number.OrderingComparison.lessThan;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;

import br.com.java8.model.Fibonacci;
import br.com.java8.model.Usuario;
import br.com.java8.util.UsuarioTestUtil;

public class StreamOthersCollectorsTest {


	private List<Usuario> usuarios;
	
	@Before
	private void setup() {
		usuarios = UsuarioTestUtil.criarUsuarios();
	}
	
	@Test
	public void sucessoVaidarOrdenacaoStreamDeListas() {

		List<Usuario> usuarios = UsuarioTestUtil.criarUsuarios();

		// Dez primeiros usuários com mais pontos
		List<Usuario> usuariosOrdenados = usuarios.stream()
				.filter(u -> u.getPontos() > 100)
				.sorted(comparing(Usuario::getNome))
				.collect(Collectors.toList());

		System.out.println("Usuários");
		usuarios.stream().forEach(System.out::println);
		usuarios.stream().iterator().forEachRemaining(System.out::println);
		System.out.println("Usuários ordenados");
		usuariosOrdenados.forEach(System.out::println);
		assertThat("Usuário com mais pontos ordenados por nome: Fatima",
				usuariosOrdenados.get(0).getNome(), is(equalTo("Fatima")));
		
		Optional<Usuario> usuarioAny = usuarios.stream().filter(u -> u.getPontos() < 100).peek(System.out :: println).findAny();
		System.out.println("Qualquer usuário " + usuarioAny.get().getNome());
		assertThat("Usuário com menos pontos ordenados por nome: Fatima",
				usuarios, hasItem(usuarioAny.get()));
		
		Optional<Usuario> usuarioFirst = usuarios.stream().filter(u -> u.getPontos() > 100).peek(System.out :: println).findFirst();
		System.out.println("Primeiro usuário " + usuarioFirst.get().getNome());
		assertThat("Usuário com mais pontos ordenados por nome: Fatima",
				usuarios, hasItem(usuarioFirst.get()));

	}

	@Test
	public void predicateTest(){
		
		boolean hasNoneModerador = usuarios.stream().noneMatch(Usuario :: isModerador);
		assertThat("Usuário moderador",
				hasNoneModerador, is(equalTo(Boolean.TRUE)));
		
		
		Optional<Usuario> primeiroUsuario = usuarios.stream().findFirst();
		primeiroUsuario.get().tornaModerador();
		
		boolean hasAnyModerador = usuarios.stream().anyMatch(Usuario :: isModerador);
		assertThat("Usuário moderador",
				hasAnyModerador, is(equalTo(primeiroUsuario.get().isModerador())));
		
		
		usuarios.forEach(Usuario::tornaModerador);
		List<Usuario> usuariosModeradores = usuarios.stream().collect(Collectors.toList());
		
		boolean hasAllModerador = usuariosModeradores.stream().allMatch(Usuario :: isModerador);
		assertThat("Usuário moderador",
				hasAllModerador, is(equalTo(Boolean.TRUE)));
	}
	
	@Test
	public void curtoCircuitoTest(){
		Random random = new Random(0);
		IntStream intStream = IntStream.generate(() -> random.nextInt());
		List<Integer> inteirosAleatorios = intStream.limit(100).boxed().collect(Collectors.toList());
		assertThat("Inteiros aleatorios",
				inteirosAleatorios.size(), is(equalTo(100)));
		
	}
	
	@Test
	public void fibonacciTest(){
		int maiorQueCem = IntStream.generate(new Fibonacci()).filter(f -> f > 100).peek(System.out :: println).findFirst().getAsInt();
		int menorQueCem = IntStream.generate(new Fibonacci()).filter(f -> f < 100).peek(System.out :: println).findFirst().getAsInt();
		int igualACem = IntStream.generate(new Fibonacci()).filter(f -> f >= 100).peek(System.out :: println).findFirst().getAsInt();
		assertThat("Inteiros aleatorios maior que cem",
				maiorQueCem, is(greaterThan(100)));

		assertThat("Inteiros aleatorios meno que cem",
				menorQueCem, is(lessThan(100)));
		
		assertThat("Inteiros aleatorios igual ou maior que cem",
				igualACem, is(greaterThanOrEqualTo(100)));
	}
	
	
}
