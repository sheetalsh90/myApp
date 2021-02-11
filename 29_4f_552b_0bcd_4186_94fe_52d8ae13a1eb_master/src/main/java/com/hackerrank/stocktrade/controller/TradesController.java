package com.hackerrank.stocktrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.service.TradeService;
import com.hackerrank.stocktrade.service.UserService;

@RestController
@RequestMapping(value = "/trades")
public class TradesController {

	@Autowired
	TradeService tradeService;
	@Autowired
	UserService userService;

	@PostMapping
	public ResponseEntity<Object> addNewTrade(@RequestBody Trade trade) {
		return tradeService.saveTrade(trade);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getTradeById(@PathVariable Long id) {
		return tradeService.findTradeById(id);

	}

	@GetMapping
	public ResponseEntity<Object> getAllTrades() {
		return tradeService.findTradeAll();

	}

	@GetMapping(value = "/users/{userID}")
	public ResponseEntity<Object> getAllTradesByUserId(@PathVariable Long userID) {
		return tradeService.findTradeByUserId(userID);

	}

	@GetMapping(value = "/stocks/{stockSymbol}")
	public ResponseEntity<Object> getAllTradesBySymbolId(@PathVariable String stockSymbol) {
		return tradeService.findTradeBySymbol(stockSymbol);

	}

	@GetMapping(value = "/users/{userID}/stocks/{stockSymbol}")
	public ResponseEntity<Object> getAllTradesByUserIdAndStockId(@PathVariable Long userID,
			@PathVariable String stockSymbol) {
		return tradeService.findTradeByUserAndSymbol(userID,stockSymbol);

	}
}
