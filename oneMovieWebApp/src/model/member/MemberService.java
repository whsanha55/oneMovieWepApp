package model.member;

import java.sql.Connection;
import java.util.ArrayList;

import conn.DBConn;
import domain.member.MemberVO;

public class MemberService {

	
	
	//ȸ�� ���θ� Ȯ���ϴ�. (�α���)
	public MemberVO retrieveMember(String memberId, String memberPwd) throws Exception {
		MemberDAO dao = MemberDAO.getInstance();
		return dao.selectMember(memberId, memberPwd);
	}
	
	
	//���̵�, �̸��� �ߺ�Ȯ��
	public int retrieveMemberDuplicate(String keyfield, String keyword) throws Exception {
		MemberDAO dao = MemberDAO.getInstance();
		return dao.selectMemberDuplicate(keyfield, keyword);		
	}
	
	
		
	//ȸ���� ����Ѵ�. 
	public void addMember(MemberVO member) throws Exception {
		Connection conn = null;
		
		try {
			conn = DBConn.getConnection();
			
			conn.setAutoCommit(false);
			
			MemberDAO dao = MemberDAO.getInstance();
			dao.insertMember(conn, member);
			
			conn.commit();
			
		} catch(Exception e) {
			conn.rollback();
			throw e;
			
		} finally {
			if(conn != null) conn.close();			
		}
		
	}
	
	
	
	//���̵� ã��
	public String retrieveMemberId(String name, String email) throws Exception {
		MemberDAO dao = MemberDAO.getInstance();
		return dao.selectMemberId(name, email);
	}
	
	
	
	//��й�ȣ ã��
	public void updateMemberPwd(String memberId, String email, String tempPwd) throws Exception {
		Connection conn = null;
		try {
			conn = DBConn.getConnection();
			
			conn.setAutoCommit(false);
			
			MemberDAO dao = MemberDAO.getInstance();
			dao.modifyMemberPwd(conn, memberId, email, tempPwd);
			
			conn.commit();
			
		} catch (Exception e) {
			conn.rollback();
			throw e;
			
		} finally {
			if(conn != null) conn.close();
		}
		
	}
	
	
	
	//ȸ������ ����
	public void updateMember(MemberVO member) throws Exception {
		Connection conn = null;
		
		try {
			conn = DBConn.getConnection();
			
			conn.setAutoCommit(false);
			
			MemberDAO dao = MemberDAO.getInstance();			
			dao.modifyMember(conn, member);
			
			conn.commit();
			
		} catch (Exception e) {
			conn.rollback();
			throw e;
			
		} finally {
			if(conn != null) conn.close();
		}
		
	}
	
	
	
	//ȸ�� Ż��
	public void deleteMember(String memberNo) throws Exception {
		
		Connection conn = null;
		
		try {
			conn = DBConn.getConnection();
			
			conn.setAutoCommit(false);
			
			MemberDAO dao = MemberDAO.getInstance();			
			dao.removeMember(conn, memberNo);
			
			conn.commit();
			
		} catch (Exception e) {
			conn.rollback();
			throw e;
			
		} finally {
			if(conn != null) conn.close();
		}
		
		
	}
	
	
	
	//ȸ������ (�˻�)
	public ArrayList<MemberVO> retrieveMemberList(String keyfield, String keyword, int startRow, int endRow) throws Exception {
		MemberDAO dao = MemberDAO.getInstance();
		return dao.selectMemberList(keyword, keyfield, startRow, endRow);
	}
	
	
	
	//ȸ������ (��ü��ȸ)
	public ArrayList<MemberVO> retrieveMemberList(int startRow, int endRow) throws Exception {
		MemberDAO dao = MemberDAO.getInstance();
		return dao.selectMemberList(startRow, endRow);
	}
	
	
}
