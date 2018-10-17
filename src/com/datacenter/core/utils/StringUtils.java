package com.datacenter.core.utils;

public class StringUtils {

	public static boolean isBlank(String beWillToValidate){
		if (null==beWillToValidate||"".equals(beWillToValidate)) {
			return true;
		}
		return false;
	}
}
