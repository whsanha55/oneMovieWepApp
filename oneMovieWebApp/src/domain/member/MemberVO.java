package domain.member;

public class MemberVO {
	
	String memberNo;
	String memberId;
	String memberPwd;
	String name;
	String gender;
	String phone;
	String email;
	String address1;
	String address2;
	String zipcode;
	String isWithdraw;
	String withdrawDay;
	
		
	
	
	public MemberVO() {
		super();
	}
	
	
		
	
	public MemberVO(String memberId, String memberPwd, String name, String gender, String phone, String email,
			String address1, String address2, String zipcode) {
		super();
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.name = name;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.address1 = address1;
		this.address2 = address2;
		this.zipcode = zipcode;
	}




	public MemberVO(String memberNo, String memberId, String memberPwd, String name, String gender, String phone,
			String email, String address1, String address2, String zipcode, String isWithdraw, String withdrawDay) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.name = name;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.address1 = address1;
		this.address2 = address2;
		this.zipcode = zipcode;
		this.isWithdraw = isWithdraw;
		this.withdrawDay = withdrawDay;
	}




	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPwd() {
		return memberPwd;
	}
	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	
			
	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getIsWithdraw() {
		return isWithdraw;
	}
	public void setIsWithdraw(String isWithdraw) {
		this.isWithdraw = isWithdraw;
	}
	public String getWithdrawDay() {
		return withdrawDay;
	}
	public void setWithdrawDay(String withdrawDay) {
		this.withdrawDay = withdrawDay;
	}
	
	
	
	

}
