package com.trade.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trade.store.model.Trade;
import com.trade.store.model.dto.TradeDTO;
import com.trade.store.repo.TradeStoreService;
import com.trade.stores.exceptions.CustomDataException;

@RestController
@RequestMapping("/trade")
public class TradeController {

	@Autowired
	private TradeStoreService tradeStoreService;

	@PostMapping("/createTradeOrder")
	public ResponseEntity<Trade> createTradeOrder(@RequestBody TradeDTO tradeObj) {

		try {
			Trade trade = tradeStoreService.createUpdateTradeOrder(tradeObj);

			if (trade == null)
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			return new ResponseEntity<>(trade, HttpStatus.OK);
		} catch (CustomDataException ex) {
			throw new CustomDataException(ex.getMessage());
		}

	}
}