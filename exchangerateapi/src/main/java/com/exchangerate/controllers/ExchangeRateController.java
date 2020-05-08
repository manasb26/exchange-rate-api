package com.exchangerate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.exchangerate.models.ExchangeRateDetails;
import com.exchangerate.models.ExchangeRateResponse;
import com.exchangerate.service.ExchangeRateService;

@RestController
@RequestMapping("/api/exchange-rate/")
public class ExchangeRateController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ExchangeRateService exchangeRateService;
	
	@Autowired
	private ExchangeRateResponse exchnageRateDetails;
	
	@GetMapping
	@RequestMapping("{date}/{baseCurrency}/{targetCurrency}")
	public ExchangeRateResponse getExchangeRate(@PathVariable String date, @PathVariable String baseCurrency, @PathVariable String targetCurrency) {
		String startDate = exchangeRateService.getStartDate(date).toString();
		ExchangeRateDetails exchangeRate = restTemplate.getForObject(exchangeRateService.getURL(startDate, date, baseCurrency, targetCurrency), ExchangeRateDetails.class);
		exchnageRateDetails = exchangeRateService.getExchangeRateDetails(exchangeRate, date, baseCurrency, targetCurrency);
		return exchnageRateDetails;
	}
}
