package com.exchangerate.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.exchangerate.models.ExchangeRateHistory;


@Repository
public interface ExchangeRateHistoryRepository extends JpaRepository<ExchangeRateHistory, Long> {


	@Query(value = "SELECT * FROM exchange_rate_history erh where erh.query_year = ?1 AND erh.query_month = ?2 AND erh.query_day = ?3 ", nativeQuery = true)
	public Collection<ExchangeRateHistory> getDailyExchangeRateHistory(int year, int month, int day);
	
	
	@Query(value = "SELECT * FROM exchange_rate_history erh where erh.query_year = ?1 AND erh.query_month = ?2", nativeQuery = true)
	public Collection<ExchangeRateHistory> getMonthlyExchangeRateHistory(int year, int month);

}
