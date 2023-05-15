package gdu.com.app12.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private int userNo;
	private String id;
	private String pw;
	private String email;	
	private String gender;	
	private String mobile;	
	private String birthyear;	
	private String birthdate;	
	private String postcode;	
	private String roadAddress;	
	private String jibunAddress;	
	private String detailAddress;
	private String extraAddress;
	private int	agreeCode; 
	private Date joinedAt; 
	private Date pwModifiedAt;
	private String autologinId;
	private Date autologinExpiredAt;
}
