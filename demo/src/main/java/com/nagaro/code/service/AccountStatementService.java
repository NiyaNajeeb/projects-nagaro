package com.nagaro.code.service;

import java.util.List;

import com.nagaro.code.vo.AccountStatementVO;

public interface AccountStatementService {

	List<AccountStatementVO> fetchAccountStatements(Integer accountID, String startDate, String endDate,
			String startAmountRange, String endAmountRange);

	List<AccountStatementVO> fetchAccountStatements(Integer accountID);
}
