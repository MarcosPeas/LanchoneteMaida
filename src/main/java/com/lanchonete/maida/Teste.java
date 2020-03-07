package com.lanchonete.maida;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Teste {

	public static void main(String[] args) {

		LocalDateTime data = LocalDateTime.now()
		
		  .withMonth(1) .withDayOfMonth(1)
		  .withHour(0).withMinute(0).withSecond(0).withNano(0);
		 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

		ZonedDateTime zdt = data.atZone(ZoneId.systemDefault());

		long epochMilli = zdt.toInstant().toEpochMilli();
		long currentTimeMillis = System.currentTimeMillis();
		System.out.println(epochMilli + " : " + currentTimeMillis);

		System.out.println(new Date(epochMilli) + " : " + new Date(currentTimeMillis));

	}

}
