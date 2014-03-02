package br.com.casadocodigo.java8;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;

class Capitulo9 {
	public static void main (String... args) {
		Usuario user1 = new Usuario("Paulo Silveira", 150, true);
		Usuario user2 = new Usuario("Rodrigo Turini", 120, true);
		Usuario user3 = new Usuario("Guilherme Silveira", 90);
		Usuario user4 = new Usuario("Sergio Lopes", 120);
		Usuario user5 = new Usuario("Adriano Almeida", 100);

		List<Usuario> usuarios = Arrays.asList(user1, user2, user3, user4, user5);

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
            	Collectors.mapping(Usuario::getNome)));

		System.out.println(nomesPorTipo);

		

		List<Uusario> usuarioss = usuarios.stream()
			.filter(u -> u.getPontos() > 100)
			.reduce(new ArrayList<Usuario>(), (l1, l2) -> {l1.addAll(l2); return l1;});
		
	}
}

