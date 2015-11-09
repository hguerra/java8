package br.com.cap4;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import br.com.modelo.Usuario;

public class RemoveIf {
	public static void main(String[] args) {
		Usuario user1 = new Usuario("Heitor", 100);
		Usuario user2 = new Usuario("Victoria", 200);
		Usuario user3 = new Usuario("Natalia", 200);

		Predicate<Usuario> predicado = new Predicate<Usuario>() {
			public boolean test(Usuario u) {
				return u.getPontos() > 160;
			}
		};
		List<Usuario> usuarios = new ArrayList<>();
		usuarios.add(user1);
		usuarios.add(user2);
		usuarios.add(user3);
		
//		usuarios.removeIf(predicado);
//		
//		usuarios.forEach(u -> System.out.println(u.getNome()));
		
		//com Lambda puro
		usuarios.removeIf(u -> u.getPontos() > 160);
		
		usuarios.forEach(u -> System.out.println(u.getNome()));

	}

}
