package com.hackerrank.stocktrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.stocktrade.service.TradeService;

@RestController
@RequestMapping(value = "/erase")
public class ResourcesController {
	@Autowired
	TradeService tradeService;

	@DeleteMapping(value ="/DELETE")
	public ResponseEntity<Object> eraseAllTrades() {
		tradeService.eraseAll();
		return new ResponseEntity<Object>("Order Deleted Successfully", HttpStatus.OK);
	}
	

}
