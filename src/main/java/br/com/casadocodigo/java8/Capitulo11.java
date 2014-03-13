package br.com.casadocodigo.java8;

import java.util.*;
import java.math.*;
import java.time.*;
import java.io.*;
import java.nio.file.*;
import java.util.stream.*;
import java.util.function.*;

enum Type {
	MUSIC, 
	VIDEO, 
	IMAGE;
}

class Product {
	private String name;
	private Path file;
	private BigDecimal price;

	public Product(String name, Path file, BigDecimal price) {
		this.name = name;
		this.file = file;
		this.price = price;
	}

	public String getName() {
		return this.name;
	}

	public Path getFile() {
		return this.file;
	}

	public BigDecimal getPrice() {
		return this.price;
	}
}

class Customer {
	private String name;

	public Customer(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}

class Payment {
	private List<Product> products;
	private LocalDateTime date;
	private Customer customer;

	public Payment(List<Product> products, 
					LocalDateTime date,
					Customer customer) {
		this.products = 
			Collections.unmodifiableList(products);
		this.date = date;
		this.customer = customer;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public LocalDateTime getDate() {
		return this.date;
	}

	public Customer customer() {
		return this.customer;
	}

}

class Subscription {
	BigDecimal monthlyFee;
	LocalDateTime begin;
	Optional<LocalDateTime> end;

}


public class Capitulo11 {

	private static long total = 0;

	public static void main (String... args) throws Exception 	{

		List<Payment> payments = null;
		// ordenar List<Payment> por data

		payments.stream()
			.sorted(Comparator.comparing(Payment::getDate))
			.forEach(System.out::println);

		// somatoria de todos os payments

		payments.stream()
			.map(p -> p.getProducts().stream()
							.map(Product::getPrice)
							.reduce(BigDecimal::add))
			.map(o -> o.orElse(BigDecimal.ZERO))
			.reduce(BigDecimal::add);			

		// qual é o produto mais vendido?

		// qual é o usuario que mais pagou

		// que tipo de produto é o mais vendido (sum(VIDEO), sum(AUDIO))

		// quero map<Product, BigDecimal>

		// quero map<MonthYear, Map<Product, BigDecimal>>

		// quero map<Customer, List<Payment>>, list ordenado por horario da compra

		// quero map<Customer, List<Product>>, flatMap

		// quero map<Customer, Type>, qual é o tipo de produto que ele mais comprou

		// quero map<MonthYear, List<Payment>> agrupar por mes as vendas

		// quero map<MonthYear, BigDecimal> total recebido por mes



		Usuario user1 = new Usuario("Paulo Silveira", 150, true);
		Usuario user2 = new Usuario("Rodrigo Turini", 120, true);
		Usuario user3 = new Usuario("Guilherme Silveira", 90);
		Usuario user4 = new Usuario("Sergio Lopes", 120);
		Usuario user5 = new Usuario("Adriano Almeida", 100);

		List<Usuario> usuarios = Arrays.asList(user1, user2, user3, user4, user5);
	}	

}
