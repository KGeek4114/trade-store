package com.trade.store.repo;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.trade.store.model.Trade;
import com.trade.store.model.dto.TradeDTO;
import com.trade.stores.exceptions.CustomDataException;
import com.trade.stores.utils.TradeUtils;

@Component
public class TradeStoreService {

	@Autowired
	TradeStoreRepository tradeStoreRepo;

	public boolean validateTrade(TradeDTO tradeObj) throws CustomDataException {

		Integer versionVal = tradeStoreRepo.findTradeByTradeId(tradeObj.getTradeId());

		// Check the maturity date and trade version
		if (!TradeUtils.isValidDate(tradeObj.getMaturityDate()))
			throw new CustomDataException("MaturityDate should be equal or greater than current date.");
		else if (tradeObj.getVersion() < versionVal)
			throw new CustomDataException("Trade version should not be lesson than " + versionVal);

		return true;
	}

	public Trade createUpdateTradeOrder(TradeDTO tradeObj) throws CustomDataException {

		if (this.validateTrade(tradeObj)) {

			Optional<Trade> trade = tradeStoreRepo.findByTradeIdAndVersion(tradeObj.getTradeId(),
					tradeObj.getVersion());
			Trade tradeEntity = new Trade();
			if (trade.isPresent()) {
				tradeEntity = trade.get();
			} else {
				tradeEntity.setCreatedDate(new Date());
				tradeEntity.setExpired("N");
			}

			BeanUtils.copyProperties(tradeObj, tradeEntity);

			return tradeStoreRepo.save(tradeEntity);
		}

		return null;

	}
}