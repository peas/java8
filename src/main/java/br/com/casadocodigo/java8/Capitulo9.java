package br.com.casadocodigo.java8;

import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.util.stream.*;
import java.util.function.*;


class Capitulo9 {

	private static long total = 0;

	public static void main (String... args) throws Exception 	{

		LongStream lines = 
			Files.list(Paths.get("./br/com/casadocodigo/java8"))
				.filter(p -> p.toString().endsWith(".java"))
				.mapToLong(p -> lines(p).count());

		List<Long> lines2 = 
			Files.list(Paths.get("./br/com/casadocodigo/java8"))
				.filter(p -> p.toString().endsWith(".java"))
				.map(p -> lines(p).count())
				.collect(Collectors.toList());	

		{
			Map<Path, Long> linesPerFile =  new HashMap<>();
			Files.list(Paths.get("./br/com/casadocodigo/java8"))
				.filter(p -> p.toString().endsWith(".java"))
				.forEach(p -> 
					linesPerFile.put(p, lines(p).count()));
			System.out.println(linesPerFile);
				
		}
		Map<Path, Long> linesPerFile = 
			Files.list(Paths.get("./br/com/casadocodigo/java8"))
				.filter(p -> p.toString().endsWith(".java"))
				.collect(Collectors.toMap(
						Function.identity(), 
						p -> lines(p).count()));

		System.out.println(linesPerFile);



		Map<Path, List<String>> content = 
			Files.list(Paths.get("./br/com/casadocodigo/java8"))
				.filter(p -> p.toString().endsWith(".java"))
				.collect(Collectors.toMap(
						p -> p, 
						p -> lines(p).collect(Collectors.toList())));




		Usuario user1 = new Usuario("Paulo Silveira", 150, true);
		Usuario user2 = new Usuario("Rodrigo Turini", 120, true);
		Usuario user3 = new Usuario("Guilherme Silveira", 90);
		Usuario user4 = new Usuario("Sergio Lopes", 120);
		Usuario user5 = new Usuario("Adriano Almeida", 100);

		List<Usuario> usuarios = Arrays.asList(user1, user2, user3, user4, user5);

		Map<String, Usuario> nameToUser = usuarios
			.stream()
			.collect(Collectors.toMap(
						Usuario::getNome, 
						Function.identity()));
		System.out.println(nameToUser);


		Map<Integer, List<Usuario>> pontuacaoVelha = new HashMap<>();
		
		for(Usuario u: usuarios) {
			if(!pontuacaoVelha.containsKey(u.getPontos())) {
				pontuacaoVelha.put(u.getPontos(), new ArrayList<>());
			}
			pontuacaoVelha.get(u.getPontos()).add(u);
		}

		System.out.println(pontuacaoVelha);		

		Map<Integer, List<Usuario>> pontuacaoJ8 = new HashMap<>();
		
		for(Usuario u: usuarios) {
			pontuacaoJ8
				.computeIfAbsent(u.getPontos(), user -> new ArrayList<>())
				.add(u);
		}

		System.out.println(pontuacaoJ8);		


		Map<Integer, List<Usuario>> pontuacao = usuarios
			.stream()
			.collect(Collectors.groupingBy(Usuario::getPontos));

		System.out.println(pontuacao);

		Map<Boolean, List<Usuario>> moderadores = usuarios
		 	.stream()
		 	.collect(Collectors.partitioningBy(Usuario::isModerador));

		System.out.println(moderadores);

		Map<Boolean, Integer> pontuacaoPorTipo = usuarios
		 	.stream()
            .collect(Collectors.partitioningBy(u -> u.isModerador(),
            	Collectors.summingInt(Usuario::getPontos)));

		System.out.println(pontuacaoPorTipo);

		Map<Boolean, List<String>> nomesPorTipo = usuarios
		 	.stream()
            .collect(Collectors.partitioningBy(u -> u.isModerador(),
            	Collectors.mapping(Usuario::getNome, Collectors.toList())));

		System.out.println(nomesPorTipo);



		// PARALLEL

		List<Usuario> filtradosOrdenados = usuarios.parallelStream()
			.filter(u -> u.getPontos() > 100)
			.sorted(Comparator.comparing(Usuario::getNome))
			.collect(Collectors.toList());

		long sum = 
			LongStream.range(0, 1_000_000_000)
			.filter(x -> x % 2 == 0)
			.parallel()
			.sum();
		System.out.println(sum);

	}

	static Stream<String> lines(Path p) {
		try {
			return Files.lines(p);
		} catch(IOException e) {
			throw new UncheckedIOException(e);
		}
	}

}

class UnsafeParallelStreamUsage {
	private static long total = 0;

	public static void main (String... args) throws Exception 	{
		LongStream.range(0, 1_000_000_000)
			.parallel()
			.filter(x -> x % 2 == 0)
			.forEach(n -> total += n);

		System.out.println(total);
	}
}
