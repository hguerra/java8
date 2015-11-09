package br.com.cap5;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

import br.com.modelo.Usuario;

public class Capitulo5 {

	public static void main(String[] args) {
		Usuario user1 = new Usuario("Natalia", 100);
		Usuario user2 = new Usuario("Victoria", 50);
		Usuario user3 = new Usuario("Heitor", 70);

		List<Usuario> usuarios = Arrays.asList(user1, user2, user3);
		System.out.println("============================================");

		Comparator<Usuario> comparator = new Comparator<Usuario>() {
			public int compare(Usuario u1, Usuario u2) {
				return u1.getNome().compareTo(u2.getNome());
			}
		};

		Collections.sort(usuarios, comparator);

		usuarios.forEach(u -> System.out.println(u.getNome()));

		System.out.println("============================================");

		Comparator<Usuario> comparatorLambda1 = (u1, u2) -> u1.getNome().compareTo(u2.getNome());

		Collections.sort(usuarios, comparatorLambda1);

		usuarios.forEach(u -> System.out.println(u.getNome()));

		System.out.println("============================================");

		// possíveis usuários com atributo nome igual a null
		// return
		// String.CASE_INSENSITIVE_ORDER.compare(u1.getNome(),u2.getNome())

		Collections.sort(usuarios, (u1, u2) -> u1.getNome().compareTo(u2.getNome()));

		usuarios.forEach(u -> System.out.println(u.getNome()));

		System.out.println("============================================");

		usuarios.sort((u1, u2) -> u1.getNome().compareTo(u2.getNome()));

		usuarios.forEach(u -> System.out.println(u.getNome()));

		System.out.println("============================================");

		usuarios.sort(Comparator.comparing(u -> u.getNome()));

		usuarios.forEach(u -> System.out.println(u.getNome()));

		System.out.println("============================================");

		List<String> palavras = Arrays.asList("Casa do Código", "Alura", "Caelum");
		palavras.sort(Comparator.naturalOrder());

		palavras.forEach(s -> System.out.println(s));

		System.out.println("============================================");

		/**
		 * Estudando compraring
		 */
		Function<Usuario, String> extraiNome = u -> u.getNome();
		Comparator<Usuario> comparatorComparing = Comparator.comparing(extraiNome);
		usuarios.sort(comparatorComparing);

		System.out.println("==============================================");

		ToIntFunction<Usuario> extraiPontos = u -> u.getPontos();
		Comparator<Usuario> comparatorComparingInt = Comparator.comparingInt(extraiPontos);
		usuarios.sort(comparatorComparingInt);

		// Claro que você pode (e deve) usar a versão mais enxuta, passando
		// diretamente o
		// lambda para a fábrica de comparators, e até mesmo invocar o sort na
		// mesma linha
		
		usuarios.sort(Comparator.comparingInt(u -> u.getPontos()));
	}

}
