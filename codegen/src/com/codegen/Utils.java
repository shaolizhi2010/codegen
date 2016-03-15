package com.codegen;

public class Utils {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static String captureFirst(String str) {

		char[] cs = str.toCharArray();
		cs[0] -= 32;
		return String.valueOf(cs);

	}

}
