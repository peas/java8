package br.com.casadocodigo.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

class Capitulo5 {

	public static void main(String[] args) {
		
		Usuario user1 = new Usuario("Paulo Silveira", 150);
		Usuario user2 = new Usuario("Rodrigo Turini", 120);
		Usuario user3 = new Usuario("Guilherme Silveira", 190);
		
		List<Usuario> usuarios = Arrays.asList(user1, user2, user3);
		
		Comparator<Usuario> comparator = new Comparator<Usuario>() {
			public int compare(Usuario u1, Usuario u2) {
				return u1.getNome().compareTo(u2.getNome());
			}	
		};
		
		Collections.sort(usuarios, comparator);

		usuarios.sort(comparator);

		Comparator<Usuario> comparatorComLambda = 
			  (u1, u2) -> u1.getNome().compareTo(u2.getNome());

		Collections.sort(usuarios, comparator);
		
		Collections.sort(usuarios, 
				(u1, u2) -> u1.getNome().compareTo(u2.getNome()));
		
		usuarios.sort((u1, u2) -> u1.getNome().compareTo(u2.getNome()));
		

		usuarios.sort(Comparator.comparing(u -> u.getNome()));	

		usuarios.sort(Comparator.comparing(u -> u.getPontos()));

		List<String> list = Collections.emptyList();
		list.sort(Comparator.comparing((String s)->s.toString())
        			.thenComparing(s -> s.length()));

		Function<Usuario, Integer> extraiPontos = u -> u.getPontos();
		Comparator<Usuario> comparator3 = Comparator.comparing(extraiPontos);
		usuarios.sort(comparator3);


		ToIntFunction<Usuario> extraiPontos2 = u -> u.getPontos();
		Comparator<Usuario> comparator4 = Comparator.comparingInt(extraiPontos2);
		usuarios.sort(comparator4);

		usuarios.sort(Comparator.comparingInt(u -> u.getPontos()));

		// ordem natural:
		List<String> palavras = 
    		Arrays.asList("Casa do Código", "Alura", "Caelum");

    	palavras.sort(Comparator.naturalOrder());
    	palavras.sort(Comparator.reverseOrder());

	}
}
