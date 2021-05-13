package com.nagaro.code.vomapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.nagaro.code.util.MD4Hashing;
import com.nagaro.code.vo.AccountStatementVO;
import com.nagaro.code.vo.AccountVO;

public class AccountStatementMapperVO implements RowMapper<AccountStatementVO>{
	
	@Override
	public AccountStatementVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		MD4Hashing mD4Hashing = new MD4Hashing();


		AccountStatementVO accountStatementVO = new AccountStatementVO();
		AccountVO accountVO = new AccountVO(); 
		accountStatementVO.setId(rs.getInt("ID"));
		accountStatementVO.setAmount(rs.getString("amount"));
		accountStatementVO.setDate(rs.getString("datefield"));		
		accountVO.setId(rs.getInt("account_id"));
		accountVO.setAccountNumber(mD4Hashing.generateHashedValue(rs.getString("account_number")));

		accountVO.setAccountType(rs.getString("account_type"));		
		accountStatementVO.setAccountVO(accountVO);
		return accountStatementVO;
	}

}
