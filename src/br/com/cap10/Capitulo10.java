package br.com.cap10;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Capitulo10 {

	public static void main(String[] args) {
		/**
		 * data sem horário nem timezone
		 */
		LocalDate mesQueVem = LocalDate.now().plusMonths(1);
		System.out.println(mesQueVem);

		LocalDate anoPassado = LocalDate.now().minusYears(1);
		System.out.println(anoPassado);

		/**
		 * hora
		 */

		LocalTime tempo = LocalTime.now();
		System.out.println(tempo);

		LocalDateTime agora = LocalDateTime.now();
		System.out.println(agora);

		/**
		 * Combinacoes
		 */
		LocalDateTime hojeAoMeioDia = LocalDate.now().atTime(12, 0);
		System.out.println(hojeAoMeioDia);

		LocalTime hora = LocalTime.now();
		LocalDate hoje = LocalDate.now();
		LocalDateTime dataEhora = hoje.atTime(hora);

		System.out.println(dataEhora);

		/**
		 * Zone
		 */

		ZonedDateTime dataComHoraETimezone = dataEhora.atZone(ZoneId.of("America/Sao_Paulo"));
		System.out.println(dataComHoraETimezone);

		/**
		 * to
		 */

		LocalDateTime semTimeZone = dataComHoraETimezone.toLocalDateTime();
		System.out.println(semTimeZone);

		/**
		 * of
		 */

		LocalDate date = LocalDate.of(2014, 12, 25);
		LocalDateTime dateTime = LocalDateTime.of(2014, 12, 25, 10, 30);

		System.out.println(date);
		System.out.println(dateTime);

		/**
		 * Java.time e imutavel
		 * 
		 * alterar dados com with
		 */

		LocalDate dataDoPassado = LocalDate.now().withYear(1988);

		System.out.println(dataDoPassado);

		/**
		 * get
		 */
		System.out.println(dataDoPassado.getYear());

		/**
		 * is
		 */

		LocalDate atividadeHoje = LocalDate.now();
		LocalDate amanha = LocalDate.now().plusDays(1);
		System.out.println(atividadeHoje.isBefore(amanha));
		System.out.println(atividadeHoje.isAfter(amanha));
		System.out.println(atividadeHoje.isEqual(amanha));

		/**
		 * isEqual
		 */

		ZonedDateTime tokyo = ZonedDateTime.of(2011, 5, 2, 10, 30, 0, 0, ZoneId.of("Asia/Tokyo"));
		ZonedDateTime saoPaulo = ZonedDateTime.of(2011, 5, 2, 10, 30, 0, 0, ZoneId.of("America/Sao_Paulo"));
		tokyo = tokyo.plusHours(12);
		System.out.println(tokyo.isEqual(saoPaulo));

		/**
		 * dados especificos
		 */

		System.out.println("hoje é dia: " + MonthDay.now().getDayOfMonth());

		/**
		 * from
		 */

		YearMonth ym = YearMonth.from(amanha);
		System.out.println(ym.getMonth() + " " + ym.getYear());

		/**
		 * enums
		 */

		System.out.println(LocalDate.of(2014, 12, 25));
		System.out.println(LocalDate.of(2014, Month.DECEMBER, 25));

		System.out.println(Month.DECEMBER.firstMonthOfQuarter());
		System.out.println(Month.DECEMBER.plus(2));
		System.out.println(Month.DECEMBER.minus(1));

		/**
		 * Locale
		 */

		Locale pt = new Locale("pt");
		System.out.println(Month.DECEMBER.getDisplayName(TextStyle.FULL, pt));
		System.out.println(Month.DECEMBER.getDisplayName(TextStyle.SHORT, pt));

		/**
		 * Formatar datas
		 */

		LocalDateTime now = LocalDateTime.now();
		String resultado = now.format(DateTimeFormatter.ISO_LOCAL_TIME);
		System.out.println(resultado);

		LocalDateTime now2 = LocalDateTime.now();
		now2.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		System.out.println(now2);

		/**
		 * Duração e Período
		 */

		LocalDate agoraChrono = LocalDate.now();
		LocalDate outraData = LocalDate.of(1989, Month.JANUARY, 25);
		long dias = ChronoUnit.DAYS.between(outraData, agoraChrono);

		System.out.println(dias);

		Period periodo = Period.between(outraData, agoraChrono);
		if (periodo.isNegative()) {
			periodo = periodo.negated();
		}
		System.out.printf("%s dias, %s meses e %s anos", periodo.getDays(), periodo.getMonths(), periodo.getYears());

		/**
		 * duration
		 */

		LocalDateTime agoraDuration = LocalDateTime.now();
		LocalDateTime daquiAUmaHora = LocalDateTime.now().plusHours(1);
		Duration duration = Duration.between(agoraDuration, daquiAUmaHora);
		if (duration.isNegative()) {
			duration = duration.negated();
		}
		System.out.printf("%s horas, %s minutos e %s segundos", duration.toHours(), duration.toMinutes(),
				duration.getSeconds());
	}

}
