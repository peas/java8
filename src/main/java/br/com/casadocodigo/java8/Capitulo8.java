package br.com.casadocodigo.java8;

import java.util.*;
import java.nio.file.*;
import java.util.stream.*;
import java.util.function.*;

class Capitulo8 {
	public static void main (String... args) throws Exception {

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


		boolean hasModerator = usuarios.stream().anyMatch(Usuario::isModerador);


		// flatmap e java.nio.files

		Files.list(Paths.get("."))
			.forEach(System.out::println);

		Files.list(Paths.get("."))
			.filter(Files::isDirectory)
			.forEach(System.out::println);

		Files.list(Paths.get("."))
			.flatMap(p -> Files.isDirectory(p) ? 
						Files.list(p) :
						Stream.of(p))
			.forEach(System.out::println);

		// stream infinito:

		Random random = new Random(0);
		IntStream stream = IntStream.generate(() -> random.nextInt());

		// loop infinito
		//int valor = stream.sum();

		List<Integer> list = IntStream
			.generate(() -> random.nextInt())
			.limit(100)
			.boxed()
			.collect(Collectors.toList());

		class Fibonacci implements IntSupplier {
			private int anterior = 0;
			private int proximo = 1;

			public int getAsInt() {
				proximo = proximo + anterior;
				anterior = proximo - anterior;
				return anterior;
			}
		}

		IntStream.generate(new Fibonacci())
			.limit(10)
			.forEach(System.out::println);

		int maiorQue100 = IntStream
			.generate(new Fibonacci())
			.filter(f -> f > 100)
			.findFirst()
			.getAsInt();

		System.out.println(maiorQue100);

		IntStream.iterate(0, x -> x + 1)
			.limit(10)
			.forEach(System.out::println);		




	}
}

