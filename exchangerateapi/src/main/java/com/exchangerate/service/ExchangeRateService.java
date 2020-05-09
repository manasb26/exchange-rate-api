package com.exchangerate.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.exchangerate.exceptions.InvalidCurrencyException;
import com.exchangerate.exceptions.InvalidDateException;
import com.exchangerate.models.ExchangeRateDetails;
import com.exchangerate.models.ExchangeRateResponse;

@Component
public class ExchangeRateService {

	private static final String DESCENDING_TREND = "descending";
	private static final String ASCENDING_TREND = "ascending";
	private static final String CONSTANT_TREND = "constant";
	private static final String UNDEFINED_TREND = "undefined";

	@Autowired
	private ExchangeRateResponse exchangeRateDetails;

	public ExchangeRateResponse getExchangeRateDetails(ExchangeRateDetails exchangeRate, String date,
			String baseCurrency, String targetCurrency) {

		LocalDate requestedDate = LocalDate.parse(date);

		if(requestedDate.isBefore(LocalDate.of(2000, 01, 01)) || requestedDate.isAfter(LocalDate.now().minusDays(1))){
			throw new InvalidDateException("The entered date is invalid!! Provide a date between 2000-01-01 and " + LocalDate.now() + " in YYYY-MM-DD format");
		}
		
		double rate = getExchangeRate(exchangeRate, baseCurrency, targetCurrency);

		double averageRate = getAverageRate(exchangeRate, baseCurrency, targetCurrency);
		String trend = getExhangeRateTrend(exchangeRate, baseCurrency, targetCurrency);

		exchangeRateDetails.setCurrentRate(rate);
		exchangeRateDetails.setAverageRate(averageRate);
		exchangeRateDetails.setTrend(trend);

		return exchangeRateDetails;

	}

	private String getExhangeRateTrend(ExchangeRateDetails exchangeRate, String baseCurrency, String targetCurrency) {

		boolean ascendingFlag = false, descendingFlag = false, constantFlag = false;
		double lastRate = 0, baseCurrencyRate = 0.0, targetCurrencyRate = 0.0, currentRate = 0.0;
		int dayNum = 0;

		String trend = UNDEFINED_TREND;

		LocalDate startDate = getStartDate(exchangeRate.getEnd_at());
		LocalDate endDate = LocalDate.parse(exchangeRate.getEnd_at());

		Map<String, Map<String, Double>> ratesMap = exchangeRate.getRates();

		while (!startDate.isEqual(endDate)) {
			String key = startDate.toString();
			Map<String, Double> currencyRateMap = ratesMap.get(key);
			if (currencyRateMap != null && (!(startDate.getDayOfWeek() == DayOfWeek.SATURDAY
					|| startDate.getDayOfWeek() == DayOfWeek.SUNDAY))) {
				baseCurrencyRate = currencyRateMap.get(baseCurrency).doubleValue();
				targetCurrencyRate = currencyRateMap.get(targetCurrency).doubleValue();
				currentRate = targetCurrencyRate / baseCurrencyRate;
				dayNum++;

				if (dayNum == 1) {
					lastRate = currentRate;
					startDate = startDate.plusDays(1);
					continue;
				}

				if (lastRate == currentRate) {
					constantFlag = true;
				}

				if (lastRate > currentRate) {
					descendingFlag = true;
				}

				if (lastRate < currentRate) {
					ascendingFlag = true;
				}

				lastRate = currentRate;

			}
			startDate = startDate.plusDays(1);
		}

		if(constantFlag && !descendingFlag && !ascendingFlag)
			trend = CONSTANT_TREND;
		else {
			if(ascendingFlag && !descendingFlag)
				trend = ASCENDING_TREND;
			
			if(ascendingFlag && !descendingFlag)
				trend = DESCENDING_TREND;
		}
		return trend;
	}

	private double getAverageRate(ExchangeRateDetails exchangeRate, String baseCurrency, String targetCurrency) {

		Map<String, Map<String, Double>> ratesMap = exchangeRate.getRates();
		LocalDate startDate = getStartDate(exchangeRate.getEnd_at());

		LocalDate endDate = LocalDate.parse(exchangeRate.getEnd_at());

		double baseCurrencyRate = 0;
		double targetCurrencyRate = 0;
		double baseToTargetRate = 0;

		while (!startDate.isEqual(endDate)) {
			String key = startDate.toString();
			Map<String, Double> currencyRateMap = ratesMap.get(key);
			if (currencyRateMap != null && (!(startDate.getDayOfWeek() == DayOfWeek.SATURDAY
					|| startDate.getDayOfWeek() == DayOfWeek.SUNDAY))) {
				baseCurrencyRate = currencyRateMap.get(baseCurrency);
				targetCurrencyRate = currencyRateMap.get(targetCurrency);
				baseToTargetRate += targetCurrencyRate / baseCurrencyRate;
			}
			startDate = startDate.plusDays(1);
		}

		double averageCurrencyRate = baseToTargetRate / (ratesMap.size() - 1);

		return averageCurrencyRate;
	}

	private double getExchangeRate(ExchangeRateDetails exchangeRate, String baseCurrency, String targetCurrency) {
		Map<String, Map<String, Double>> ratesMap = exchangeRate.getRates();
		Map<String, Double> currencyRateMap = ratesMap.get(exchangeRate.getEnd_at());

		if (currencyRateMap.containsKey(baseCurrency) && currencyRateMap.containsKey(targetCurrency)) {
			double baseCurrencyRate = currencyRateMap.get(baseCurrency);
			double targetCurrencyRate = currencyRateMap.get(targetCurrency);
			return targetCurrencyRate / baseCurrencyRate;
		} else {
			throw new InvalidCurrencyException("The base or the target currency is not a valid currency!");
		}
	}

	public LocalDate getStartDate(String date) {
		LocalDate requestDate = LocalDate.parse(date);
		LocalDate startDate = requestDate;
		int subtractedDays = 0;
		while (subtractedDays != 5) {
			startDate = startDate.minusDays(1);
			if (!(startDate.getDayOfWeek() == DayOfWeek.SATURDAY || startDate.getDayOfWeek() == DayOfWeek.SUNDAY)) {
				subtractedDays++;
			}
		}
		return startDate;
	}

	public String getURL(String startDate, String endDate, String baseCurrency, String targetCurrency) {
		return "https://api.exchangeratesapi.io/history?start_at=" + startDate + "&end_at=" + endDate + "&symbols="
				+ baseCurrency + "," + targetCurrency;
	}
}
