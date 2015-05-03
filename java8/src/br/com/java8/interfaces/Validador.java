package br.com.java8.interfaces;

@FunctionalInterface
public interface Validador<T> {
	
	Boolean valida(T t);

}
