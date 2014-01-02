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

		Usuario uu1 = new Usuario("Paulo Silveira", 150);
		Usuario uu2 = new Usuario("Rodrigo Turini", 120);
		Usuario uu3 = new Usuario("Guilherme Silveira", 190);
	
		List<Usuario> usuarios = Arrays.asList(uu1, uu2, uu3);

		for(Usuario u : usuarios) {
			System.out.println(u.getNome());
		}

		Consumer<Usuario> mostrador = new Consumer<Usuario>() {
			public void accept(Usuario p) {
				System.out.println(p.getNome());
			}
		};

		usuarios.forEach(mostrador);

		// paulo 1 (falar de defender methods)
		usuarios.forEach(new Consumer<Usuario>() {
			public void accept(Usuario p) {
				System.out.println(p.getNome());
			}
		});

		// paulo 2
		usuarios.forEach((Usuario u) -> { System.out.println(u.getNome());});
		
		// paulo 3
		//Consumer<Usuario> consumidor = (Usuario u) -> { System.out.println(u.getNome());};

		usuarios.forEach((u) -> { System.out.println(u.getNome());});

		usuarios.forEach(u -> { System.out.println(u.getNome());});

		// paulo 4
		usuarios.forEach(u -> System.out.println(u.getNome()));

		usuarios.stream().peek(System.out::println);


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

		usuarios.stream().filter(p -> p.getPontos() > 50).forEach(p -> p.tornaModerador());

		// rodrigo 7: abusando do method reference
		usuarios.stream().filter(p -> p.getPontos() > 50).forEach(Usuario::tornaModerador);

		usuarios.stream().filter(p -> p.getPontos() > 50).forEach(System.out::println);

		// usando o peek
		usuarios.stream().filter(p -> p.getPontos() > 50).peek(Usuario::tornaModerador).forEach(System.out::println);		

		// ordenar os Usuarios por post (numero de pontos)

		// usando o sort (da propria lista de usuarios) com lambda

		usuarios.sort((Usuario o1, Usuario o2) -> (o1.getPontos() - o2.getPontos()));

		// usando o sort com lambda sem informação do tipo Usuario

		usuarios.sort( (o1, o2) -> (o1.getPontos() - o2.getPontos()));

		// usando o sorted do Stream.. a diferença é que esse cara é lazy, ou 
		// seja retorna um Stream<Usuario> que contem a lista ordenada


		usuarios.sort((u1, u2) -> u1.getNome().compareTo(u2.getNome()));

		usuarios.stream().sorted( (o1, o2) -> (o1.getPontos() - o2.getPontos()));

		
		Collections.sort(usuarios, 
			(u1, u2) -> u1.getNome().compareTo(u2.getNome()));


		Comparator<Usuario> comparator =
			Comparator.comparing(u -> u.getNome());

		usuarios.sort(Comparator.comparing(u -> u.getPontos()));


Function<Usuario, String> extraiNome = u -> u.getNome();
Comparator<Usuario> comparator2 =
	Comparator.comparing(extraiNome);

usuarios.sort(comparator2);

		// É meio obvio, mas acho valido falar que podemos escrever 
		// o compareTo no usuario e usar method reference, algo como:
		// usuarios.sort(Usuario::compareTo) ... fica bem mais simples

		// só um sout pra mostrar que deu certo
		usuarios.forEach(u -> System.out.println("Usuario "+ u.getNome() 
			+ " tem " +u.getPontos() + " Pontos" ));

	}


}
