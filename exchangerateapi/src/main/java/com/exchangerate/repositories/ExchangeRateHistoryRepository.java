package com.exchangerate.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.exchangerate.models.ExchangeRateHistory;

@Repository
public interface ExchangeRateHistoryRepository extends JpaRepository<ExchangeRateHistory, Long> {

	@Query(value = "SELECT erh FROM ExchangeRateHistory erh WHERE erh.queryYear = :year AND erh.queryMonth = :month AND erh.queryDay = :day")
	public Collection<ExchangeRateHistory> findDailyExchangeRateHistory(@Param("year") int year,
			@Param("month") int month, @Param("day") int day);

	@Query(value = "SELECT erh FROM ExchangeRateHistory erh WHERE erh.queryYear = :year AND erh.queryMonth = :month")
	public Collection<ExchangeRateHistory> findMonthlyExchangeRateHistory(@Param("year") int year,
			@Param("month") int month);

}
