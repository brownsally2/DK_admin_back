package com.DK.admin.adSendEmail;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

public class EmailSender {
	// 메일을 보내는 이의 메일주소, 보내는 이름, 패스워드
	private String emailAddress, senderName, emailPassword;
	
	public EmailSender(String emailAddress, String senderName, String emailPassword) {
		this.emailAddress = emailAddress;
		this.senderName = senderName;
		this.emailPassword = emailPassword;
	}


	public String getEmailAddress() {
		return emailAddress;
	}


	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}


	public String getSenderName() {
		return senderName;
	}


	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}


	public String getEmailPassword() {
		return emailPassword;
	}


	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}
	
	//메일보내기
	void sendSimple(String email, String name) {
		//메일 수신자 이메일, 이름
		SimpleEmail mail = new SimpleEmail();
		mail.setCharset("utf-8");
		mail.setDebug(true);
		mail.setHostName("smtp.naver.com");
		mail.setAuthentication("developerkirby@naver.com","Devkirby1234!");
		mail.setSmtpPort(587);
		mail.setStartTLSEnabled(true);
		
		try {
			// 보내는 사람 이메일 주소와 이름 
			mail.setFrom("developerkirby@naver.com","developerkirby");
			mail.addTo("heej0258@naver.com","heeju"); // 받을사람 지정
			
			// 제목지정
			mail.setSubject("생일 축하합니다");
			mail.setMsg(name + "님을 위한 10% 할인 쿠폰 발행!!!축하합니다.");
			
			
			mail.send(); // 보내기 버튼 클릭
		}catch(EmailException e) {
			System.out.println(e.getMessage());
		}
	}
	
// 메일보내기 html 형식으로 보내기
	 void sendHtml(String email, String name) {
		HtmlEmail mail = new HtmlEmail();
		mail.setCharset("utf-8");
		mail.setCharset("utf-8");
		mail.setDebug(true);
		mail.setHostName("smtp.naver.com");
		mail.setAuthentication("developerkirby@naver.com","Devkirby1234!");
		mail.setSmtpPort(587);
		mail.setStartTLSEnabled(true);
		
		try {
			mail.setFrom("developerkirby@naver.com", "developerkirby");
			mail.addTo("heej0258@naver.com", "heeju");
			
			mail.setSubject("생일 축하 !!!");
			StringBuffer msg = new StringBuffer();
			msg.append("<html><body>");
			msg.append("<a href='http://naver.com' target='_blank'>네이버</a>");
			msg.append("</body></html>");
			mail.setHtmlMsg(msg.toString());
			mail.send();
			
		}catch(EmailException e){
			System.out.println(e.getMessage());
		}
		
		
	}
}
