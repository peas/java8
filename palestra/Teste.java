import java.util.*;
import java.util.function.*;
import java.util.stream.*;

class Usuario {
  private int pontos;
  private String nome;
  private boolean favorito;

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

  public void favoritar() {
  	this.favorito = true;
  }
}

public class Teste {


	public static void main(String ... args) {
		Usuario p1 = new Usuario("Paulo Silveira", 150);
		Usuario p2 = new Usuario("Rodrigo Turini", 120);
		Usuario p3 = new Usuario("Alexandre Freire", 35);


		List<Usuario> usuarios = Arrays.asList(p1, p2, p3);

		usuarios.forEach(new Consumer<Usuario>() {
			public void accept(Usuario p) {
				System.out.println(p.getNome());
			}
		});

		usuarios.forEach((p) -> { System.out.println(p.getNome());});

		usuarios.forEach(p -> { System.out.println(p.getNome());});

		usuarios.forEach(p -> System.out.println(p.getNome()));

		// somar numero de posts





		// todos que tem mais de 50 posts, favoritar!

		Stream<Usuario> stream = usuarios.stream().filter(
			new Predicate<Usuario>() {
				public boolean test(Usuario p) {
					return p.getPontos() > 50;
				}
			}
		);

		List<Usuario> filtrados = usuarios.stream().filter(
			new Predicate<Usuario>() {
				public boolean test(Usuario p) {
					return p.getPontos() > 50;
				}
			}
		).collect(Collectors.toList());

		List<Usuario> filtrados2 = usuarios.stream().filter(p -> p.getPontos() > 50).collect(Collectors.toList());

		usuarios.stream().filter(p -> p.getPontos() > 50).forEach(p -> p.favoritar());

		usuarios.stream().filter(p -> p.getPontos() > 50).forEach(Usuario::favoritar);

		usuarios.stream().filter(p -> p.getPontos() > 50).forEach(System.out::println);

		// ordenar por post

		// 4 coisas:	

	}


}