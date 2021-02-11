package com.hackerrank.stocktrade.service;

import java.security.cert.PKIXRevocationChecker.Option;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.User;
import com.hackerrank.stocktrade.repository.TradeRepository;
import com.hackerrank.stocktrade.repository.UserRepository;

public class TradeService {
	@Autowired
	TradeRepository repository;
	@Autowired
	UserRepository userRepository;

	public void eraseAll() {
		repository.deleteAll();

	}

	public ResponseEntity<Object> saveTrade(Trade trade) {
		if (repository.findById(trade.getId()) != null) {
			return new ResponseEntity<Object>("Already Exist", HttpStatus.BAD_REQUEST);
		}
		repository.save(trade);
		return new ResponseEntity<Object>(trade, HttpStatus.CREATED);
	}

	public ResponseEntity<Object> findTradeById(Long id) {
		Optional<Trade> trade = repository.findById(id);
		if (trade.isPresent()) {
			return new ResponseEntity<Object>(trade.get(), HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<Object>(trade.get(), HttpStatus.OK);
	}

	public ResponseEntity<Object> findTradeAll() {
		List<Trade> list = repository.findAll();
		Collections.sort(list, new Comparator<Trade>() {

			@Override
			public int compare(Trade t1, Trade t2) {
				if (t1.getId() > t2.getId()) {
					return 1;
				}
				if (t1.getId() < t2.getId()) {
					return -1;
				}
				return 0;
			}
		});
		return new ResponseEntity<Object>(list, HttpStatus.OK);
	}

	public ResponseEntity<Object> findTradeByUserId(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			List<Trade> list = repository.findByUserId(userId);
			Collections.sort(list, new Comparator<Trade>() {

				@Override
				public int compare(Trade t1, Trade t2) {
					if (t1.getId() > t2.getId()) {
						return 1;
					}
					if (t1.getId() < t2.getId()) {
						return -1;
					}
					return 0;
				}
			});
			new ResponseEntity<Object>(list, HttpStatus.OK);
		}

		return new ResponseEntity<Object>(userId, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<Object> findTradeBySymbol(String stockSymbol) {
		List<Trade> list = repository.findBySymbol(stockSymbol);
		if (list.isEmpty()) {
			new ResponseEntity<Object>(stockSymbol, HttpStatus.NOT_FOUND);
		}
		Collections.sort(list, new Comparator<Trade>() {

			@Override
			public int compare(Trade t1, Trade t2) {
				if (t1.getId() > t2.getId()) {
					return 1;
				}
				if (t1.getId() < t2.getId()) {
					return -1;
				}
				return 0;
			}
		});
		return new ResponseEntity<Object>(list, HttpStatus.OK);
	}

	public ResponseEntity<Object> findTradeByUserAndSymbol(Long userID, String stockSymbol) {
		Optional<User> user = userRepository.findById(userID);
		if (user.isPresent()) {
			return new ResponseEntity<Object>(user, HttpStatus.NOT_FOUND);
		}
		List<Trade> list = repository.findBySymbol(stockSymbol);
		if (list.isEmpty()) {
			return new ResponseEntity<Object>(stockSymbol, HttpStatus.NOT_FOUND);
		}
		List<Trade> tradeList = repository.findByUserIdAndSymbol(userID, stockSymbol);
		Collections.sort(tradeList, new Comparator<Trade>() {

			@Override
			public int compare(Trade t1, Trade t2) {
				if (t1.getId() > t2.getId()) {
					return 1;
				}
				if (t1.getId() < t2.getId()) {
					return -1;
				}
				return 0;
			}
		});
		return new ResponseEntity<Object>(tradeList, HttpStatus.OK);
	}
}
