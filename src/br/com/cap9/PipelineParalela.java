package br.com.cap9;

import java.util.stream.LongStream;

public class PipelineParalela {
	public static void main(String[] args) {

		long sum = LongStream.range(0, 1_000_000_000).parallel().filter(x -> x % 2 == 0).sum();
		System.out.println(sum);
		
		/**
		 * Operações não determinísticas e ordered streams
		 */
		
		

	}
}
