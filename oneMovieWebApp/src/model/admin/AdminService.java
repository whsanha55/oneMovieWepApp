package model.admin;

import domain.admin.AdminVO;

public class AdminService {
	
	private static AdminService instance = new AdminService();
	
	private AdminService() {
		
	}
	
	public static AdminService getInstance() {
		return instance;
	}
	

	public AdminVO retrieveAdmin(String adminId, String adminPwd) throws Exception {
		AdminDAO dao = AdminDAO.getInstance();
		return dao.selectAdmin(adminId, adminPwd);		
	}
	
}
