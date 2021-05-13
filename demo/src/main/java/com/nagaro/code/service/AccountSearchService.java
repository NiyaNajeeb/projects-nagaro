package com.nagaro.code.service;

import java.util.List;

import com.nagaro.code.vo.AccountStatementVO;

public interface AccountSearchService {
	List<AccountStatementVO> fetchAccountDetails(Integer accountID, String startDate, String endDate,
			String startAmountRange, String endAmountRange);
}
