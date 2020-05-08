package com.exchangerate.models;

import java.util.Map;
import java.util.SortedMap;

import org.springframework.stereotype.Component;

@Component
public class ExchangeRateDetails {
	private String start_at;
	private String end_at;
	private String base;
	private SortedMap<String, Map<String, Double>> rates;

	public ExchangeRateDetails() {
		// TODO Auto-generated constructor stub
	}

	public String getStart_at() {
		return start_at;
	}

	public void setStart_at(String start_at) {
		this.start_at = start_at;
	}

	public String getEnd_at() {
		return end_at;
	}

	public void setEnd_at(String end_at) {
		this.end_at = end_at;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public SortedMap<String, Map<String, Double>> getRates() {
		return rates;
	}

	public void setRates(SortedMap<String, Map<String, Double>> rates) {
		this.rates = rates;
	}
}
