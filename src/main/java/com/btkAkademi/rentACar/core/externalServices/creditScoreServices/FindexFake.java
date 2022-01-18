package com.btkAkademi.rentACar.core.externalServices.creditScoreServices;




public class FindexFake {
	
	public int getScoreOfIndividualCustomer(String nationalityId) {
		int max= 1900;
		int min= 650;
				
		int score = (int) Math.floor(Math.random()*(max-min+1)+min);
		System.out.println("findex score :"+score);
		return score;
	}
	public int getScoreOfCorporateCustomer(String taxNumber) {
		int max= 1900;
		int min= 650;
				
		int score = (int) Math.floor(Math.random()*(max-min+1)+min);
		System.out.println("findex score :"+score);
		return score;
		
	}
}
