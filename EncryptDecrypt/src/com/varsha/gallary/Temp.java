package com.varsha.gallary;

public class Temp {

	public static void main(String[] args) {
		
		String location = "D:/data/enc/sdsd/asd.jpg";
		System.out.println(location.split("\\/")[location.lastIndexOf("//")-1]);
		
	}
}
