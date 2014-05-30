package com.test;

public class Test {
	
	public static void main(String[] args) {
		String a = "a_b_c_123";
		String str1 = a.substring(0, a.lastIndexOf("_"));
		System.out.println(str1.substring(str1.lastIndexOf("_") + 1));
	}
}