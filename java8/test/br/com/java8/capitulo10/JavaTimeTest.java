package br.com.java8.capitulo10;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import org.junit.Test;

public class JavaTimeTest {

	@Test
	public void test() {

		LocalDate dataAgora = LocalDate.now();
		System.out.println(dataAgora);
		
		
		LocalTime horarioAtual = LocalTime.now();
		System.out.println(horarioAtual);
		
		LocalDateTime dataAgoraComHorario = LocalDateTime.now();
		System.out.println(dataAgoraComHorario);

		LocalDateTime dataAgoraComHorarioMeioDia = LocalDate.now().atTime(12, 0);
		System.out.println(dataAgoraComHorarioMeioDia);
		
		ZonedDateTime dataComTimeZoneSaoPaulo = LocalDateTime.now().atZone(ZoneId.of("America/Sao_Paulo"));
		System.out.println(dataComTimeZoneSaoPaulo);
		
		ZonedDateTime dataComTimeZoneTokio = LocalDateTime.now().atZone(ZoneId.of("Asia/Tokyo"));
		System.out.println(dataComTimeZoneTokio);
		
		
		LocalDate dataConfigurada = LocalDate.of(2015, 5, 25);
		System.out.println(dataConfigurada);
		
		LocalDateTime dataHoraConfigurada = LocalDateTime.of(2015, 5, 25, 11, 30);
		System.out.println(dataHoraConfigurada);
		
		LocalDate dataPassado = LocalDate.now().withYear(2000);
		System.out.println(dataPassado);
		
		System.out.println("Data anterior " + dataPassado.isBefore(dataAgora));
		System.out.println("Data igual " + dataAgora.isEqual(dataConfigurada));
		System.out.println("Data posterior " + dataAgora.isAfter(dataPassado));
		System.out.println("Data Timezone diferentes: " + dataComTimeZoneSaoPaulo.isEqual(dataComTimeZoneTokio));
		
		ZonedDateTime dataSaoPaulo = ZonedDateTime.of(2015, 5, 25, 21, 0, 0, 0, ZoneId.of("America/Sao_Paulo"));
		ZonedDateTime dataTokio = ZonedDateTime.of(2015, 5, 25, 21, 0, 0, 0, ZoneId.of("Asia/Tokyo"));
		dataTokio = dataTokio.plusHours(12);
		System.out.println("Data Timezone iguais: " + dataSaoPaulo.isEqual(dataTokio));

		System.out.println("Hoje é dia: " + MonthDay.now().getDayOfMonth());
		System.out.println("Ano/Mês: " + YearMonth.now().getYear() + YearMonth.now().getMonth());
		
		Locale locale = new Locale("pt");
		System.out.println(Month.MAY.getDisplayName(TextStyle.FULL, locale));
		System.out.println(Month.MAY.getDisplayName(TextStyle.SHORT, locale));
		System.out.println(DayOfWeek.SATURDAY.getDisplayName(TextStyle.FULL, locale));
		System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));
		System.out.println("Diferença entre datas em anos " + ChronoUnit.YEARS.between(dataPassado, dataAgora));
		System.out.println("Diferença entre datas em meses " + ChronoUnit.MONTHS.between(dataPassado, dataAgora));
		System.out.println("Diferença entre datas em dias " + ChronoUnit.DAYS.between(dataPassado, dataAgora));
		
		LocalDateTime agora = LocalDateTime.now();
		LocalDateTime daquiAUmaHora = LocalDateTime.now().plusHours(1);
		Duration duration = Duration.between(agora, daquiAUmaHora);
		System.out.printf("%s horas, %s minutos e %s segundos",
				duration.toHours(), duration.toMinutes(), duration.getSeconds());
		
	}

}
