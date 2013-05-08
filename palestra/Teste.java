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

		usuarios.forEach((Usuario u) -> { System.out.println(u.getNome());});

		usuarios.forEach((u) -> { System.out.println(u.getNome());});

		usuarios.forEach(u -> { System.out.println(u.getNome());});

		usuarios.forEach(u -> System.out.println(u.getNome()));


		// somar numero de posts dos usuarios

		// com lambda, posso usar o mapToInt e chamar o sum

		usuarios.stream().mapToInt(u -> u.getPontos()).sum();

		// a mesma coisa só que com method reference

		int soma = usuarios.stream().mapToInt(Usuario::getPontos).sum();

		System.out.println("soma dos pontos: "+ soma);


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



		// ordenar os Usuarios por post (numero de pontos)

		// usando o sort (da propria lista de usuarios) com lambda

		usuarios.sort((Usuario u1, Usuario u2) -> (u1.getPontos() - u2.getPontos()));

		// usando o sort com lambda sem informação do tipo Usuario

		usuarios.sort( (u1, u2) -> (u1.getPontos() - u2.getPontos()));

		// usando o sorted do Stream.. a diferença é que esse cara é lazy, ou 
		// seja retorna um Stream<Usuario> que contem a lista ordenada

		usuarios.stream().sorted( (u1, u2) -> (u1.getPontos() - u2.getPontos()));

		// É meio obvio, mas acho valido falar que podemos escrever 
		// o compareTo no usuario e usar method reference, algo como:
		// usuarios.sort(Usuario::compareTo) ... fica bem mais simples

		// só um sout pra mostrar que deu certo
		usuarios.forEach(u -> System.out.println("Usuario "+ u.getNome() 
			+ " tem " +u.getPontos() + " Pontos" ));
	}


}
