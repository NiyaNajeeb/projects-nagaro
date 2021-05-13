package com.nagaro.code.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.hsqldb.lib.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.nagaro.code.controller.AccountController;
import com.nagaro.code.dao.AccountDetailsDAO;
import com.nagaro.code.service.AccountStatementService;
import com.nagaro.code.util.DateUtil;
import com.nagaro.code.vo.AccountStatementVO;

@Service
public class AccountStatementServiceImpl implements AccountStatementService {
	Logger logger = LoggerFactory.getLogger(AccountStatementServiceImpl.class);

	@Autowired
	AccountDetailsDAO accountDetailsDAO;
	@Autowired
	DateUtil dateUtil;
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<AccountStatementVO> fetchAccountStatements(Integer accountID, String startDate, String endDate,
			String startAmountRange, String endAmountRange) {
		logger.info("Entered fetchAccountStatements in AccountStatementServiceImpl");

		List<AccountStatementVO> accountStatementVOList = accountDetailsDAO.fetchAccountDetails(accountID);
		return filterBySearchOptions(startDate, endDate, startAmountRange, endAmountRange, accountStatementVOList);

	}

	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public List<AccountStatementVO> fetchAccountStatements(Integer accountID) {
		logger.info("Entered fetchAccountStatements in AccountStatementServiceImpl accountId="+accountID);

		List<AccountStatementVO> accountStatementVOList = accountDetailsDAO.fetchAccountDetails(accountID);
		return filterBySearchOptions(null, null, null, null, accountStatementVOList);

	}

	private List<AccountStatementVO> filterBySearchOptions(String startDate, String endDate, String startAmountRange,
			String endAmountRange, List<AccountStatementVO> accountStatementVOList) {
		logger.info("Entered filterBySearchOptions in AccountStatementServiceImpl");

		/* Case 1: All parameters are empty, then 3 months data */
		if (StringUtil.isEmpty(startDate) && StringUtil.isEmpty(endDate) && StringUtil.isEmpty(startAmountRange)
				&& StringUtil.isEmpty(endAmountRange)) {
			startDate = dateUtil.getPastDate(6); // Since no data in 3 months put 6
			endDate = dateUtil.getCurrentDate();
			return filterAccountByDate(startDate, endDate, accountStatementVOList);
		}

		/* Case 2: start Date and End date present */
		if (!StringUtil.isEmpty(startDate) && !StringUtil.isEmpty(endDate)) {
			return filterAccountByDate(startDate, endDate, accountStatementVOList);
		}

		/* Case 3: amount ranges are present */
		if (!StringUtil.isEmpty(startAmountRange) && !StringUtil.isEmpty(endAmountRange)) {
			return filterAccountByAmount(startAmountRange, endAmountRange, accountStatementVOList);
		}

		return null;
	}

	private List<AccountStatementVO> filterAccountByAmount(String startAmountRange, String endAmountRange,
			List<AccountStatementVO> accountStatementVOList) {
		logger.info("Entered filterAccountByAmount in AccountStatementServiceImpl");

		return accountStatementVOList.stream().filter(accountStatementVO -> {
			return Double.parseDouble(accountStatementVO.getAmount()) >= Double.parseDouble(startAmountRange)
					&& Double.parseDouble(accountStatementVO.getAmount()) <= (Double.parseDouble(endAmountRange));
		}).collect(Collectors.toList());
	}

	private List<AccountStatementVO> filterAccountByDate(String startDate, String endDate,
			List<AccountStatementVO> accountStatementVOList) {
		logger.info("Entered filterAccountByDate in AccountStatementServiceImpl");

		return accountStatementVOList.stream().filter(accountStatementVO -> {
			try {
				return sdf.parse(accountStatementVO.getDate()).after(sdf.parse(startDate))
						&& sdf.parse(accountStatementVO.getDate()).before(sdf.parse(endDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;

		}).collect(Collectors.toList());

	}

}
