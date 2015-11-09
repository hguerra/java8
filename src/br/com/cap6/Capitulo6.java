package br.com.cap6;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import br.com.modelo.Usuario;

public class Capitulo6 {
	public static void main(String[] args) {
		Usuario user1 = new Usuario("Natalia", 100);
		Usuario user2 = new Usuario("Victoria", 50);
		Usuario user3 = new Usuario("Heitor", 70);

		List<Usuario> usuarios = Arrays.asList(user1, user2, user3);
		System.out.println("============================================");

		// Lambda normal

		//usuarios.forEach(u -> u.tornarModerador());

		// method reference

		usuarios.forEach(Usuario::tornarModerador);
		
		System.out.println("============================================");
		
		//usuarios.sort(Comparator.comparing(u -> u.getNome()));
		
		usuarios.sort(Comparator.comparing(Usuario::getNome));
		
		usuarios.forEach(u -> System.out.println(u.getNome()));
		
		System.out.println("============================================");
		/**
		 * ordenar pelos pontos e, no caso de empate, ordenar pelo nome.
		 */
		usuarios.sort(Comparator.comparingInt(Usuario::getPontos)
				.thenComparing(Usuario::getNome));
		
		/**
		 * todos os usuários nulos da nossa lista estarão posicionados no fim
		 */
		
		usuarios.sort(Comparator.nullsLast(
				Comparator.comparing(Usuario::getNome)));
		
		/**
		 * ordenar por pontos, porém na ordem decrescente
		 */
		
		usuarios.sort(Comparator.comparing(Usuario::getPontos).reversed());
		
		
		
	}
}
