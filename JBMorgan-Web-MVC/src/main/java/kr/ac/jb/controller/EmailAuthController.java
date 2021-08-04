package kr.ac.jb.controller;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.jb.util.RandomAuth;

public class EmailAuthController implements Controller {
	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 환경설정
		// https://ktko.tistory.com/entry/JAVA-SMTP%EC%99%80-Mail-%EB%B0%9C%EC%86%A1%ED%95%98%EA%B8%B0Google-Naver
		
		// 사용자의 메일주소
		String emailAddr = request.getParameter("emailAddr"); 
				
		String mail_id = "jb8068049@gmail.com";
		String mail_pwd = "whd806712./!";
		
		int authNo = RandomAuth.makeAuthNo();
		
		//서버 정보 설정
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 465); 
        prop.put("mail.smtp.auth", "true"); 
        prop.put("mail.smtp.ssl.enable", "true"); 
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        
        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mail_id, mail_pwd);
            }
        });
        
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mail_id));

            //수신자 메일 주소
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddr)); 

            // Subject
            message.setSubject("JBMorgan 인증번호 발송 안내 메일입니다.");

            // Text
            message.setText("인증번호 6자리 : " + authNo);

            // send the message
            Transport.send(message); ////전송
            
            request.setAttribute("authNo", authNo);
            
            System.out.println("message sent successfully...");
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } 
		
		return "/login/emailAuth.jsp";
	}

}
