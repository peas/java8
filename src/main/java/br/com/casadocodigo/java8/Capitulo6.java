package br.com.casadocodigo.java8;

import static java.util.Comparator.comparing;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

class Capitulo6 {

	public static void main(String[] args) {
		
		Usuario u1 = new Usuario("Paulo Silveira", 150);
		Usuario u2 = new Usuario("Rodrigo Turini", 120);
		Usuario u3 = new Usuario("Guilherme Silveira", 190);

		List<Usuario> usuarios = Arrays.asList(u1, u2, u3);

		usuarios.forEach(u -> u.tornaModerador());

		usuarios.forEach(Usuario::tornaModerador);

		Consumer<Usuario> tornaModerador = Usuario::tornaModerador;
		usuarios.forEach(tornaModerador);

		//TODO: mesmo do Cap.5, <Usuario, String>
		
		usuarios.sort(Comparator.<Usuario, String>comparing(u -> u.getNome()));
		
		// TODO: com method reference o Target Type funcionou, sem o <Usuario, String>
		
		usuarios.sort(Comparator.comparing(Usuario::getNome));
		
		Function<Usuario, String> byName = Usuario::getNome;
		usuarios.sort(comparing(byName));
		
		usuarios.forEach(System.out::println);
		
		Function<String, Usuario> criadorDeUsuarios = Usuario::new;
		Usuario rodrigo = criadorDeUsuarios.apply("Rodrigo Turini");
		Usuario paulo = criadorDeUsuarios.apply("Paulo Silveira");
		
		BiFunction<String, Integer, Usuario> criadorDeUsuarios2 = Usuario::new;
		Usuario rodrigo2 = criadorDeUsuarios2.apply("Rodrigo Turini", 50);
		Usuario paulo2 = criadorDeUsuarios2.apply("Paulo Silveira", 300);
	}
}
