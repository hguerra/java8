package br.com.cap3;

public class InterfaceFuncional {
	public static void main(String[] args) {
		String cepInvalido = "04101300";

		String cepValido = "04101-300";

		Validador<String> validadorCEP = new Validador<String>() {
			public boolean valida(String valor) {
				return valor.matches("[0-9]{5}-[0-9]{3}");
			}
		};

		System.out.println("Invalido:" + validadorCEP.valida(cepInvalido));

		System.out.println("Valido:" + validadorCEP.valida(cepValido));

		System.out.println("===========================================");

		Validador<String> validadorCEPLambda = valor -> valor.matches("[0-9]{5}-[0-9]{3}");
		
		System.out.println("Invalido:" + validadorCEPLambda.valida(cepInvalido));

		System.out.println("Valido:" + validadorCEPLambda.valida(cepValido));
		
		System.out.println("===========================================");
		
		
		
	}
	
	

}
