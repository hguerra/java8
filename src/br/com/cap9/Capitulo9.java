package br.com.cap9;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.modelo.Usuario;

public class Capitulo9 {

	public static void main(String[] args) throws IOException {
		/**
		 * Numero de linhas de um arquivo
		 */
		Map<Path, Long> lines = Files.list(Paths.get("../java8/src/br/com/cap8/"))
				.filter(p -> p.toString().endsWith(".java")).collect(Collectors.toMap(p -> p, p -> lines(p).count()));
		System.out.println(lines);
		/**
		 * Function identity
		 */

		Map<Path, List<String>> content = Files.list(Paths.get("../java8/src/br/com/cap8/"))
				.filter(p -> p.toString().endsWith(".java"))
				.collect(Collectors.toMap(Function.identity(), p -> lines(p).collect(Collectors.toList())));

		System.out.println(content);

		/**
		 * groupingBy e partitioningBy
		 */

		Usuario user1 = new Usuario("Paulo Silveira", 150, true);
		Usuario user2 = new Usuario("Rodrigo Turini", 120, true);
		Usuario user3 = new Usuario("Guilherme Silveira", 90);
		Usuario user4 = new Usuario("Sergio Lopes", 120);
		Usuario user5 = new Usuario("Adriano Almeida", 100);
		List<Usuario> usuarios = Arrays.asList(user1, user2, user3, user4, user5);

		/**
		 * lista de usuários que possuem aquela pontuação
		 */
		Map<Integer, List<Usuario>> pontuacao = new HashMap<>();
		for (Usuario u : usuarios) {
			pontuacao.computeIfAbsent(u.getPontos(), user -> new ArrayList<>()).add(u);
		}
		System.out.println(pontuacao);

		Map<Integer, List<Usuario>> pontuacaoStream = usuarios.stream()
				.collect(Collectors.groupingBy(Usuario::getPontos));

		System.out.println(pontuacaoStream);

		/**
		 * separando os moderadores
		 */
		Map<Boolean, List<Usuario>> moderadores = usuarios.stream()
				.collect(Collectors.partitioningBy(Usuario::isModerador));
		System.out.println(moderadores);

		/**
		 * nomes por tipo
		 */

		Map<Boolean, List<String>> nomesPorTipo = usuarios.stream().collect(Collectors
				.partitioningBy(Usuario::isModerador, Collectors.mapping(Usuario::getNome, Collectors.toList())));

		/**
		 * Pontuacao por tipo
		 */

		Map<Boolean, Integer> pontuacaoPorTipo = usuarios.stream()
				.collect(Collectors.partitioningBy(Usuario::isModerador, Collectors.summingInt(Usuario::getPontos)));
		System.out.println(pontuacaoPorTipo);

		/**
		 * Concatenando nomes usuarios
		 */

		String nomes = usuarios.stream().map(Usuario::getNome).collect(Collectors.joining(", "));

		System.out.println(nomes);

		/**
		 * Executando o pipeline em paralelo
		 */

		List<Usuario> filtradosOrdenados = usuarios.parallelStream().filter(u -> u.getPontos() > 100)
				.sorted(Comparator.comparing(Usuario::getNome)).collect(Collectors.toList());
		
		filtradosOrdenados.forEach(System.out::println);

	}

	static Stream<String> lines(Path p) {
		try {
			return Files.lines(p);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

}
