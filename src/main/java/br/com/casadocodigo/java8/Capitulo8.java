package br.com.casadocodigo.java8;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;

class Capitulo8 {
	public static void main (String... args) {

		Usuario u1 = new Usuario("Paulo Silveira", 150);
		Usuario u2 = new Usuario("Rodrigo Turini", 120);
		Usuario u3 = new Usuario("Guilherme Silveira", 90);

		List<Usuario> usuarios = Arrays.asList(u1, u2, u3);

		Comparator<Usuario> bomba = new Comparator<Usuario>() {
			@Override
			public int compare(Usuario u1, Usuario u2) {
				throw new IllegalStateException();
			}
		};

		usuarios.stream().filter(u -> u.getPontos() > 100)
				.peek(System.out::println).findAny();

	}
}

