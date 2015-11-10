package br.com.cap8;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Arquivos {

	public static void main(String[] args) throws IOException {
		/**
		 * listar todos os arquivos de um diretorio
		 */
		Files.list(Paths.get("../")).forEach(System.out::println);
		/**
		 * listar todos os arquivos de um diretorio que terminem com .java
		 */
		Files.list(Paths.get("../java8/src/br/com/cap8")).filter(p -> p.toString().endsWith(".java"))
				.forEach(System.out::println);

		/**
		 * Stream com todas as linhas de um arquivo
		 */
		Stream<String> strings = Files.list(Paths.get("../java8/src/br/com/cap8/")).filter(p -> p.toString().endsWith(".java"))
				.flatMap(p -> lines(p));
		
		strings.forEach(System.out::println);
	}

	static Stream<String> lines(Path p) {
		try {
			return Files.lines(p);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

}
