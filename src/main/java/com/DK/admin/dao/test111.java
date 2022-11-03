package com.DK.admin.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class test111 {
	public static void main(String[]args) {
		
	
	Set<Integer> ints = new HashSet<>(Arrays.asList(3,2));
	
	int[] primitive = ints.stream().mapToInt(Integer::intValue).toArray();
	
//	System.out.println(Arrays.toString(primitive));
	for(int i = 0; i< primitive.length; i++) {
		System.out.println(primitive[i]);
	}
	}
}

