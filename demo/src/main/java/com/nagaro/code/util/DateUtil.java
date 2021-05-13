package com.nagaro.code.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {

	public String getCurrentDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}

	public String getPastDate(int months) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		LocalDateTime now = LocalDateTime.now();
		now = now.minusMonths(months);
		return dtf.format(now);
	}
}
