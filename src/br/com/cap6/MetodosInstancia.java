package br.com.cap6;

import java.util.Arrays;
import java.util.List;

import br.com.modelo.Usuario;

public class MetodosInstancia {
	public static void main(String[] args) {
		Usuario heitor = new Usuario("Heitor Guerra", 50);

		// Consumer<Usuario> consumer1 = Usuario::tornaModerador;
		// Consumer<Usuario> consumer2 = u -> u.tornaModerador();
		
		Runnable bloco = heitor::tornarModerador;
		
		Runnable bloco2 = () -> heitor.tornarModerador();
		
		bloco.run();
		
		/**
		 * Referecenciando metodos com argumentos
		 */
		Usuario user1 = new Usuario("Natalia", 100);
		Usuario user2 = new Usuario("Victoria", 50);
		Usuario user3 = new Usuario("Heitor", 70);

		List<Usuario> usuarios = Arrays.asList(user1, user2, user3);
		
		usuarios.forEach(System.out::println);

	}
}
