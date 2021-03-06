package br.com.java8.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import br.com.java8.model.Usuario;

public class UsuarioTestUtil {

	
	public static List<Usuario> criarUsuarios() {

		BiFunction<String, Integer, Usuario> fabricaUsuarios = Usuario::new;

		Usuario userMichel = new Usuario("Michel", 136, true);
		Usuario userGrace = new Usuario("Grace", 33, true);
		Usuario userAna = fabricaUsuarios.apply("Ana", 100);
		Usuario userMaria = fabricaUsuarios.apply("Maria", 11);
		Usuario userPaulo = fabricaUsuarios.apply("Paulo", 15);
		Usuario userFatima = fabricaUsuarios.apply("Fatima", 177);
		Usuario userJunior = fabricaUsuarios.apply("Junior", 199);
		Usuario userPedro = fabricaUsuarios.apply("Pedro", 136);
		Usuario userJuliana = fabricaUsuarios.apply("Juliana", 144);
		Usuario userJoana = fabricaUsuarios.apply("Joana", 33);
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

		return usuarios;
	}

}

