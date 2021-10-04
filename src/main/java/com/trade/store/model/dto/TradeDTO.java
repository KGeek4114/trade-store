package com.trade.store.model.dto;

import java.util.Date;

public class TradeDTO {
	
	private String tradeId;
	private int version;
	private String countryPartyId;
	private String bookId;
	private Date maturityDate;
	
	public TradeDTO() {
		
	}

	public TradeDTO(String tradeId, int version, String countryPartyId, String bookId, Date maturityDate) {
		this.tradeId = tradeId;
		this.version = version;
		this.countryPartyId = countryPartyId;
		this.bookId = bookId;
		this.maturityDate = maturityDate;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getCountryPartyId() {
		return countryPartyId;
	}

	public void setCountryPartyId(String countryPartyId) {
		this.countryPartyId = countryPartyId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public Date getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}

}
