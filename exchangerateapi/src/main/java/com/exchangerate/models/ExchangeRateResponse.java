package com.exchangerate.models;

import org.springframework.stereotype.Component;

@Component
public class ExchangeRateResponse {

	private double currentRate;
	private double averageRate;
	private String trend;

	public ExchangeRateResponse() {
		// TODO Auto-generated constructor stub
	}

	public double getCurrentRate() {
		return currentRate;
	}

	public void setCurrentRate(double currentRate) {
		this.currentRate = currentRate;
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
