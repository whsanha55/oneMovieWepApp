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
		props.put("mail.smtp.starttls.enable", "true"); 	//gmail�� true ����
		props.put("mail.smtp.host", "smtp.gmail.com");		//gmail ���� �ּ�
		props.put("mail.smtp.auth", "true");				//gmail�� true ����
		props.put("mail.smtp.port", "587");					//gmail ��Ʈ 
		
		Authenticator auth = new MyAuthentication();
		
		//session ���� �� MimeMessage ����
		Session session = Session.getDefaultInstance(props, auth);
		MimeMessage msg = new MimeMessage(session);
		
		try {

			//���� �߼� �ð�
			msg.setSentDate(new Date());

			//�̸��� �߽���
			InternetAddress from = new InternetAddress("admin<onemoviemovie@gmail.com>");
			msg.setFrom(from);
			
			//�̸��� ������

			InternetAddress to = new InternetAddress(email);
			msg.setRecipient(Message.RecipientType.TO, to);
			
			//�̸��� ����
			msg.setSubject("�ӽ� ��й�ȣ�� �߱޵Ǿ����ϴ�.", "UTF-8");
			
			//�̸��� ����
			msg.setText("ȸ������ ��й�ȣ �нǷ� �߱޵� �ӽ� ��й�ȣ�� " + tempPwd 
					    + " �Դϴ�. �ӽú�й�ȣ�� �α����Ͻ� �� ȸ�� ���� ���� �޴����� ��й�ȣ�� �������ּ���.", "UTF-8");
			
			//�̸��� ���
			msg.setHeader("content-Type", "text/html");
			
			//���� ������
			javax.mail.Transport.send(msg);
		
			
		} finally {
			// ?
		} 
	}
		
}

	

class MyAuthentication extends Authenticator {
	
	PasswordAuthentication pa;
	
	public MyAuthentication() {
		String id = "onemoviemovie";	//���� ID
		String pwd = "admin1movie";		//���� ��й�ȣ
		
		//ID�� ��й�ȣ�� �Է��Ѵ�.
		pa = new PasswordAuthentication(id, pwd);
	}
	
	// �ý��ۿ��� ����ϴ� ��������
	public PasswordAuthentication getPasswordAuthentication() {
		return pa;
	}
	
	
}
	
	
	
	
	




