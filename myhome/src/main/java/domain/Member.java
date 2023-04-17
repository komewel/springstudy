package domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Member {
	private int memberNo;
	private String id;
	private String pw;
	private String name;
	private String email;
	private String registedDate;
}
