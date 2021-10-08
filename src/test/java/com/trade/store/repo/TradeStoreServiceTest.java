package com.trade.store.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.trade.store.model.Trade;
import com.trade.store.model.dto.TradeDTO;
import com.trade.stores.exceptions.CustomDataException;

@SpringBootTest
public class TradeStoreServiceTest {

	@Mock
	private TradeStoreRepository tradeStoreRepo;

	@InjectMocks
	private TradeStoreService tradeStoreService;

	private TradeDTO tradeDto;

	private List<Trade> tradeLst;

	@BeforeEach
	void setMockOutput() {
		tradeLst = new ArrayList<>();

		tradeLst.add(new Trade("T1", 1, "CP-1", "B1", parseDate("2020-01-08"), new Date(), "N"));
		tradeLst.add(new Trade("T2", 1, "CP-1", "B1", parseDate("2020-01-08"), new Date(), "N"));
		tradeLst.add(new Trade("T2", 2, "CP-1", "B1", parseDate("2020-01-08"), new Date(), "N"));
	}

	@DisplayName("Test Mock ValidateTrade Failed For MaturityDate Check")
	@Test
	public void testvalidateTradeWithMaturityDateFailedCase() throws Exception {
		tradeDto = new TradeDTO("T1", 1, "CP-1", "B1", parseDate("2020-01-08"));
		Throwable exception = Assertions.assertThrows(CustomDataException.class, () -> {
			tradeStoreService.validateTrade(tradeDto);
		});
		
		 assertEquals("MaturityDate should be equal or greater than current date.", exception.getMessage());
	}
	
	@DisplayName("Test Mock ValidateTrade Failed For Version Check")
	@Test
	public void testvalidateTradeWithVersionFailedCase() throws Exception {
		tradeDto = new TradeDTO("T2", 1, "CP-1", "B1", new Date());
		when(tradeStoreRepo.findTradeByTradeId(tradeDto.getTradeId())).thenReturn(Integer.valueOf(2));	
		Throwable exception = Assertions.assertThrows(CustomDataException.class, () -> {
			tradeStoreService.validateTrade(tradeDto);
		});
		
		 assertEquals("Trade version should not be lesson than 2", exception.getMessage());
	}
	
	@DisplayName("Test Mock ValidateTrade Valid passed check")
	@Test
	public void testvalidateTradeWithValidCase() throws Exception {
		tradeDto = new TradeDTO("T1", 1, "CP-1", "B1", new Date());
		when(tradeStoreRepo.findTradeByTradeId(tradeDto.getTradeId())).thenReturn(Integer.valueOf(1));	
		Assertions.assertEquals(true, tradeStoreService.validateTrade(tradeDto));
	}
	
	@DisplayName("Test Mock createUpdateTradeOrder Valid Case")
	@Test
	public void testCreateUpdateTradeOrderWithValidCase() throws Exception {
		tradeDto = new TradeDTO("T3", 2, "CP-2", "B1", new Date());
		Trade newTrade = new Trade("T3", 2, "CP-2", "B1", new Date(), new Date(), "N");
		when(tradeStoreRepo.findTradeByTradeId(tradeDto.getTradeId())).thenReturn(Integer.valueOf(0));	
		when(tradeStoreRepo.findByTradeIdAndVersion(tradeDto.getTradeId(),
				tradeDto.getVersion())).thenReturn(Optional.empty());
		when(tradeStoreRepo.save(any())).thenReturn(newTrade);
		
		// Execute the service call
        Trade trade = tradeStoreService.createUpdateTradeOrder(tradeDto);

        // Assert the response
        Assertions.assertNotNull(newTrade, "The saved trade should not be null");
        Assertions.assertEquals(newTrade.getTradeId(), trade.getTradeId(), "The ID should be matched");
        
	}
	

	private static Date parseDate(String date) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
}
