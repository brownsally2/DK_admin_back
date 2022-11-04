package com.DK.admin.adSendEmail;

public class AdminEmailMain {
	public static void main(String[] args) {
		EmailSender mail = new EmailSender("developerkirby@naver.com","developerkirby","Devkirby1234!");
//		mail.sendSimple("heej0258@naver.com","heeju");
		// 받는 사람 list로 받아와서 for문???
		mail.sendHtml("heej0258@naver.com","heeju");
		System.out.println("message sent successfully...");
	}
	  
}
