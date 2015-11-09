package br.com.cap3;

public class Capitulo03 {
	/**
	 * Lambda
	 * 
	 * NomeClasse variavel = (entrada) -> {metodo direto};
	 */

	public static void main(String[] args) {
		System.out.println("====================================");

		Runnable r = new Runnable() {
			public void run() {
				for (int i = 0; i <= 10; i++) {
					System.out.println(i);
				}
			}
		};
		new Thread(r).start();

		System.out.println("====================================");

		Runnable rLambda1 = () -> {
			for (int i = 0; i <= 10; i++) {
				System.out.println(i);
			}
		};
		new Thread(rLambda1).start();

		System.out.println("====================================");

		new Thread(() -> {
			for (int i = 0; i <= 10; i++) {
				System.out.println(i);
			}
		}).start();

		/**
		 * listeners
		 */

//		button.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("evento do click acionado");
//			}
//		});

	}
	
	//Lambda
	
//	button.addActionListener(
//			event -> System.out.println("evento do click acionado"));

}
