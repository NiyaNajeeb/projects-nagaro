package com.nagaro.code.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagaro.code.controller.AccountController;
import com.nagaro.code.exception.MandatoryDataNotPresent;
import com.nagaro.code.service.AccountSearchService;
import com.nagaro.code.service.AccountStatementService;
import com.nagaro.code.util.StringUtil;
import com.nagaro.code.vo.AccountStatementVO;

@Service
public class AccountSearchServiceImpl implements AccountSearchService {
	Logger logger = LoggerFactory.getLogger(AccountSearchServiceImpl.class);

	@Autowired
	AccountStatementService accountStatementService;
	@Autowired
	StringUtil stringUtil;

	@Override
	public List<AccountStatementVO> fetchAccountDetails(Integer accountID, String startDate, String endDate,
			String startAmountRange, String endAmountRange) {
		logger.info("Entered fetchAccountDetails in AccountSearchServiceImpl");

		validateSearchParameters(accountID, startDate, endDate, startAmountRange, endAmountRange); // to do
		String searchType = getSearchType(startDate, endDate, startAmountRange, endAmountRange);
		switch (searchType) {
		case "ADVANCED":
			return accountStatementService.fetchAccountStatements(accountID, startDate, endDate, startAmountRange,
					endAmountRange);
		case "BASIC":
			return accountStatementService.fetchAccountStatements(accountID);
		}
		return null;
	}

	private void validateSearchParameters(Integer accountID, String startDate, String endDate, String startAmountRange,
			String endAmountRange) {
		logger.info("Entered fetchAccountDetails in AccountSearchServiceImpl");

		if (accountID == null || accountID == 0) {
			throw new MandatoryDataNotPresent("Account ID manadatory");
		}
		if (!StringUtil.isEmpty(startDate) && StringUtil.isEmpty(endDate)) {
			throw new MandatoryDataNotPresent("if start date selected, end date should be mandatory");

		}
		if (StringUtil.isEmpty(startDate) && !StringUtil.isEmpty(endDate)) {
			throw new MandatoryDataNotPresent("if end  date selected, start date should be mandatory");

		}
		if (!StringUtil.isEmpty(startAmountRange) && StringUtil.isEmpty(endAmountRange)) {
			throw new MandatoryDataNotPresent("if start amount range selected, end amount range should be mandatory");

		}
		if (StringUtil.isEmpty(startAmountRange) && !StringUtil.isEmpty(endAmountRange)) {
			throw new MandatoryDataNotPresent(
					"if end amount range  date selected, start amount range should be mandatory");

		}
	}

	private String getSearchType(String startDate, String endDate, String startAmountRange, String endAmountRange) {
		logger.info("Entered getSearchType in AccountSearchServiceImpl");

		String role = "BASIC";
		if (!StringUtil.isEmpty(startDate) || !StringUtil.isEmpty(endDate) || !StringUtil.isEmpty(startAmountRange)
				|| !StringUtil.isEmpty(endAmountRange)) {

			role = "ADVANCED";
		}
		return role;
	}
}
