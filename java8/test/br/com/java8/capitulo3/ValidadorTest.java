package br.com.java8.capitulo3;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import br.com.java8.interfaces.Validador;

public class ValidadorTest {

	@Test
	public void sucessoAoValidarCepModoAntigo() {
		
		Validador<String> validadorCep = new Validador<String>() {
			@Override
			public Boolean valida(String cep) {
				return cep.matches("[0-9]{5}[0-9]{3}");
			}
		};
		
		Boolean isCepValido = validadorCep.valida("59370000");
		Boolean isCepInvalido = validadorCep.valida("22222");
		assertThat(isCepInvalido, equalTo(Boolean.FALSE));
		assertThat(isCepValido, equalTo(Boolean.TRUE));
	}
	
	@Test
	public void sucessoAoValidarCepModoLambda() {
		
		Validador<String> validadorCep =
				cep -> cep.matches("[0-9]{5}[0-9]{3}");
				
		Boolean isCepValido = validadorCep.valida("59370000");
		Boolean isCepInvalido = validadorCep.valida("22222");
		assertThat(isCepInvalido, equalTo(Boolean.FALSE));
		assertThat(isCepValido, equalTo(Boolean.TRUE));
	}
	
	
	@Test
	public void testInterfaceFuncinais(){
		
		//Forma mais legivel de se declarar interfaces funcionais no Lamda
		Runnable o = () ->{
			System.out.println("O que sou eu? Que lambda?");
		};
		new Thread(o).start();
	
		//Forma mais opcional de se declarar interfaces funcionais no Lamda
		int numero = 10;
		new Thread(() -> {
			System.out.println(numero);
		}).start();

		System.out.println(o);
		System.out.println(o.getClass());
		assertThat("Classe esperada ValidadorTest", o.getClass(), is(not(instanceOf(Runnable.class))));
	}

}
