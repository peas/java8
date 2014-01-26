package br.com.casadocodigo.java8;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;

class Capitulo8 {
	public static void main (String... args) {

		Usuario user1 = new Usuario("Paulo Silveira", 150);
		Usuario user2 = new Usuario("Rodrigo Turini", 120);
		Usuario user3 = new Usuario("Guilherme Silveira", 90);

		List<Usuario> usuarios = Arrays.asList(user1, user2, user3);

		// peek mostra so os processados:
		usuarios.stream().filter(u -> u.getPontos() > 100)
				.peek(System.out::println).findAny();

		System.out.println();

		// peek é apens intermeridiario:

		usuarios.stream().filter(u -> u.getPontos() > 100)
				.peek(System.out::println);  // precisa chamar terminal!

		System.out.println();

		// sort é operador intermediario, porem stateful:
		usuarios.stream().sorted(Comparator.comparing(Usuario::getNome))
				.peek(System.out::println).findAny();

		System.out.println();

		

		usuarios.stream()
			.sorted(Comparator.comparing(Usuario::getNome))
			.peek(System.out::println)
			.findAny();

		Usuario maximaPontuacao = usuarios.stream()
			.max(Comparator.comparing(Usuario::getPontos))
			.get();
		int total = usuarios.stream()
			.mapToInt(Usuario::getPontos)
			.sum();

		int valorInicial = 0;
		IntBinaryOperator operacao = (a, b) -> a + b;

		int total2 = usuarios.stream()
			.mapToInt(Usuario::getPontos)
			.reduce(valorInicial, operacao);

		int multiplicacao = usuarios.stream()
			.mapToInt(Usuario::getPontos)
			.reduce(1, (a,b) -> a * b);

		// pulando o map!
		int total3 = usuarios.stream()
			.reduce(0, (atual, u) -> atual + u.getPontos(), Integer::sum);	

		Stream<Usuario> s = usuarios.stream();
		s.iterator().next().getPontos();
		s.iterator().next().getPontos();

		boolean hasModerator = usuarios.stream().anyMatch(Usuario::isModerador);

	}
}

