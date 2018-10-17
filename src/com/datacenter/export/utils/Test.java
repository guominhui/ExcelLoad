package com.datacenter.export.utils;

public class Test {

	public static void main(String[] args) {
		ExcelUtil.getHSSFWorkbook("1", new String[]{"2","3"}, new String[][]{{"1"},{"2"}},null);
	}
}
