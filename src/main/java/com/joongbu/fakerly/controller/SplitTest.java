package com.joongbu.fakerly.controller;

import java.util.Arrays;

public class SplitTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String userEmail = "tiger981105@gmail.com";
		String[] emailArr = userEmail.split("@|\\.");
		System.out.println(Arrays.toString(emailArr));
		switch (emailArr[1]) {
		case "gmail":
			System.out.println("지메일로 이동!");
			break;
		case "naver":
			System.out.println("네이버메일로 이동!");
			break;
		}
	}

}
