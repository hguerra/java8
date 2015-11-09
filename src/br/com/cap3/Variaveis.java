package br.com.cap3;

public class Variaveis {
	public static void main(String[] args) {

		/**
		 * Assim como numa classe an�nima local, voc� tamb�m pode acessar as
		 * vari�veis finais do m�todo a qual voc� pertence.
		 * 
		 * ela deve ser efetivamente final. Isso �, apesar de n�o precisar
		 * declarar as vari�veis locais como final, voc� n�o pode alter�-las se
		 * estiver utilizando-as dentro do lambda.
		 * 
		 * Voc� n�o precisa mais declarar as vari�veis locais como final
		 */
		int numero = 5;

		new Thread(() -> {
			System.out.println(numero);
		}).start();

		// numero = 10; // nao compila por causa dessa linha!
	}

}
