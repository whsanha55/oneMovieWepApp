package controller.member;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;

public class SendMail {

	public void sendTempPwd(String email, String tempPwd) throws Exception {
	
		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", "true"); 	//gmail은 true 고정
		props.put("mail.smtp.host", "smtp.gmail.com");		//gmail 서버 주소
		props.put("mail.smtp.auth", "true");				//gmail은 true 고정
		props.put("mail.smtp.port", "587");					//gmail 포트 
		
		Authenticator auth = new MyAuthentication();
		
		//session 생성 및 MimeMessage 생성
		Session session = Session.getDefaultInstance(props, auth);
		MimeMessage msg = new MimeMessage(session);
		
		try {

			//메일 발송 시간
			msg.setSentDate(new Date());

			//이메일 발신자
			InternetAddress from = new InternetAddress("admin<onemoviemovie@gmail.com>");
			msg.setFrom(from);
			
			//이메일 수신자

			InternetAddress to = new InternetAddress(email);
			msg.setRecipient(Message.RecipientType.TO, to);
			
			//이메일 제목
			msg.setSubject("임시 비밀번호가 발급되었습니다.", "UTF-8");
			
			//이메일 내용
			msg.setText("회원님의 비밀번호 분실로 발급된 임시 비밀번호는 " + tempPwd 
					    + " 입니다. 임시비밀번호로 로그인하신 후 회원 정보 수정 메뉴에서 비밀번호를 변경해주세요.", "UTF-8");
			
			//이메일 헤더
			msg.setHeader("content-Type", "text/html");
			
			//메일 보내기
			javax.mail.Transport.send(msg);
		
			
		} finally {
			// ?
		} 
	}
		
}

	

class MyAuthentication extends Authenticator {
	
	PasswordAuthentication pa;
	
	public MyAuthentication() {
		String id = "onemoviemovie";	//구글 ID
		String pwd = "admin1movie";		//구글 비밀번호
		
		//ID와 비밀번호를 입력한다.
		pa = new PasswordAuthentication(id, pwd);
	}
	
	// 시스템에서 사용하는 인증정보
	public PasswordAuthentication getPasswordAuthentication() {
		return pa;
	}
	
	
}
	
	
	
	
	




