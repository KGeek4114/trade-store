package com.trade.store.schedulingtasks;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.trade.store.repo.TradeStoreRepository;

@Component
public class TradeScheduledTasks {

	private static final Logger LOGGER = LoggerFactory.getLogger(TradeScheduledTasks.class);

	@Autowired
	TradeStoreRepository tradeStoreRepo;

	@Scheduled(cron = "0 10 * * * ?")
	public void updateTradeExpiryStatus() {
		LOGGER.info("Current time is :: " + Calendar.getInstance().getTime());

		int countUpdatedRecord = tradeStoreRepo.setExpiredFlag();

		LOGGER.info("Total Record Updated = " + countUpdatedRecord);

	}
}
