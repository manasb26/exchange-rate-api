package com.exchangerate.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.exchangerate.models.ExchangeRateDetails;
import com.exchangerate.models.ExchangeRateResponse;
import com.exchangerate.service.ExchangeRateHistoryService;
import com.exchangerate.service.ExchangeRateService;

@RestController
@RequestMapping("/api/exchange-rate/")
public class ExchangeRateController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ExchangeRateService exchangeRateService;

	@Autowired
	private ExchangeRateResponse exchangeRateResponse;

	@Autowired
	private ExchangeRateHistoryService exchangeRateHistoryService;

	@GetMapping
	@RequestMapping("{date}/{baseCurrency}/{targetCurrency}")
	@Transactional
	public ExchangeRateResponse getExchangeRate(@PathVariable String date, @PathVariable String baseCurrency,
			@PathVariable String targetCurrency) {
		ExchangeRateDetails exchangeRate = new ExchangeRateDetails();
		String startDate = exchangeRateService.getStartDate(date).toString();
		exchangeRate = restTemplate.getForObject(
				exchangeRateService.getURL(startDate, date, baseCurrency, targetCurrency), ExchangeRateDetails.class);
		exchangeRateResponse = exchangeRateService.getExchangeRateDetails(exchangeRate, date, baseCurrency,
				targetCurrency);
		exchangeRateHistoryService.saveExchangeRateHistory(exchangeRateResponse, date, baseCurrency, targetCurrency);
		return exchangeRateResponse;
	}

}
