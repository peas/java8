package br.com.casadocodigo.java8;

import static java.util.Comparator.comparing;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

class Capitulo12 {

	public static void main(String[] args) {
		
		Usuario u1 = new Usuario("Paulo Silveira", 150);
		Usuario u2 = new Usuario("Rodrigo Turini", 120);
		Usuario u3 = new Usuario("Guilherme Silveira", 190);

		List<Usuario> usuarios = Arrays.asList(u1, u2, u3);

        // limitacoes da inferencia

        // necessidade de tipagem para inferencia correta:
        usuarios.sort(Comparator.comparingInt((Usuario u) -> u.getPontos())
                            .thenComparing(u -> u.getNome()));

        usuarios.sort(Comparator.<Usuario, Integer>comparing(u -> u.getPontos()).reversed());	
  


	}
}