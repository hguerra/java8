package br.com.cap11;

import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TDD {

	public static void main(String[] args) {
		Customer paulo = new Customer("Paulo Silveira");
		Customer rodrigo = new Customer("Rodrigo Turini");
		Customer guilherme = new Customer("Guilherme Silveira");
		Customer adriano = new Customer("Adriano Almeida");

		Product bach = new Product("Bach Completo", Paths.get("/music/bach.mp3"), new BigDecimal(100));
		Product poderosas = new Product("Poderosas Anita", Paths.get("/music/poderosas.mp3"), new BigDecimal(90));
		Product bandeira = new Product("Bandeira Brasil", Paths.get("/images/brasil.jpg"), new BigDecimal(50));
		Product beauty = new Product("Beleza Americana", Paths.get("beauty.mov"), new BigDecimal(150));
		Product vingadores = new Product("Os Vingadores", Paths.get("/movies/vingadores.mov"), new BigDecimal(200));
		Product amelie = new Product("Amelie Poulain", Paths.get("/movies/amelie.mov"), new BigDecimal(100));

		/**
		 * Compra
		 */

		LocalDateTime today = LocalDateTime.now();
		LocalDateTime yesterday = today.minusDays(1);
		LocalDateTime lastMonth = today.minusMonths(1);

		Payment payment1 = new Payment(Arrays.asList(bach, poderosas), today, paulo);
		Payment payment2 = new Payment(Arrays.asList(bach, bandeira, amelie), yesterday, rodrigo);
		Payment payment3 = new Payment(Arrays.asList(beauty, vingadores, bach), today, adriano);
		Payment payment4 = new Payment(Arrays.asList(bach, poderosas, amelie), lastMonth, guilherme);
		Payment payment5 = new Payment(Arrays.asList(beauty, amelie), yesterday, paulo);

		List<Payment> payments = Arrays.asList(payment1, payment2, payment3, payment4, payment5);

		/**
		 * ordenando pagamento
		 */

		payments.stream().sorted(Comparator.comparing(Payment::getDate)).forEach(System.out::println);

		/**
		 * Reduzindo BigDecimal em somas
		 */

		payment1.getProducts().stream().map(Product::getPrice).reduce(BigDecimal::add).ifPresent(System.out::println);

		/**
		 * soma de todos os produtos
		 */

		BigDecimal total = payments.stream()
				.map(p -> p.getProducts().stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		System.out.println(total);

		BigDecimal totalFlat = payments.stream().flatMap(p -> p.getProducts().stream().map(Product::getPrice))
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		System.out.println(totalFlat);

		/**
		 * Produtos mais vendidos
		 */

		Stream<Product> products = payments.stream().flatMap(p -> p.getProducts().stream());

		Map<Product, Long> topProducts = payments.stream().flatMap(p -> p.getProducts().stream())
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		System.out.println(topProducts);

		topProducts.entrySet().stream().forEach(System.out::println);

		topProducts.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).ifPresent(System.out::println);

		/**
		 * Valores gerados por produto
		 */

		Map<Product, BigDecimal> totalValuePerProduct = payments.stream().flatMap(p -> p.getProducts().stream())
				.collect(Collectors.groupingBy(Function.identity(),
						Collectors.reducing(BigDecimal.ZERO, Product::getPrice, BigDecimal::add)));

		totalValuePerProduct.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue))
				.forEach(System.out::println);

		/**
		 * os produtos de cada cliente
		 */

		Map<Customer, List<List<Product>>> customerToProductsList = payments.stream().collect(Collectors
				.groupingBy(Payment::getCustomer, Collectors.mapping(Payment::getProducts, Collectors.toList())));
		customerToProductsList.entrySet().stream().sorted(Comparator.comparing(e -> e.getKey().getName()))
				.forEach(System.out::println);

		// sem listas aninhadas

		Map<Customer, List<Product>> customerToProducts2steps = customerToProductsList.entrySet().stream()
				.collect(Collectors.toMap(Map.Entry::getKey,
						e -> e.getValue().stream().flatMap(List::stream).collect(Collectors.toList())));
		customerToProducts2steps.entrySet().stream().sorted(Comparator.comparing(e -> e.getKey().getName()))
				.forEach(System.out::println);

		/**
		 * nosso cliente mais especial
		 */

		Function<Payment, BigDecimal> paymentToTotal = p -> p.getProducts().stream().map(Product::getPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		Map<Customer, BigDecimal> totalValuePerCustomer = payments.stream().collect(Collectors.groupingBy(
				Payment::getCustomer, Collectors.reducing(BigDecimal.ZERO, paymentToTotal, BigDecimal::add)));

		totalValuePerCustomer.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue))
				.forEach(System.out::println);

		/**
		 * relatorio por datas
		 */

		Map<YearMonth, List<Payment>> paymentsPerMonth = payments.stream()
				.collect(Collectors.groupingBy(p -> YearMonth.from(p.getDate())));

		paymentsPerMonth.entrySet().stream().forEach(System.out::println);

		Map<YearMonth, BigDecimal> paymentsValuePerMonth = payments.stream()
				.collect(
						Collectors.groupingBy(p -> YearMonth.from(p.getDate()),
								Collectors.reducing(BigDecimal.ZERO, p -> p.getProducts().stream()
										.map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add),
								BigDecimal::add)));

		/**
		 * Sistema de assinaturas
		 */

		BigDecimal monthlyFee = new BigDecimal("99.90");
		Subscription s1 = new Subscription(monthlyFee, yesterday.minusMonths(5), paulo);
		Subscription s2 = new Subscription(monthlyFee, yesterday.minusMonths(8), today.minusMonths(1), rodrigo);
		Subscription s3 = new Subscription(monthlyFee, yesterday.minusMonths(5), today.minusMonths(2), adriano);
		List<Subscription> subscriptions = Arrays.asList(s1, s2, s3);

		/**
		 * calcular quantos meses foram pagos através daquela assinatura
		 */

		long meses = ChronoUnit.MONTHS.between(s1.getBegin(), s1.getEnd().orElse(LocalDateTime.now()));

		BigDecimal totalAssinatura = s1.getMonthlyFee().multiply(
				new BigDecimal(ChronoUnit.MONTHS.between(s1.getBegin(), s1.getEnd().orElse(LocalDateTime.now()))));

		BigDecimal totalPaid = subscriptions.stream().map(Subscription::getTotalPaid).reduce(BigDecimal.ZERO,
				BigDecimal::add);

	}

}
