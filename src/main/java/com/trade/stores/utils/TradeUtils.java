package com.trade.stores.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TradeUtils {

	private final static Logger LOGGER = LoggerFactory.getLogger(TradeUtils.class);
            
	
	public static boolean isValidDate(Date date) {

		LocalDate todayDate = LocalDate.now();

		Calendar maturityDate = Calendar.getInstance();
		maturityDate.setTime(date);

		LocalDate anotherDay = maturityDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		if (todayDate.equals(anotherDay) || todayDate.isBefore(anotherDay)) {
			return true;
		}

		return false;
	}
}
