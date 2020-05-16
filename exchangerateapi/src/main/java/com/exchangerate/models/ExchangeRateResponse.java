package com.exchangerate.models;

import org.springframework.stereotype.Component;

@Component
public class ExchangeRateResponse {

	private double rate;
	private double averageRate;
	private String trend;

	public ExchangeRateResponse() {
		// TODO Auto-generated constructor stub
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getAverageRate() {
		return averageRate;
	}

	public void setAverageRate(double averageRate) {
		this.averageRate = averageRate;
	}

	public String getTrend() {
		return trend;
	}

	public void setTrend(String trend) {
		this.trend = trend;
	}

}
