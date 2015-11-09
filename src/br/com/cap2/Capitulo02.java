package br.com.cap2;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import br.com.modelo.Usuario;

public class Capitulo02 {
	public static void main(String[] args) {
		Usuario user1 = new Usuario("Heitor", 100);
		Usuario user2 = new Usuario("Victoria", 50);
		Usuario user3 = new Usuario("Natalia", 70);

		List<Usuario> usuarios = Arrays.asList(user1, user2, user3);
		System.out.println("============================================");
		
		Consumer<Usuario> mostradorExtenso = new Consumer<Usuario>() {
			@Override
			public void accept(Usuario t) {
				System.out.println(t.getNome());
			}
		};

		usuarios.forEach(mostradorExtenso);
		
		System.out.println("============================================");
		
		usuarios.forEach(new Consumer<Usuario>() {
			@Override
			public void accept(Usuario t) {
				System.out.println(t.getNome());
			}
		});
		
		System.out.println("============================================");
		
		Consumer<Usuario> mostrador = (Usuario u) -> {
			System.out.println(u.getNome());
		};

		usuarios.forEach(mostrador);
		
		System.out.println("============================================");
		
		Consumer<Usuario> mostradorReduzido = u ->{System.out.println(u.getNome());};
		
		usuarios.forEach(mostradorReduzido);
		
		System.out.println("============================================");
		
		Consumer<Usuario> mostradorReduzido2 = u ->System.out.println(u.getNome());
		
		usuarios.forEach(mostradorReduzido2);
	}

}
