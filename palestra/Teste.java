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

		// paulo 1 (falar de defender methods)
		usuarios.forEach(new Consumer<Usuario>() {
			public void accept(Usuario p) {
				System.out.println(p.getNome());
			}
		});

		// paulo 2
		usuarios.forEach((Usuario u) -> { System.out.println(u.getNome());});
		
		// paulo 3
		Consumer<Usuario> consumidor = (Usuario u) -> { System.out.println(u.getNome());};

		usuarios.forEach((u) -> { System.out.println(u.getNome());});

		usuarios.forEach(u -> { System.out.println(u.getNome());});

		// paulo 4
		usuarios.forEach(u -> System.out.println(u.getNome()));


		// somar numero de posts dos usuarios

		// com lambda, posso usar o mapToInt e chamar o sum

		// rodrigo 1 (Falar do streamificacao da api)
		usuarios.stream().mapToInt(u -> u.getPontos()).sum();

		Stream<String> nomes = usuarios.stream().map(Usuario::getNome);

		// a mesma coisa só que com method reference
		// rodrigo 2, falar do method reference
		int soma = usuarios.stream().mapToInt(Usuario::getPontos).sum();

		System.out.println("soma dos pontos: "+ soma);


		// todos que tem mais de 50 posts, favoritar!
		// rodrigo 3, falando que stream tem muitos outros metodos!
		Stream<Usuario> stream = usuarios.stream().filter(
			new Predicate<Usuario>() {
				public boolean test(Usuario p) {
					return p.getPontos() > 50;
				}
			}
		);

		// rodrigo4: problema, eu nao queria Stream! eu queria List!

		List<Usuario> filtrados = usuarios.stream().filter(
			new Predicate<Usuario>() {
				public boolean test(Usuario p) {
					return p.getPontos() > 50;
				}
			}
		).collect(Collectors.toList());

		// rodrigo 5: explicar que .stream e .collect sao complementares, a ida e a volta!
		// rodrigo 6: com lambda:
		List<Usuario> filtrados2 = usuarios.stream().filter(p -> p.getPontos() > 50).collect(Collectors.toList());

		usuarios.stream().filter(p -> p.getPontos() > 50).forEach(p -> p.favoritar());

		// rodrigo 7: abusando do method reference
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
