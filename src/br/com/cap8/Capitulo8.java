package br.com.cap8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.modelo.Usuario;

public class Capitulo8 {
	public static void main(String[] args) {
		Usuario user1 = new Usuario("Natalia", 100);
		Usuario user2 = new Usuario("Victoria", 50);
		Usuario user3 = new Usuario("Heitor", 150);

		List<Usuario> usuarios = Arrays.asList(user1, user2, user3);

		/**
		 * Ordenando um Stream
		 */
		List<Usuario> filtradosOrdenados = usuarios.stream().filter(u -> u.getPontos() > 100)
				.sorted(Comparator.comparing(Usuario::getNome)).collect(Collectors.toList());

		/**
		 * Analisando com peek
		 */
		usuarios.stream().filter(u -> u.getPontos() > 100).peek(System.out::println).findAny();
	}
}
