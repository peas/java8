package br.com.casadocodigo.java8;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Locale;

public class Capitulo10 {

	public static void main(String[] args) {
		
		// incrementando um mês com Calendar
		Calendar mesQueVem = Calendar.getInstance();
		mesQueVem.add(Calendar.MONTH, 1);
		
		// incrementando um mês com LocalDate
		LocalDate mesQueVem2 = LocalDate.now().plusMonths(1);
		
		// decrementando um mês com LocalDate
		LocalDate anoPassado = LocalDate.now().minusYears(1);
		
		// LocalDateTime
		
		LocalDateTime agora = LocalDateTime.now(); 
		System.out.println(agora);

		// construindo um LocalDateTime a partir de um LocalDate
		LocalDateTime hojeAoMeioDia = LocalDate.now().atTime(12,0);

		// construindo um LocalDateTime pela junção de um LocalDate com LocalTime
		
		LocalTime agora3 = LocalTime.now();
		LocalDate hoje = LocalDate.now();
		LocalDateTime dataEhora = hoje.atTime(agora3);

		// adicionando informação de timezone para ter um ZonedDateTime.
		ZonedDateTime dataComHoraETimezone = 
			dataEhora.atZone(ZoneId.of("America/Sao_Paulo"));

		// de ZonedDateTime para LocalDateTime
		
		LocalDateTime semTimeZone = dataComHoraETimezone.toLocalDateTime();

		// criando a partir do factory method *of*
		
		LocalDate date = LocalDate.of(2014, 12, 25);
		LocalDateTime dateTime = LocalDateTime.of(2014, 12, 25, 10, 30);

		// utilizando métodos *with* para adicionar valores
		
		LocalDate dataDoPassado = LocalDate.now().withYear(1988);

		System.out.println(dataDoPassado.getYear());
		
		// comparações entre datas com os métodos *is*

		LocalDate amanha = LocalDate.now().plusDays(1);

		System.out.println(hoje.isBefore(amanha));
		System.out.println(hoje.isAfter(amanha));
		System.out.println(hoje.isEqual(amanha));

		// dia do mês atual a partir do MonthDay
		
		System.out.println("hoje é dia: "+ MonthDay.now().getDayOfMonth());
		
		// enum Month
		
		System.out.println(LocalDate.of(2014, 12, 25)); 
		System.out.println(LocalDate.of(2014, Month.DECEMBER, 25));	
		
		System.out.println(Month.DECEMBER.firstMonthOfQuarter());
		System.out.println(Month.DECEMBER.plus(2)); 
		System.out.println(Month.DECEMBER.minus(1));

		// formatando  e exibindo os modelos de data
		
		Locale pt = new Locale("pt");

		System.out.println(Month.DECEMBER
			.getDisplayName(TextStyle.FULL, pt));

		System.out.println(Month.DECEMBER
			.getDisplayName(TextStyle.SHORT, pt));

		String resultado = agora.format(DateTimeFormatter.ISO_LOCAL_TIME);

		agora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		// parseando de String para LocalDate
		
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String resultado2 = agora.format(formatador);
		LocalDate agoraEmData = LocalDate.parse(resultado2, formatador);

		// formatando com Calendar
		
		Calendar instante = Calendar.getInstance();
		instante.set(2014, Calendar.FEBRUARY, 30);		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
		System.out.println(dateFormat.format(instante.getTime()));

		// data e hora inválida
		
		LocalDate.of(2014, Month.FEBRUARY, 30);

		LocalDateTime horaInvalida = LocalDate.now().atTime(25, 0);

		// diferença de dias com Calendar
		
		Calendar calendar = Calendar.getInstance();

		Calendar outraData = Calendar.getInstance();
		outraData.set(1988, Calendar.JANUARY, 25);

		long diferenca = calendar.getTimeInMillis() - outraData.getTimeInMillis();

		long milissegundosDeUmDia = 1000 * 60 * 60 * 24;

		long dias = diferenca / milissegundosDeUmDia;

		// diferença de dias com ChronoUnit
		
		LocalDate agora4 = LocalDate.now();
		LocalDate outraData2 = LocalDate.of(1989, Month.JANUARY, 25);
		long dias2 = ChronoUnit.DAYS.between(outraData2, agora4);
		
		// diferença de meses e anos
		
		long meses = ChronoUnit.MONTHS.between(outraData2, agora4);
		long anos = ChronoUnit.YEARS.between(outraData2, agora4);
		System.out.printf("%s dias, %s meses e %s anos", dias2, meses, anos);

		// periodo entre duas datas
		
		LocalDate outraData3 = LocalDate.of(1989, Month.JANUARY, 25);
		Period periodo = Period.between(outraData3, agora4);
		System.out.printf("%s dias, %s meses e %s anos", 
			periodo.getDays(), periodo.getMonths(), periodo.getYears());

		// invertendo valores do periodo
		
		Period periodo2 = Period.between(outraData3, agora4);

		if (periodo2.isNegative()) periodo2 = periodo2.negated();

		System.out.printf("%s dias, %s meses e %s anos", 
			periodo2.getDays(), periodo2.getMonths(), periodo2.getYears());

		// o mesmo, só que agora trabalhando com duração
		
		LocalDateTime agora5 = LocalDateTime.now();
		LocalDateTime daquiAUmaHora = agora5.plusHours(1);
		Duration duration = Duration.between(agora5, daquiAUmaHora);

		if (duration.isNegative()) duration = duration.negated();

		System.out.printf("%s horas, %s minutos e %s segundos", 
			duration.toHours(), duration.toMinutes(), duration.getSeconds());

	}
}
