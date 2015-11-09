package br.com.cap4;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import br.com.modelo.Usuario;

public class Capitulo4 {
	public static void main(String... args) {
		Usuario user1 = new Usuario("Heitor", 100);
		Usuario user2 = new Usuario("Victoria", 50);
		Usuario user3 = new Usuario("Natalia", 70);

		List<Usuario> usuarios = Arrays.asList(user1, user2, user3);

		// primeira interface com o metodo accept implementado
		Consumer<Usuario> mostraMensagem = u -> System.out.println("antes de imprimir os nomes");
		// segunda interface com o metodo accept implementado
		Consumer<Usuario> imprimeNome = u -> System.out.println(u.getNome());
		// terceira interface com o metodo accept implementado
		Consumer<Usuario> separa = u -> System.out.println("=================================");
		/**
		 * pattern decorator
		 */
		usuarios.forEach(mostraMensagem.andThen(imprimeNome).andThen(separa));
		
		
	}
}
