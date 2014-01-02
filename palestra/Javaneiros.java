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

public class Javaneiros {


	public static void main(String ... args) {

		Usuario p1 = new Usuario("Paulo Silveira", 150);
		Usuario p2 = new Usuario("Gilliard Cordeiro", 120);
		Usuario p3 = new Usuario("Giovanni Bassi", 350);


    List<Usuario> x = Arrays.asList(p1, p2, p3);

    // foreach de usuarios com Consumer e Accept




    x.forEach((Usuario u) -> {System.out.println(u.getNome());});


    // lambda e transformacao do Consumer

    x.forEach(System.out::println);

    // Extension/Virtual Methods

    // lambda sem tipo, parenteses


    // Runnable!

    new Thread(() -> {}).start();

    new Thread(new Runnable() {
      public void run() {

      }
    }).start();


    // mapear para nomes dos usuarios. streamificacao

    Stream<String>  x2= x.stream().map(u -> u.getNome());


    // method reference pro getNome


    Stream<String>  x3= x.stream().map(Usuario::getNome);

    // methos reference pro Sysout.

    // total de pontos por usuario


    // usuarios com mais de 50 pontos. filter, predicate e test 
    // devolvendo Stream

    List<Usuario> ll = x.stream().filter(u -> u.getPontos() > 50).
              collect(Collectors.toList());


    // collect e Collectos toList

    // favoritando todos os usuarios com mais de 50 pontos


      x.stream().filter(u -> u.getPontos() > 300).
                          peek(Usuario::favoritar).forEach(
                            u -> System.out.println(u.getNome()));



    // ordenando uma lista 20 anos depois do Java 1.

    x.sort(Comparators.comparing(Usuario::getPontos));




  }
}
