package br.com.cap7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.modelo.Usuario;

public class Capitulo7 {
	public static void main(String[] args) {
		Usuario user1 = new Usuario("Natalia", 100);
		Usuario user2 = new Usuario("Victoria", 50);
		Usuario user3 = new Usuario("Heitor", 150);

		List<Usuario> usuarios = Arrays.asList(user1, user2, user3);

		Stream<Usuario> stream = usuarios.stream().filter(u -> u.getPontos() > 100);

		stream.forEach(System.out::println);
		/**
		 * Imprime os usuarios com mais de 100 pointos
		 */
		usuarios.stream().filter(u -> u.getPontos() > 100).forEach(System.out::println);
		/**
		 * filtrar moderadores
		 */
		usuarios.stream().filter(u -> !u.isModerador()).forEach(u -> System.out.println("Nao sao moderadores:" + u));
		/**
		 * guardar os usuários que têm mais de 100 pontos
		 */
		List<Usuario> maisQue100 = new ArrayList<>();

		usuarios.stream().filter(u -> u.getPontos() > 100).forEach(maisQue100::add);

		/**
		 * guardar os usuários que têm mais de 100 pontos com
		 * Collectors.toList()
		 */
		List<Usuario> maisQue100Collector = usuarios.stream().filter(u -> u.getPontos() > 100)
				.collect(Collectors.toList());

		maisQue100Collector.forEach(System.out::println);
		/**
		 * usando map
		 */
		List<Integer> pontos = usuarios.stream().map(Usuario::getPontos).collect(Collectors.toList());

		pontos.forEach(System.out::println);

		/**
		 * pontuacao media
		 */

		double pontuacaoMedia = usuarios.stream().mapToInt(Usuario::getPontos).average().getAsDouble();
		System.out.println("Pontuacao media:" + pontuacaoMedia);

		/**
		 * pontuacao media com tratamento
		 */

		OptionalDouble media = usuarios.stream().mapToInt(Usuario::getPontos).average();

		double pontuacaoMediaTratamento = media.orElse(0.0);

		System.out.println("Pontuacao media: " + pontuacaoMediaTratamento);

		/**
		 * 
		 */
		Optional<String> maxNome = usuarios.stream().max(Comparator.comparingInt(Usuario::getPontos))
				.map(u -> u.getNome());
	
		System.out.println("Max nome:" + maxNome.get());

	}
}
