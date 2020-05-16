package com.exchangerate.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ExchangeRateHistory {

	@Id
	@GeneratedValue
	private long id;
	@Column(name = "base_currency")
	private String baseCurrency;

	@Column(name = "target_currency")
	private String targetCurrency;

	@Column(name = "rate")
	private Double rate;

	@Column(name = "average_rate")
	private Double averageRate;

	@Column(name = "trend")
	private String trend;

	@Column(name = "dat_of_requested_rate")
	private LocalDate dateOfRequestedRate;

	@Column(name = "query_year")
	private int queryYear;

	@Column(name = "query_month")
	private int queryMonth;

	@Column(name = "query_day")
	private int queryDay;

	public ExchangeRateHistory() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public String getTargetCurrency() {
		return targetCurrency;
	}

	public void setTargetCurrency(String targetCurrency) {
		this.targetCurrency = targetCurrency;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getAverageRate() {
		return averageRate;
	}

	public void setAverageRate(Double averageRate) {
		this.averageRate = averageRate;
	}

	public String getTrend() {
		return trend;
	}

	public void setTrend(String trend) {
		this.trend = trend;
	}

	public int getQueryYear() {
		return queryYear;
	}

	public void setQueryYear(int queryYear) {
		this.queryYear = queryYear;
	}

	public int getQueryMonth() {
		return queryMonth;
	}

	public void setQueryMonth(int queryMonth) {
		this.queryMonth = queryMonth;
	}

	public LocalDate getDateOfRequestedRate() {
		return dateOfRequestedRate;
	}

	public void setDateOfRequestedRate(LocalDate dateOfRequestedRate) {
		this.dateOfRequestedRate = dateOfRequestedRate;
	}

	public int getQueryDay() {
		return queryDay;
	}

	public void setQueryDay(int queryDay) {
		this.queryDay = queryDay;
	}

}
