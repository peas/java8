package br.com.casadocodigo.java8;

import java.util.*;
import java.math.*;
import java.time.*;
import java.io.*;
import java.nio.file.*;
import java.util.stream.*;
import java.util.function.*;

enum Type {
	MUSIC, VIDEO, IMAGE;
}

class Product {
	BigDecimal price;
	String name;
	String file;
}

class Payment {
	List<Product> products;
	//DateTime

}

class Subscription {
	BigDecimal monthlyFee;
	LocalDateTime begin;
	Optional<LocalDateTime> end;

}


public class Capitulo11 {

	private static long total = 0;

	public static void main (String... args) throws Exception 	{

		Usuario user1 = new Usuario("Paulo Silveira", 150, true);
		Usuario user2 = new Usuario("Rodrigo Turini", 120, true);
		Usuario user3 = new Usuario("Guilherme Silveira", 90);
		Usuario user4 = new Usuario("Sergio Lopes", 120);
		Usuario user5 = new Usuario("Adriano Almeida", 100);

		List<Usuario> usuarios = Arrays.asList(user1, user2, user3, user4, user5);
	}	

}
