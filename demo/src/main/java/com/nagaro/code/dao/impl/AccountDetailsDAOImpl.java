package com.nagaro.code.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nagaro.code.dao.AccountDetailsDAO;
import com.nagaro.code.service.impl.AccountStatementServiceImpl;
import com.nagaro.code.vo.AccountStatementVO;
import com.nagaro.code.vomapper.AccountStatementMapperVO;

@Repository
@Transactional
public class AccountDetailsDAOImpl implements AccountDetailsDAO {
	Logger logger = LoggerFactory.getLogger(AccountDetailsDAOImpl.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<AccountStatementVO> fetchAccountDetails(int accountID) {
		// TODO Auto-generated method stub
		logger.info("Entered fetchAccountDetails in AccountDetailsDAOImpl");

		List<AccountStatementVO> accountStatementVOList = null;

		String query = "select s.account_id as account_id ,s.datefield as datefield,s.ID as ID,s.amount as amount, "
				+ "a.account_type as account_type,a.account_number "
				+ "as account_number from statement s inner join account a " + "on a.ID = s.account_id "
				+ "where  s.account_id = ? ";

		try {
			accountStatementVOList = jdbcTemplate.query(query, new Object[] { accountID },
					new AccountStatementMapperVO());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return accountStatementVOList;
	}

}
