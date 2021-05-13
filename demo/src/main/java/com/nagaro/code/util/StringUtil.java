package com.nagaro.code.util;

import org.springframework.stereotype.Component;

@Component
public class StringUtil {

	public static boolean isEmpty(String arg) {
		if (arg == null || arg.isEmpty())
			return true;
		return false;
	}

}
