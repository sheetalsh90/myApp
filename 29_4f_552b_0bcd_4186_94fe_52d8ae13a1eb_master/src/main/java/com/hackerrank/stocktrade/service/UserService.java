package com.hackerrank.stocktrade.service;

import java.util.Optional;

import javax.swing.RepaintManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.hackerrank.stocktrade.model.User;
import com.hackerrank.stocktrade.repository.UserRepository;

public class UserService {
	@Autowired
	UserRepository userRepository;
	
}
