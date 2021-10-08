package com.trade.store.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import com.trade.store.model.Trade;
import com.trade.store.model.dto.TradeDTO;
import com.trade.store.repo.TradeStoreRepository;
import com.trade.store.repo.TradeStoreService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TradeControllerTest {
	@LocalServerPort
	private int port;
	
	@Mock
	private TradeStoreRepository tradeStoreRepo;

	@InjectMocks
	private TradeStoreService tradeStoreService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void createTradeOrderReturnResponse() throws Exception {
		TradeDTO tradeDTO = new TradeDTO("T3", 3, "CP-2", "B1", new Date());
		
		assertThat(this.restTemplate
				.postForEntity("http://localhost:" + port + "/trade/createTradeOrder", tradeDTO, Trade.class)
				.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
}
