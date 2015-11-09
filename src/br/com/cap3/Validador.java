package br.com.cap3;

@FunctionalInterface
public interface Validador<T> {
	boolean valida(T t);
}
