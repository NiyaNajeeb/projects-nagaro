package com.nagaro.code.dao;

import java.util.List;

import com.nagaro.code.vo.AccountStatementVO;

public interface AccountDetailsDAO {

	List<AccountStatementVO> fetchAccountDetails(int accountID);

}
