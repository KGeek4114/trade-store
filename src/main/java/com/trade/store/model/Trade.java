package com.trade.store.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Trade {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long seqId;
	
	private String tradeId;
	private int version;
	private String countryPartyId;
	private String bookId;
	private Date maturityDate;
	private Date createdDate;
	private String expired;
	
	public Trade() {
		
	}

	public Trade(String tradeId, int version, String countryPartyId, String bookId, Date maturityDate, Date createdDate,
			String expired) {
		this.tradeId = tradeId;
		this.version = version;
		this.countryPartyId = countryPartyId;
		this.bookId = bookId;
		this.maturityDate = maturityDate;
		this.createdDate = createdDate;
		this.expired = expired;
	}

	public Long getSeqId() {
		return seqId;
	}

	public void setSeqId(Long seqId) {
		this.seqId = seqId;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getExpired() {
		return expired;
	}

	public void setExpired(String expired) {
		this.expired = expired;
	}

	@Override
	public String toString() {
		return "Trade [tradeId=" + tradeId + ", version=" + version + ", countryPartyId=" + countryPartyId + ", bookId="
				+ bookId + ", maturityDate=" + maturityDate + ", createdDate=" + createdDate + ", expired=" + expired
				+ "]";
	}

}