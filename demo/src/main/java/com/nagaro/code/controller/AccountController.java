package com.nagaro.code.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nagaro.code.model.SearchRequest;
import com.nagaro.code.service.AccountSearchService;

@RestController
public class AccountController {

	Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	AccountSearchService accountSearchService;
	 
	@RequestMapping(value = "/searchAccount", method = RequestMethod.POST)
	public ResponseEntity<?> searchCustomerAccount(@RequestBody SearchRequest searchRequest) throws Exception {
		
		logger.info("Entered searchCustomerAccount in AccountController");
		return ResponseEntity.ok(accountSearchService.fetchAccountDetails(searchRequest.getAccountID(),
				searchRequest.getStartDate(), searchRequest.getEndDate(), searchRequest.getStartAmountRange(),
				searchRequest.getEndAmountRange()));

	}

	}
