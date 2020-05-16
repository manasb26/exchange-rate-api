package com.exchangerate.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exchangerate.models.ExchangeRateHistory;
import com.exchangerate.service.ExchangeRateHistoryService;

@RestController
@RequestMapping("/api/exchange-rate/history/")
public class ExchangeRateHistoryController {
	
	@Autowired
	ExchangeRateHistoryService exchangeRateHistoryService;
	
	@GetMapping
	@RequestMapping("/daily/{year}/{month}/{day}")
	public Collection<ExchangeRateHistory> getMonthlyExchangeRateHistory(@PathVariable int year, @PathVariable int month, @PathVariable int day){
		return exchangeRateHistoryService.getDailyExchangeRateHistory(year, month, day);
	}
	
	@GetMapping
	@RequestMapping("/daily/{year}/{month}")
	public Collection<ExchangeRateHistory> getDailyExchangeRateHistory(@PathVariable int year, @PathVariable int month){
		return exchangeRateHistoryService.getMonthlyExchangeRateHistory(year, month);
	}

}
