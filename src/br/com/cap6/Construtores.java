package br.com.cap6;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.Supplier;
import java.util.function.ToIntBiFunction;

import br.com.modelo.Usuario;

public class Construtores {
	public static void main(String[] args) {
		Supplier<Usuario> criadorDeUsuarios = Usuario::new;
		Usuario novo = criadorDeUsuarios.get();
		novo.setNome("Heitor");
		System.out.println(novo);
		// --------------------------------------------------------------
		Function<String, Usuario> criadorDeUsuariosComArgumento = Usuario::new;

		Usuario heitor = criadorDeUsuariosComArgumento.apply("Heitor");

		System.out.println(heitor);
		// --------------------------------------------------------------
		BiFunction<String, Integer, Usuario> criadorDeUsuariosComArgumento2 = Usuario::new;

		Usuario heitor2 = criadorDeUsuariosComArgumento2.apply("Heitor", 100);

		System.out.println(heitor2);
		// --------------------------------------------------------------
		BiFunction<Integer, Integer, Integer> max = Math::max;
		ToIntBiFunction<Integer, Integer> max2 = Math::max;
		IntBinaryOperator max3 = Math::max;
	}
}
