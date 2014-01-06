package br.com.casadocodigo.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Capitulo2 {
	
	public static void main(String... args) {
		
		Usuario u1 = new Usuario("Paulo Silveira", 150);
		Usuario u2 = new Usuario("Rodrigo Turini", 120);
		Usuario u3 = new Usuario("Guilherme Silveira", 190);

		List<Usuario> usuarios = Arrays.asList(u1, u2, u3);

		for (Usuario u : usuarios) {
			System.out.println(u.getNome());
		}
		
		usuarios.forEach(new Consumer<Usuario>() {
			public void accept(Usuario p) {
				System.out.println(p.getNome());
			}
		});
		
		usuarios.forEach(u -> System.out.println(u.getNome()));
		
		usuarios.forEach(u -> u.tornaModerador());
		
	}
}