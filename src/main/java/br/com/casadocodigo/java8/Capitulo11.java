package br.com.casadocodigo.java8;

import static java.util.Arrays.asList;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.function.Function;

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

	public String toString() {
		return this.name;
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

	public String toString() {
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

	public Customer getCustomer() {
		return this.customer;
	}

	public String toString() {
		return "[Payment: " + 
			date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
			" " + customer + " " + products + "]";
	}
}

class Subscription {
	private BigDecimal monthlyFee;
	private LocalDateTime begin;
	private Optional<LocalDateTime> end;
	private Customer customer;
	
	public Subscription(BigDecimal monthlyFee, LocalDateTime begin,
			 Customer customer) {
		this.monthlyFee = monthlyFee;
		this.begin = begin;
		this.end = Optional.empty();
		this.customer = customer;
	}

	public Subscription(BigDecimal monthlyFee, LocalDateTime begin,
			LocalDateTime end, Customer customer) {
		this.monthlyFee = monthlyFee;
		this.begin = begin;
		this.end = Optional.of(end);
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
	
	public BigDecimal getTotalPaid() {
		return getMonthlyFee()
					.multiply(new BigDecimal(ChronoUnit.MONTHS
						.between(getBegin(), 
							getEnd().orElse(LocalDateTime.now()))));
	}
}


public class Capitulo11 {

	public static void main (String... args) throws Exception 	{

		Customer paulo = new Customer("Paulo Silveira");
		Customer rodrigo = new Customer("Rodrigo Turini");
		Customer guilherme = new Customer("Guilherme Silveira");
		Customer adriano = new Customer("Adriano Almeida");
		
		Product bach = new Product("Bach Completo", 
				Paths.get("/music/bach.mp3"), new BigDecimal(100));
		Product poderosas = new Product("Poderosas Anita",
			 Paths.get("/music/poderosas.mp3"), new BigDecimal(90));
		Product bandeira = new Product("Bandeira Brasil",
				Paths.get("/images/brasil.jpg"), new BigDecimal(50));
		Product beauty = new Product("Beleza Americana", 
				Paths.get("beauty.mov"), new BigDecimal(150));
		Product vingadores = new Product("Os Vingadores", 
				Paths.get("/movies/vingadores.mov"), new BigDecimal(200));
		Product amelie = new Product("Amelie Poulain", 
				Paths.get("/movies/amelie.mov"), new BigDecimal(100));
		
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime yesterday = today.minusDays(1);
		LocalDateTime lastMonth = today.minusMonths(1);
		
		Payment payment1 = 
			new Payment(asList(bach, poderosas), today, paulo);
		Payment payment2 = 
			new Payment(asList(bach, bandeira, amelie), yesterday, rodrigo);
		Payment payment3 = 
			new Payment(asList(beauty, vingadores, bach), today, adriano);
		Payment payment4 = 
			new Payment(asList(bach, poderosas, amelie), lastMonth, guilherme);
		Payment payment5 = 
			new Payment(asList(beauty, amelie), yesterday, paulo);

		List<Payment> payments = asList(payment1, payment2, 
			payment3, payment4, payment5);

		// ordenar List<Payment> por data

		payments.stream()
			.sorted(Comparator.comparing(Payment::getDate))
			.forEach(System.out::println);

		// somatoria dos valores de  payment1:

		BigDecimal p1total = 
			payment1.getProducts().stream()
			.map(Product::getPrice)
			.reduce(BigDecimal.ZERO, (total, price) -> total.add(price));

		payment1.getProducts().stream()
			.map(Product::getPrice)
			.reduce(BigDecimal::add)
			.ifPresent(System.out::println);

		// somatoria de todos os payments

		Stream<BigDecimal> pricesStream =
			payments.stream()
			.map(p -> p.getProducts().stream()
				.map(Product::getPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add));


		BigDecimal total = 
			payments.stream()
			.map(p -> p.getProducts().stream()
						.map(Product::getPrice)
						.reduce(BigDecimal.ZERO, BigDecimal::add))
			.reduce(BigDecimal.ZERO, BigDecimal::add);	

		Stream<BigDecimal> priceOfEachProduct = 
			payments.stream()	
			.flatMap(p -> p.getProducts().stream().map(Product::getPrice));	

		Function<Payment, Stream<BigDecimal>> mapper = 
			p -> p.getProducts().stream().map(Product::getPrice);
		

		BigDecimal totalFlat = 
			payments.stream()
			.flatMap(p -> p.getProducts().stream().map(Product::getPrice))
			.reduce(BigDecimal.ZERO, BigDecimal::add);			

		// qual é o produto mais vendido?
		
		Stream<Product> products = payments.stream()
			.flatMap(p -> p.getProducts().stream());


		Map<Product, Long> topProducts = payments.stream()
			.flatMap(p -> p.getProducts().stream())
			.collect(Collectors.groupingBy(Function.identity(), 
						Collectors.counting()));

		
		System.out.println(topProducts);




		topProducts.entrySet().stream()
			.forEach(System.out::println);

		topProducts.entrySet().stream()
			.max(Comparator.comparing(Map.Entry::getValue))
			.ifPresent(System.out::println);
		

		// quero map<Product, BigDecimal>
		
		Map<Product, BigDecimal> totalValuePerProduct = payments.stream()
			.flatMap(p -> p.getProducts().stream())
			.collect(Collectors.groupingBy(Function.identity(), 
					 Collectors.reducing(BigDecimal.ZERO, Product::getPrice, BigDecimal::add)));

		totalValuePerProduct.entrySet().stream()
			.sorted(Comparator.comparing(Map.Entry::getValue))
			.forEach(System.out::println);


		Map<Product, BigDecimal> totalValuePerProduct2 = payments.stream()
			.flatMap(p -> p.getProducts().stream())
			.collect(Collectors.toMap(Function.identity(), 
						Product::getPrice,
						BigDecimal::add));
		
		totalValuePerProduct2.entrySet().stream()
			.sorted(Comparator.comparing(Map.Entry::getValue))
			.forEach(System.out::println);

		// usuário para lista de payment
		Map<Customer, List<Payment>> customerToPayments = 
			payments.stream()
			.collect(Collectors.groupingBy(Payment::getCustomer));

		Map<Customer, List<List<Product>>> customerToProductsList = payments.stream() 
			.collect(Collectors.groupingBy(Payment::getCustomer, 
				Collectors.mapping(Payment::getProducts, Collectors.toList())));

		customerToProductsList.entrySet().stream()
			.sorted(Comparator.comparing(e -> e.getKey().getName()))
			.forEach(System.out::println);


		System.out.println();

		// transformando List<List>> em List quando value de map:
		Map<Customer, List<Product>> customerToProducts2steps =
			customerToProductsList.entrySet().stream()
			.collect(Collectors.toMap(Map.Entry::getKey,
						e -> e.getValue().stream()
							.flatMap(List::stream)
							.collect(Collectors.toList())));

		customerToProducts2steps.entrySet().stream()
			.sorted(Comparator.comparing(e -> e.getKey().getName()))
			.forEach(System.out::println);

		System.out.println();

		Map<Customer, List<Product>> customerToProducts1step = payments.stream() 
			.collect(Collectors.groupingBy(Payment::getCustomer, 
				Collectors.mapping(Payment::getProducts, Collectors.toList())))
				.entrySet().stream()
					.collect(Collectors.toMap(Map.Entry::getKey,
						e -> e.getValue().stream()
							.flatMap(List::stream)
							.collect(Collectors.toList())));

		// usando reducao
		Map<Customer, List<Product>> customerToProducts = payments.stream() 
			.collect(Collectors.groupingBy(Payment::getCustomer, 
				Collectors.reducing(Collections.emptyList(),
					Payment::getProducts,
						(l1, l2) -> { List<Product> l = new ArrayList<>();
									l.addAll(l1);
									l.addAll(l2);
									return l;} )));

		customerToProducts.entrySet().stream()
			.sorted(Comparator.comparing(e -> e.getKey().getName()))
			.forEach(System.out::println);


		// qual é o usuario que mais pagou
		
		Map<Customer, BigDecimal> totalValuePerCustomer = payments.stream()
			.collect(Collectors.groupingBy(Payment::getCustomer, 
					 Collectors.reducing(BigDecimal.ZERO, 
					 	p -> p.getProducts().stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add),
					 		 BigDecimal::add)));
		
		totalValuePerCustomer.entrySet().stream()
			.sorted(Comparator.comparing(Map.Entry::getValue))
			.forEach(System.out::println);

		Function<Payment, BigDecimal> paymentToTotal = 
			p -> p.getProducts().stream()
				.map(Product::getPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		Map<Customer, BigDecimal> totalValuePerCustomer2 = payments.stream()
			.collect(Collectors.groupingBy(Payment::getCustomer, 
					 Collectors.reducing(BigDecimal.ZERO, 
					 	paymentToTotal,
					 		 BigDecimal::add)));

		

		// quero map<YearMonth, List<Payment>> agrupar por mes as vendas
		Map<YearMonth, List<Payment>> paymentsPerMonth = payments.stream()
			.collect(Collectors.groupingBy(p -> YearMonth.from(p.getDate())));

		paymentsPerMonth.entrySet().stream()
			.forEach(System.out::println);

		// quero map<YearMonth, BigDecimal> total recebido por mes
		Map<YearMonth, BigDecimal> paymentsValuePerMonth = payments.stream()
			.collect(Collectors.groupingBy(p -> YearMonth.from(p.getDate()),
				Collectors.reducing(BigDecimal.ZERO, 
					p -> p.getProducts().stream()
						.map(Product::getPrice)
						.reduce(BigDecimal.ZERO,
							BigDecimal::add),
					BigDecimal::add)));


		paymentsValuePerMonth.entrySet().stream()
			.forEach(System.out::println);
		



		// subscriptions

		BigDecimal monthlyFee = new BigDecimal("99.90");
		
		Subscription s1 = new Subscription(monthlyFee, 
				yesterday.minusMonths(5), paulo);
		
		Subscription s2 = new Subscription(monthlyFee, 
				yesterday.minusMonths(8), today.minusMonths(1), rodrigo);
		
		Subscription s3 = new Subscription(monthlyFee, 
				yesterday.minusMonths(5), today.minusMonths(2), adriano);
		
		List<Subscription> subscriptions = Arrays.asList(s1, s2, s3);
		
		// numero de meses pago
		System.out.println(ChronoUnit.MONTHS.between(
			s1.getBegin(), LocalDateTime.now()));

		System.out.println(ChronoUnit.MONTHS.between(
			s1.getBegin(), s1.getEnd().orElse(LocalDateTime.now())));

		// dada uma unica subscription, calcular quanto ele pagou ate hoje
		System.out.println(s1.getMonthlyFee()
					.multiply(new BigDecimal(ChronoUnit.MONTHS.between(
			s1.getBegin(), s1.getEnd().orElse(LocalDateTime.now())))));


		// dada uma colecao de subscription, calcular quanto todos pagaram ate hoje
		
		BigDecimal totalPaid = subscriptions.stream()
			.map(Subscription::getTotalPaid)
			.reduce(BigDecimal.ZERO, BigDecimal::add);
		System.out.println(totalPaid);

		// EXERCICIOS

		// achar o usuario que mais pagou total de valor de subscriptions ate hoje	
		
		// achar o usuario que ficou mais meses pagando (independente dela estar ativa ou nao)

		// quero map<Customer, Type>, qual é o tipo de produto que ele mais comprou

		// que tipo de produto é o mais vendido (sum(VIDEO), sum(AUDIO))

		// Dado um %%Payment%%, gere a lista de %%Paths%% dos arquivos digitais comprados.
	}
}

// para exercicios

enum Type {
	MUSIC, 
	VIDEO, 
	IMAGE;
}
