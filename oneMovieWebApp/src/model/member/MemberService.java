package model.member;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

import conn.DBConn;
import domain.member.MemberVO;

public class MemberService {

	private static MemberService instance = new MemberService();
	
	private MemberService() {
		
	}
	
	public static MemberService getInstance() {
		return instance;
	}
	
	
	
	//회원 여부를 확인하다. (로그인)
	public MemberVO retrieveMember(String memberId, String memberPwd) throws Exception {
		MemberDAO dao = MemberDAO.getInstance();
		return dao.selectMember(memberId, memberPwd);
	}
	
	
	//아이디 중복확인
	public int retrieveIdDuplicate(String keyword) throws Exception {
		MemberDAO dao = MemberDAO.getInstance();
		int count = dao.selectIdDuplicate(keyword);
		return count;		
	}
	
	
	//이메일 중복확인
	public int retrieveEmailDuplicate(String memberNo, String keyword) throws Exception {
		MemberDAO dao = MemberDAO.getInstance();
		int count = dao.selectEmailDuplicate(memberNo, keyword);
		return count;		
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
	
	
	//회원 여부를 확인하다 (비밀번호 찾기용)
	public int retrieveMemberCount(String memberId, String email) throws Exception {
		MemberDAO dao = MemberDAO.getInstance();
		return dao.selectMemberCount(memberId, email);		
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
	
	
	
	
	
	//회원관리 (조회)
	public ArrayList<MemberVO> retrieveMemberList(Map<String, Object> map) throws Exception {
		MemberDAO dao = MemberDAO.getInstance();
		return dao.selectMemberList(map);
	}
	
	
	
	//회원 수 조회
	public int retrieveSearchCount(String keyfield, String keyword) throws Exception {
		MemberDAO dao = MemberDAO.getInstance();
		return dao.selectSearchCount(keyfield, keyword);
	}
	
	
	
}
