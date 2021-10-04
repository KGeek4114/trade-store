package com.trade.store.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.trade.store.model.Trade;

@Repository
public interface TradeStoreRepository extends JpaRepository<Trade, Long> {

	@Query(value = "SELECT max(version) as version FROM Trade where tradeId = ?1")
	public Integer findTradeByTradeId(String tradeId);
	
	public Optional<Trade> findByTradeIdAndVersion(String tradeId, int version);
	
	@Modifying
	@Query("UPDATE Trade t set t.expired = 'N' WHERE t.maturityDate < CURRENT_DATE")
	public int setExpiredFlag();
	
}
