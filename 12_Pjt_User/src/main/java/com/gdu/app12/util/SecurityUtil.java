package com.gdu.app12.util;

import java.security.MessageDigest;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {

	// 크로스 사이트 스크립팅(Cross Site Scripting) 방지하기, 전달된 스트링(String str)을 변환해서 반환(String)한다
	// 작성할때 <script>history.back</script> 이런식으로 정삭적인 텍스트가 아니게 입력하여 공격하는 방식을 말한다
	public String preventXSS(String str) {
		str = str.replace("<", "&lt;"); // 이 공격을 할거면 반드시 <sciprt>를 써야 하는데, db에 저장할땐 문자로 변환하고 보여줄때는 반대로 보여줄수는 있다 
		str = str.replace(">", "&gt;");
		return str;
	}
	
	// 인증코드 반환하기, 인수: 숫자를 쓸거냐 문자를 쓸거냐 길이는 어떻게 할거냐
	public String getRandomString(int count, boolean letters, boolean numbers) {
		return RandomStringUtils.random(count, letters, numbers); // commons lang3 기능을 처음 써본다
	}

	// SHA-256 암호화하기
	/*
	 1. 입력 값을 256비트(32바이트) 암호화 처리하는 해시 알고리즘이다.
	 2. 암호화는 가능하지만 복호화(암호화 되기 전 데이터 모른다)는 불가능한 알고리즘이다.
	 3. 암호화된 결과를 저장하기 위한 32바이트 byte 배열이 필요하다.
	 4. 1바이트 -> 16진수로 변환해서 암호화된 문자열을 만든다.(1바이트 -> 8비트, 4개씩 잘라서 한글자씩 나온다 총 2개가 나온다, 1바이트는 16진수 문자 2개로 변환된다)
	 5. 32바이트 -> 16진수로 변환하려면 64글자가 생성된다. (DB 칼럼의 크기를 VARCHAR2(64 BYTE)로 설정한다.)  
	 6. java.security 패키지를 이용한다(별도의 라이브러리 안사용함)
	 */ 
	public String getsha256(String str) { // 비밀번호가 들어온다, 바이틀 배열로 바꿔준다. 위에 설명한 작업이 들어간다.
		MessageDigest messageDigest = null;
		
		try { // 값을 가져오려면 try가 필요하다
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(str.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		byte[] b = messageDigest.digest(); // 암호화된 32 바이트 크기의 byte 배열 b가 생성된다, 2진수를 16진수로 바꾸는 과정이 필요(for문)
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < b.length; i++) {
			sb.append(String.format("%2X", b[i]) ); // 암호화가 진행되는 과정, %X : 16진수(헥사코드)를 의미, %2X : 2자리의 16진수를 의미(X를 대문자로 하냐 x소문자로 하느냐 따라 다르게 표현된다.)
		}
		return sb.toString();
	}
	
	
	
}
