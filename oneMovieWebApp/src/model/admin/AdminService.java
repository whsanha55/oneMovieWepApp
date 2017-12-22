package model.admin;

import domain.admin.AdminVO;

public class AdminService {

	public AdminVO retrieveAdmin(String adminId, String adminPwd) throws Exception {
		AdminDAO dao = AdminDAO.getInstance();
		return dao.selectAdmin(adminId, adminPwd);		
	}
	
}
