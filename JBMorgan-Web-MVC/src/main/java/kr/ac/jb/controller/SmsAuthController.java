package kr.ac.jb.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import kr.ac.jb.util.RandomAuth;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class SmsAuthController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//인증번호를 받을 전화번호
		String phoneNumber = request.getParameter("phoneNumber");
		
		int authNo = RandomAuth.makeAuthNo();
		
		String api_key = "NCSRI4WNPD7KEZQ6";
		String api_secret = "WASOT4YPEKCAPHICV7KM1IWKFESCN4EK";
		
		
		Message coolsms = new Message(api_key, api_secret);
		
		
//		4 params(to, from, type, text) are mandatory. must be filled
//	    HashMap<String, String> params = new HashMap<String, String>();
//	    params.put("to", "01000000000"); // 수신번호
//	    params.put("from", "01000000000"); // 발신번호
//	    params.put("type", "SMS"); // Message type ( SMS, LMS, MMS, ATA )
//	    params.put("text", "Test Message"); // 문자내용    
//	    params.put("app_version", "JAVA SDK v1.2"); // application name and version(필수아님)
		
	    HashMap<String, String> params = new HashMap<String,String>();
	    params.put("to", phoneNumber);
	    params.put("from", "01090258049");
	    params.put("type", "SMS");
	    params.put("text", "[JBMorgan 인증번호 " + authNo + "]" );
	    
	    
	    try {
	        JSONObject obj = (JSONObject) coolsms.send(params);
	        request.setAttribute("authNo", authNo);
	        System.out.println(obj.toString());
	        
	      } catch (CoolsmsException e) {
	        System.out.println(e.getMessage());
	        System.out.println(e.getCode());
	      }
		
		return "/login/smsAuth.jsp";
	}

}
