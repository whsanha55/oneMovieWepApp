package model.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import conn.DBConn;
import domain.admin.AdminVO;

public class AdminDAO {

	private static AdminDAO instance = new AdminDAO();
	
	
	private AdminDAO() {
		
	}

	
	public static AdminDAO getInstance() {
			return instance;
	}
	
	
	//관리자 아이디와 비밀번호에 해당하는 관리자 정보를 반환하다.
	public AdminVO selectAdmin(String adminId, String adminPwd) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AdminVO admin = new AdminVO();
		
		try {
			conn = DBConn.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("select admin_no, admin_id, admin_pwd	");
			sql.append("from admin								");
			sql.append("where admin_Id = ? and admin_Pwd = ?		");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, adminId);
			pstmt.setString(2, adminPwd);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				admin.setAdminNo(rs.getInt(1));
				admin.setAdminId(rs.getString(2));
				admin.setAdminPwd(rs.getString(3));
			}			
			
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		
		return admin;		
	}
	
	
	
}
