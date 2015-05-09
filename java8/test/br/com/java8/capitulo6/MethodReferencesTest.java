package br.com.java8.capitulo6;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.Comparator.nullsLast;
import static java.util.Comparator.nullsFirst;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;












import java.nio.channels.InterruptedByTimeoutException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.Supplier;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;

import org.junit.Test;

import br.com.java8.model.Usuario;

public class MethodReferencesTest {

	@Test
	public void sucessoForEachAndThen() {
		
		List<Usuario> usuarios = criarUsuarios();
		//Dez primeiros usuários com mais pontos
		usuarios.sort(comparing(Usuario :: getPontos));
		usuarios.subList(0, 10).forEach(Usuario :: tornaModerador);
		usuarios.forEach(System.out :: println);

		usuarios.forEach(u -> u.tornaModerador());
		usuarios.forEach(Usuario :: tornaModerador);

		usuarios.sort(comparing(u -> u.getNome()));
		usuarios.sort(comparing(Usuario :: getNome));
		
		Function<Usuario, String> byName = Usuario :: getNome;
		usuarios.sort(comparing(byName));

		usuarios.sort(comparingInt(u -> u.getPontos()));
		usuarios.sort(comparingInt(Usuario :: getPontos));
		
		Comparator<Usuario> comparator = Comparator.comparingInt(Usuario :: getPontos).thenComparing(Usuario :: getNome);
		usuarios.sort(comparator);
		System.out.println(usuarios);
		
		usuarios.sort(comparing(Usuario :: getPontos).reversed());
		System.out.println(usuarios);
		assertThat("Valor esperado: ", usuarios.get(0).getNome(), is("Michel"));
		
		usuarios.add(0, null);
		usuarios.add(usuarios.size() - 1, null);

		usuarios.sort(nullsFirst(comparing(Usuario :: getNome)));
		System.out.println(usuarios);
		assertThat("Valor esperado: ", usuarios.get(0), is(nullValue()));
		usuarios.sort(nullsLast(comparing(Usuario :: getPontos)));
		System.out.println(usuarios);
		assertThat("Valor esperado: ", usuarios.get(usuarios.size() - 1), is(nullValue()));
		
		Function<String, Usuario> fabricaUsuario = Usuario::new;
		Usuario brian = fabricaUsuario.apply("Brian");
		usuarios.add(brian);
		BiFunction<String, Integer, Usuario> biBunction = Usuario::new;
		Usuario jose = biBunction.apply("Jose", 123);
		usuarios.add(jose);
		
		Consumer<Usuario> consumer = Usuario :: tornaModerador;
		consumer.accept(jose);
		assertThat("Valor esperado: ", jose.isModerador(), is(Boolean.TRUE));
		usuarios.forEach(System.out :: println);
		
		BiFunction<Integer, Integer, Integer> biMath = Math::max;
		System.out.println(biMath.apply(20, 3));
		assertThat("Valor esperado: ", biMath.apply(20, 3), is(equalTo(20)));
		
		ToIntBiFunction<Integer, Integer> toIntMath = Math::max;
		System.out.println(toIntMath.applyAsInt(33, 334));
		assertThat("Valor esperado: ", toIntMath.applyAsInt(33, 334), is(equalTo(334)));
		
		IntBinaryOperator iboMath = Math::max;
		System.out.println(iboMath.applyAsInt(33, 555));
		assertThat("Valor esperado: ", iboMath.applyAsInt(33, 555), is(equalTo(555)));

		

	}

	private List<Usuario> criarUsuarios() {
		
		BiFunction<String, Integer, Usuario> fabricaUsuarios = Usuario::new;
		
		Usuario userMichel = fabricaUsuarios.apply("Michel", 136);
		Usuario userGrace = fabricaUsuarios.apply("Grace", 33);
		Usuario userAna = fabricaUsuarios.apply("Ana", 100);
		Usuario userMaria = fabricaUsuarios.apply("Maria", 11);
		Usuario userPaulo = fabricaUsuarios.apply("Paulo", 15);
		Usuario userFatima = fabricaUsuarios.apply("Fatima", 177);
		Usuario userJunior = fabricaUsuarios.apply("Junior", 199);
		Usuario userPedro = fabricaUsuarios.apply("Pedro", 155);
		Usuario userJuliana = fabricaUsuarios.apply("Juliana", 144);
		Usuario userJoana = fabricaUsuarios.apply("Joana", 133);
		Usuario userLuiz = fabricaUsuarios.apply("Luiz", 16);
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(userMichel);
		usuarios.add(userGrace);
		usuarios.add(userAna);
		usuarios.add(userMaria);
		usuarios.add(userPaulo);
		usuarios.add(userFatima);
		usuarios.add(userJunior);
		usuarios.add(userPedro);
		usuarios.add(userJuliana);
		usuarios.add(userJoana);
		usuarios.add(userLuiz);
		
		return usuarios;
	}

	
}
