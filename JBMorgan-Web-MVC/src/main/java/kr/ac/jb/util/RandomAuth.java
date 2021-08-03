package kr.ac.jb.util;

import java.util.concurrent.ThreadLocalRandom;

public class RandomAuth {
	
	// 6자리의 인증번호 생성하기
	
	public static int makeAuthNo() {
		
		return ThreadLocalRandom.current().nextInt(100000, 1000000); // 100000 ~ 999999
		
		
	}
	
	
}
