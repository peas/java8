package br.com.casadocodigo.java8;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;

class Capitulo7 {
	public static void main (String... args) {

		Usuario u1 = new Usuario("Paulo Silveira", 150);
		Usuario u2 = new Usuario("Rodrigo Turini", 120);
		Usuario u3 = new Usuario("Guilherme Silveira", 90);

		List<Usuario> usuarios = Arrays.asList(u1, u2, u3);

		usuarios.sort(Comparator.comparing(Usuario::getPontos));
		usuarios.subList(0,10).forEach(Usuario::tornaModerador);

		Collections.sort(usuarios, new Comparator<Usuario>() {
			@Override
			public int compare(Usuario u1, Usuario u2) {
				return u1.getPontos() - u2.getPontos();
			}
		});
		List<Usuario> top10 = usuarios.subList(0, 10);
		for(Usuario usuario : top10) {
			usuario.tornaModerador();
		}

		Stream<Usuario> stream = usuarios.stream()
			.filter(u -> u.getPontos() > 100);
	
		stream.forEach(System.out::println);

		Supplier<ArrayList<Usuario>> supplier = ArrayList::new;
		BiConsumer<ArrayList<Usuario>, Usuario> accumulator = ArrayList::add;
		BiConsumer<ArrayList<Usuario>,ArrayList<Usuario>> combiner = ArrayList::addAll;

		usuarios.stream().filter(u -> u.getPontos() > 100)
			.collect(supplier, accumulator, combiner);

		usuarios.stream().filter(u -> u.getPontos() > 100)
			.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

		List<Usuario> maisQue100 = usuarios.stream()
			.filter(u -> u.getPontos() > 100).collect(Collectors.toList());

		ArrayList<Integer> pontos = new ArrayList<>();
		usuarios.forEach(u -> pontos.add(u.getPontos()));

		List<Integer> pontos2 = usuarios.stream()
			.map(u -> u.getPontos()).collect(Collectors.toList());

		List<Integer> pontos3 = usuarios.stream()
			.map(Usuario::getPontos).collect(Collectors.toList());

		Stream<Integer> stream2 = usuarios.stream().map(Usuario::getPontos);

		IntStream stream3 = usuarios.stream().mapToInt(Usuario::getPontos);

		double pontuacaoMedia = usuarios.stream()
			.mapToInt(Usuario::getPontos).average().getAsDouble();
	}
}

