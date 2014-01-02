import java.util.*;
import java.util.function.*;
import java.util.stream.*;

class Usuario {
  private int pontos;
  private String nome;
  private boolean moderador;

  public Usuario(String nome, int pontos) {
  	this.pontos = pontos;
  	this.nome = nome;
  }

  public int getPontos() { 
  	return pontos; 
  }
  
  public String getNome() {
  	return nome;
  }

  public void tornaModerador() {
  	this.moderador = true;
  }
}

public class Capitulo2 {
	public static void main(String ... args) {
		Usuario u1 = new Usuario("Paulo Silveira", 150);
		Usuario u2 = new Usuario("Rodrigo Turini", 120);
		Usuario u3 = new Usuario("Guilherme Silveira", 190);
	
		List<Usuario> usuarios = Arrays.asList(u1, u2, u3);

		for(Usuario u : usuarios) {
			System.out.println(u.getNome());
		}

		// Consumer<Usuario> c = Usuario::new;

		usuarios.forEach(System.out::println);

		int soma = usuarios.stream().mapToInt(Usuario::getPontos).sum();

		System.out.println("soma" +soma);


		// favoritar quem tem mais de 50 pontos:

		usuarios.stream().filter(u -> u.getPontos() > 50)
				.forEach(Usuario::favoritar);


		// comparator!

		usuarios.sort(Usuario::compareUsandoPontos);















	}


}
