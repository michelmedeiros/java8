package br.com.java8.capitulo8;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.junit.Test;

public class FileTest {

	@Test
	public void sucessoAoValidarFileTest() {
		try {
			// Lista todos os arquivos de um determinado caminho
			Files.list(Paths.get("./src/br/com/java8")).forEach(
					System.out::println);
			// Lista os arquivso com extensão .java
			Files.list(Paths.get("./src/br/com/java8/model"))
					.filter(f -> f.toString().endsWith(".java"))
					.forEach(System.out::println);
			// Ler todo o conteúdo dos arquivos .java
			Stream<String> strings = Files.list(Paths.get("./src/br/com/java8/model"))
					.filter(f -> f.toString().endsWith(".java"))
					.flatMap(f -> lines(f));

			strings.forEach(System.out::println);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	static Stream<String> lines(Path p){
		try{
			return Files.lines(p);
		}catch(IOException e){
			throw new UncheckedIOException(e);
		}
	}

}
