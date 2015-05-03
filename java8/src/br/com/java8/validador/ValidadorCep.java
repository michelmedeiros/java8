package br.com.java8.validador;

import br.com.java8.interfaces.Validador;

public class ValidadorCep implements Validador<String>{

	@Override
	public Boolean valida(String cep) {
		return cep.matches("[0-9]{5}[0-9]{3}");
	}

}
