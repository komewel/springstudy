package com.gdu.movie.util;

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
	

	
	
	
}
