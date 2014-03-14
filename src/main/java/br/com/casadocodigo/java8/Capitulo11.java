package br.com.casadocodigo.java8;

import static java.util.Arrays.asList;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

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
	
	private BigDecimal monthlyFee;
	private LocalDateTime begin;
	private Optional<LocalDateTime> end;
	private Customer customer;
	
	public Subscription(BigDecimal monthlyFee, LocalDateTime begin,
			Optional<LocalDateTime> end, Customer customer) {
		this.monthlyFee = monthlyFee;
		this.begin = begin;
		this.end = end;
		this.customer = customer;
	}

	public BigDecimal getMonthlyFee() {
		return monthlyFee;
	}

	public LocalDateTime getBegin() {
		return begin;
	}

	public Optional<LocalDateTime> getEnd() {
		return end;
	}

	public Customer getCustomer() {
		return customer;
	}
}


public class Capitulo11 {

	private static long total = 0;

	public static void main (String... args) throws Exception 	{

		Customer paulo = new Customer("Paulo Silveira");
		Customer rodrigo = new Customer("Rodrigo Turini");
		Customer guilherme = new Customer("Guilherme Silveira");
		Customer adriano = new Customer("Adriano Almeida");
		
		Product p1 = new Product("P1", Paths.get(""), new BigDecimal(100));
		Product p2 = new Product("P2", Paths.get(""), new BigDecimal(89.90));
		Product p3 = new Product("P3", Paths.get(""), new BigDecimal(50));
		Product p4 = new Product("P4", Paths.get(""), new BigDecimal(150.99));
		Product p5 = new Product("P5", Paths.get(""), new BigDecimal(200));
		Product p6 = new Product("P6", Paths.get(""), new BigDecimal(180));
		
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime yesterday = today.minusDays(1);
		LocalDateTime tomorrow = today.plusDays(1);
		
		Payment payment1 = new Payment(asList(p1, p2), today, paulo);
		Payment payment2 = new Payment(asList(p3, p1, p6), yesterday, rodrigo);
		Payment payment3 = new Payment(asList(p4, p5, p1), today, adriano);
		Payment payment4 = new Payment(asList(p6, p1, p2), tomorrow, guilherme);
		
		List<Payment> payments = asList(payment1, payment2, payment3, payment4);
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
		
		Map<Product, Long> collect = payments.stream()
			.map(Payment::getProducts)
			.flatMap(p -> p.stream())
			.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		
		System.out.println(collect);
		
		// qual é o usuario que mais pagou

		// que tipo de produto é o mais vendido (sum(VIDEO), sum(AUDIO))

		// quero map<Product, BigDecimal>
		
		payments.stream()
			.map(Payment::getProducts)
			.flatMap(p -> p.stream())
			.collect(Collectors.groupingBy(Function.identity(), 
					 Collectors.reducing(BigDecimal.ZERO, Product::getPrice, BigDecimal::add)));

		
		// quero map<MonthYear, Map<Product, BigDecimal>>

		// quero map<Customer, List<Payment>>, list ordenado por horario da compra
		
		//Map<Customer, List<Payment>>
		payments.stream()
//			.sorted(Comparator.comparing(Payment::getDate)) // pela pela data
			.sorted(Comparator.comparing(p -> p.getDate().toLocalTime())) // pela hora
			.collect(Collectors.groupingBy(Payment::customer))
			.entrySet()
			.forEach(System.out::println);
		
		// quero map<Customer, List<Product>>, flatMap

		Map<Customer, List<List<Product>>> mapa = payments.stream() 
		// como eu faço o flatmap dentro do Collectors.mapping ?
		.collect(Collectors.groupingBy(Payment::customer, 
				Collectors.mapping(Payment::getProducts, Collectors.toList())));
		
		
		// quero map<Customer, Type>, qual é o tipo de produto que ele mais comprou

		// quero map<MonthYear, List<Payment>> agrupar por mes as vendas

		// quero map<MonthYear, BigDecimal> total recebido por mes





		// subscriptions

		// dada uma unica subscription, calcular quanto ele pagou ate hoje
			// algo como calculaMesesEntre(subscription.getBegin(), subscription.getEnd().orElse(today))
			// e ai multiplica pelo mnthlyFee

		// dada uma colecao de subscription, calcular quanto todos pagaram ate hoje
		
		// achar o usuario que mais pagou total de valor de subscriptions ate hoje	
		// achar o usuario que ficou mais meses pagando (independente dela estar ativa ou nao)

	}	

}
