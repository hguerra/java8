package br.com.cap3;

public class Variaveis {
	public static void main(String[] args) {

		/**
		 * Assim como numa classe anônima local, você também pode acessar as
		 * variáveis finais do método a qual você pertence.
		 * 
		 * ela deve ser efetivamente final. Isso é, apesar de não precisar
		 * declarar as variáveis locais como final, você não pode alterá-las se
		 * estiver utilizando-as dentro do lambda.
		 * 
		 * Você não precisa mais declarar as variáveis locais como final
		 */
		int numero = 5;

		new Thread(() -> {
			System.out.println(numero);
		}).start();

		// numero = 10; // nao compila por causa dessa linha!
	}

}
