package com.hackerrank.stocktrade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import com.hackerrank.stocktrade.model.Trade;

public interface TradeRepository extends JpaRepository<Trade, Long> {

	List<Trade> findByUserId(Long userId);

	List<Trade> findBySymbol(String stockSymbol);

	List<Trade> findByUserIdAndSymbol(Long userID, String stockSymbol);

}
