package model.member;

import java.sql.Connection;
import java.util.ArrayList;

import conn.DBConn;
import domain.member.MemberVO;

public class MemberService {

	
	
	//회원 여부를 확인하다. (로그인)
	public MemberVO retrieveMember(String memberId, String memberPwd) throws Exception {
		MemberDAO dao = MemberDAO.getInstance();
		return dao.selectMember(memberId, memberPwd);
	}
	
	
	//아이디, 이메일 중복확인
	public int retrieveMemberDuplicate(String keyfield, String keyword) throws Exception {
		MemberDAO dao = MemberDAO.getInstance();
		return dao.selectMemberDuplicate(keyfield, keyword);		
	}
	
	
		
	//회원을 등록한다. 
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
	
	
	
	//아이디 찾기
	public String retrieveMemberId(String name, String email) throws Exception {
		MemberDAO dao = MemberDAO.getInstance();
		return dao.selectMemberId(name, email);
	}
	
	
	
	//비밀번호 찾기
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
	
	
	
	//회원정보 수정
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
	
	
	
	//회원 탈퇴
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
	
	
	
	//회원관리 (검색)
	public ArrayList<MemberVO> retrieveMemberList(String keyfield, String keyword, int startRow, int endRow) throws Exception {
		MemberDAO dao = MemberDAO.getInstance();
		return dao.selectMemberList(keyword, keyfield, startRow, endRow);
	}
	
	
	
	//회원관리 (전체조회)
	public ArrayList<MemberVO> retrieveMemberList(int startRow, int endRow) throws Exception {
		MemberDAO dao = MemberDAO.getInstance();
		return dao.selectMemberList(startRow, endRow);
	}
	
	
}
