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

		
		// TODO: corrigir o texto (talvez explicar o porque...)
		// não funciona com (u1, u2) por causa do escopo
		
		Comparator<Usuario> comparatorComLambda = 
			  (u1, u2) -> u1.getNome().compareTo(u2.getNome());

		Collections.sort(usuarios, comparator);
		
		Collections.sort(usuarios, 
				(u1, u2) -> u1.getNome().compareTo(u2.getNome()));
		
		usuarios.sort((u1, u2) -> u1.getNome().compareTo(u2.getNome()));
		
		// TODO: porque não compila sem o <Usuario, String> ?
		// na spec fala que ele infere o primeiro argumento do tipo Comparator<Usuario>
		// e o segundo argumento do retorno do método u.getNome()
		
		Comparator<Usuario> comparator22 =
				Comparator.comparing(Usuario::getNome)
					.thenComparing(Usuario::getNome);

		usuarios.sort(Comparator.comparing(u -> u.getPontos()));


		//usuarios.sort(Comparator.comparing(u -> u.getNome())
		//			.thenComparing(u -> u.getPontos()));




		List<String> list = Collections.emptyList();
		list.sort(
		        Comparator.comparing((String s)->s.toString())
        			.thenComparing(s -> s.length()));


		Function<Usuario, Integer> extraiPontos = u -> u.getPontos();
		Comparator<Usuario> comparator3 =
			Comparator.comparing(extraiPontos);
		usuarios.sort(comparator3);


		ToIntFunction<Usuario> extraiPontos2 = u -> u.getPontos();
		Comparator<Usuario> comparator4 =
			Comparator.comparingInt(extraiPontos2);
		usuarios.sort(comparator4);

		usuarios.sort(Comparator.comparingInt(u -> u.getPontos()));

	//	usuarios.sort(Comparator.<String>comparing((String s) -> s.toString())
	//								.thenComparing((String s) -> s.length()));


		//usuarios.sort(Comparator.comparing(String::toString)
		//							.thenComparing(String::length));





//		Comparator<Usuario> c = Comparator.comparing(u -> u.toString())
//									.thenComparing(u -> u.toString());


	}
}
