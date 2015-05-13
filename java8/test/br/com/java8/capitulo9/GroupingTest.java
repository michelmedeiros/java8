package br.com.java8.capitulo9;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.junit.Test;

import br.com.java8.model.Usuario;
import br.com.java8.util.UsuarioTestUtil;

public class GroupingTest {

	@Test
	public void criandoMapsTest() throws IOException {
		// Ler todo o conteúdo dos arquivos .java
		Stream<String> strings = Files
				.list(Paths.get("./src/br/com/java8/model"))
				.filter(f -> f.toString().endsWith(".java"))
				.flatMap(f -> lines(f));

		strings.forEach(System.out::println);

		// Obter a quantidade de linhas de um arquivo num LongStream
		LongStream linhasStream = Files
				.list(Paths.get("./src/br/com/java8/model"))
				.filter(f -> f.toString().endsWith(".java"))
				.mapToLong(f -> lines(f).count());

		// Extraindo a lista de quantidade de linhas dos arquivos
		List<Long> linhas = Files.list(Paths.get("./src/br/com/java8/model"))
				.filter(f -> f.toString().endsWith(".java"))
				.map(f -> lines(f).count()).collect(Collectors.toList());
		System.out.println(linhas);
		assertThat("Numero de linhas", linhas.size(), is(equalTo(3)));

		// Extraindo a lista de quantidade de linhas dos arquivos num Map -
		// Opcao 1
		Map<Path, Long> linhasPorArquivo = new HashMap<Path, Long>();
		Files.list(Paths.get("./src/br/com/java8/model"))
				.filter(f -> f.toString().endsWith(".java"))
				.forEach(f -> linhasPorArquivo.put(f, lines(f).count()));
		System.out.println(linhasPorArquivo);

		// Extraindo a lista de quantidade de linhas dos arquivos num Map -
		// Opcao 2
		Map<Path, Long> linhasPoraMapaDeArquivo = new HashMap<Path, Long>();
		linhasPoraMapaDeArquivo = Files
				.list(Paths.get("./src/br/com/java8/model"))
				.filter(f -> f.toString().endsWith(".java"))
				.collect(
						Collectors.toMap(Function.identity(), f -> lines(f)
								.count()));
		System.out.println(linhasPoraMapaDeArquivo);

		// Lista de conteudo por arquivos
		Map<Path, List<String>> conteudoArquivos = Files
				.list(Paths.get("./src/br/com/java8/model"))
				.filter(f -> f.toString().endsWith(".java"))
				.collect(
						Collectors.toMap(Function.identity(), f -> lines(f)
								.collect(Collectors.toList())));
		System.out.println(conteudoArquivos);
	}

	static Stream<String> lines(Path p) {
		try {
			return Files.lines(p);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	@Test
	public void sucessoAoAgruparDadosTest() {
		List<Usuario> usuarios = UsuarioTestUtil.criarUsuarios();
		// Usuarios cuja chave é o nome e valor é os pontos
		Map<String, Usuario> mapUsuarios = usuarios.stream().collect(
				Collectors.toMap(Usuario::getNome, Function.identity()));
		System.out.println(usuarios);

		// Usuarios cuja chave os pontos para um conjunto de usuarios desses
		// pontos
		Map<Integer, List<Usuario>> pontosPorUsuarios = usuarios.stream()
				.collect(Collectors.groupingBy(Usuario::getPontos));
		System.out.println(pontosPorUsuarios);

		// Usuarios divididos em moderadores ou não moderadores
		Map<Boolean, List<Usuario>> moderadoresPorUsuarios = usuarios.stream()
				.collect(Collectors.partitioningBy(Usuario::isModerador));
		System.out.println(moderadoresPorUsuarios);

		
		// Usuarios divididos em moderadores ou não moderadores
		Map<Boolean, List<String>> moderadoresPorNomeUsuarios = usuarios.stream()
				.collect(Collectors.partitioningBy(Usuario::isModerador,
						Collectors.mapping(
								Usuario::getNome, Collectors.toList())));
		System.out.println(moderadoresPorNomeUsuarios);
		
		// Usuarios divididos em moderadores somando seus pontos
		Map<Boolean, Integer> moderadoresPorSomaPontosUsuarios = usuarios.stream()
				.collect(Collectors.partitioningBy(Usuario::isModerador,
						Collectors.summingInt(Usuario::getPontos)));
		System.out.println(moderadoresPorSomaPontosUsuarios);
		
		String nomesConcatenados = usuarios.stream()
				.map(Usuario :: getNome)
				.sorted()
				.collect(Collectors.joining(", "));
		System.out.println(nomesConcatenados);
	}

}
