package com.exchangerate.service;

import java.time.LocalDate;
import java.util.Collection;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exchangerate.models.ExchangeRateHistory;
import com.exchangerate.models.ExchangeRateResponse;
import com.exchangerate.repositories.ExchangeRateHistoryRepository;

@Service
public class ExchangeRateHistoryService {

	@Autowired
	private ExchangeRateHistoryRepository exchangeRateHistoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	public ExchangeRateHistoryService() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	public void saveExchangeRateHistory(ExchangeRateResponse exchangeRateResponse, String date, String baseCurrency,
			String targetCurrency) {
		ExchangeRateHistory exchangeRateHistory = setExchangeRateHisory(exchangeRateResponse, date, baseCurrency,
				targetCurrency);
		exchangeRateHistoryRepository.saveAndFlush(exchangeRateHistory);
		
	}

	public ExchangeRateHistory setExchangeRateHisory(ExchangeRateResponse exchangeRateResponse, String date,
			String baseCurrency, String targetCurrency) {
		ExchangeRateHistory exchangeRateHistory = new ExchangeRateHistory();
		exchangeRateHistory = modelMapper.map(exchangeRateResponse, ExchangeRateHistory.class);
		LocalDate requestedDate = LocalDate.parse(date);
		exchangeRateHistory.setDateOfRequestedRate(requestedDate);
		exchangeRateHistory.setQueryYear(LocalDate.now().getYear());
		exchangeRateHistory.setQueryMonth(LocalDate.now().getMonthValue());
		exchangeRateHistory.setQueryDay(LocalDate.now().getDayOfMonth());
		exchangeRateHistory.setBaseCurrency(baseCurrency);
		exchangeRateHistory.setTargetCurrency(targetCurrency);
		return exchangeRateHistory;
	}

	public Collection<ExchangeRateHistory> getDailyExchangeRateHistory(int year, int month, int day) {
		Collection<ExchangeRateHistory> result =  exchangeRateHistoryRepository.getDailyExchangeRateHistory(year, month, day);
		return result;	
	}
	
	public Collection<ExchangeRateHistory> getMonthlyExchangeRateHistory(int year, int month) {
		Collection<ExchangeRateHistory> result =  exchangeRateHistoryRepository.getMonthlyExchangeRateHistory(year, month);
		return result;	
	}

}
