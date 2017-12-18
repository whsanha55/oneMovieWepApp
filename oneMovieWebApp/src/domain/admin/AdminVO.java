package domain.admin;

public class AdminVO {

	int adminNo;
	String adminId;
	String adminPwd;
	
	
	public AdminVO() {
		super();
	}


	public AdminVO(int adminNo, String adminId, String adminPwd) {
		super();
		this.adminNo = adminNo;
		this.adminId = adminId;
		this.adminPwd = adminPwd;
	}


	public int getAdminNo() {
		return adminNo;
	}


	public void setAdminNo(int adminNo) {
		this.adminNo = adminNo;
	}


	public String getAdminId() {
		return adminId;
	}


	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}


	public String getAdminPwd() {
		return adminPwd;
	}


	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}
	
		
	
	
}
