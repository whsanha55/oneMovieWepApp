package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import conn.DBConn;
import domain.member.MemberVO;

public class MemberDAO {

	private static MemberDAO instance = new MemberDAO();
	
	
	private MemberDAO() {
		
	}
	
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	
	//아이디, 비밀번호에 해당하는 회원정보를 반환하다.
	public MemberVO selectMember(String memberId, String memberPwd) throws Exception {
		MemberVO member = new MemberVO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		try {
			conn = DBConn.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("select member_no, member_id, member_pwd, name, gender, phone, email,	 	 ");
			sql.append("address1, address2, zipcode, iswithdraw, to_char(withdraw_day, 'YYYY/MM/DD') ");
			sql.append("from member												   				 	 ");
			sql.append("where member_id = ? and member_pwd =? and iswithdraw = 'N' 				 	 ");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPwd);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				member.setMemberNo(rs.getString(1));
				member.setMemberId(rs.getString(2));
				member.setMemberPwd(rs.getString(3));
				member.setName(rs.getString(4));
				member.setGender(rs.getString(5));
				member.setPhone(rs.getString(6));
				member.setEmail(rs.getString(7));
				member.setAddress1(rs.getString(8));
				member.setAddress2(rs.getString(9));
				member.setZipcode(rs.getString(10));
				member.setIsWithdraw(rs.getString(11));
				member.setWithdrawDay(rs.getString(12));
			}
			
			
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return member;
	}

	
	

	
	
	
	
	//아이디 중복 확인	(0 반환 시 사용 가능, 1 반환 시 중복으로 사용 불가)
	public int selectIdDuplicate(String keyword) throws Exception {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConn.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("select count(member_no)					 ");
			sql.append("from member								 ");
			sql.append("where member_id = ? and iswithdraw = 'N' ");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, keyword);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		} finally  {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
				
		return count;
	}	
	
		

	
	//이메일 중복 확인	(0 반환 시 사용 가능, 1 반환 시 중복으로 사용 불가)
	public int selectEmailDuplicate(String memberNo, String keyword) throws Exception {
	
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		
		try {
			conn = DBConn.getConnection();
			
			StringBuffer sql = new StringBuffer();
			
			sql.append("select count(member_no)					");
			
			if(memberNo.equals("0")) {
				sql.append("from member							");
			} else {
				sql.append("from (select *						");
				sql.append("from member							");
				sql.append("where member_no not like ? )	 	");
			}
			
			sql.append("where email = ? and iswithdraw = 'N'	");
						
			pstmt = conn.prepareStatement(sql.toString());
			
			if(memberNo.equals("0")) {
				pstmt.setString(1, keyword);
			} else {
				pstmt.setString(1, memberNo);
				pstmt.setString(2, keyword);
			}
						
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		} finally  {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
				
		return count;
	}	
	
	
	
	
	
	
	//회원을 등록하다.
	public void insertMember(Connection conn, MemberVO member) throws Exception {
		
		PreparedStatement pstmt = null;
		
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append("insert into member(member_no, member_id, member_pwd, name, gender,		  ");
			sql.append("phone, email, address1, address2, zipcode) 						   		  ");
			sql.append("values(to_char(sysdate, 'YYMMDD') || lpad(member_no_seq.nextval, 5, 0),   ");
			sql.append("?, ?, ?, ?, ?, ?, ?, ?, ?)											      ");				
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPwd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getGender());
			pstmt.setString(5, member.getPhone());
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getAddress1());
			pstmt.setString(8, member.getAddress2());
			pstmt.setString(9, member.getZipcode());
			
			pstmt.executeQuery();			
			
		} finally {
			if(pstmt != null) pstmt.close();
		}
		
	}
	
	
	
	//아이디 찾기: 이름과 이메일 정보에 해당하는 아이디 반환
	public String selectMemberId(String name, String email) throws Exception {
		String memberId = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		try {
			conn = DBConn.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select member_id										");
			sql.append("from member												");
			sql.append("where name = ? and email = ? and iswithdraw = 'N'		");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, name);
			pstmt.setString(2, email);			
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				memberId = rs.getString(1);
			}
			
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();			
		}
		
		return memberId;
	}
	
	
	

	//아이디, 이메일에 해당하는 회원 여부 확인 (비밀번호 찾기용: 0이면 정보 없음, 1이면 회원 있음)
	public int selectMemberCount(String memberId, String email) throws Exception {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConn.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("select count(member_no)									");
			sql.append("from member												");
			sql.append("where member_id = ? and email = ? and iswithdraw = 'N'	");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, memberId);
			pstmt.setString(2, email);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		} finally  {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
				
		return count;
	}	
	
	
		
	
	//비밀번호 변경: 아이디에 해당하는 회원의 비밀번호를 임시 비밀번호로 변경한다.
	public void modifyMemberPwd(Connection conn, String memberId, String email, String tempPwd) throws Exception {

		PreparedStatement pstmt = null;
		
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("update member						     			   ");
			sql.append("set member_pwd = ?								       ");
			sql.append("where member_id = ?	and email = ? and iswithdraw = 'N' ");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, tempPwd);
			pstmt.setString(2, memberId);
			pstmt.setString(3, email);
			

			pstmt.executeQuery();
			
		} finally {
			if(pstmt != null) pstmt.close();
		}
		
	}
	
	
	
	//회원정보 수정
	public void modifyMember(Connection conn, MemberVO member) throws Exception {

		PreparedStatement pstmt = null;	
		
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append("update member										 	");
			sql.append("set member_pwd = ?, name = ?, gender = ?, phone = ?,	");
			sql.append("email = ?, address1 = ?, address2 = ?, zipcode =?		");
			sql.append("where member_no = ?										");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, member.getMemberPwd());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getGender());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getAddress1());
			pstmt.setString(7, member.getAddress2());
			pstmt.setString(8, member.getZipcode());
			pstmt.setString(9, member.getMemberNo());
			
			pstmt.executeQuery();
			
			
		} finally {
			if(pstmt != null) pstmt.close();
		}
	}
	
	
	
	//회원 탈퇴: 회원번호에 해당하는 회원의 탈퇴여부(Y), 탈퇴일자(sysdate) 기록
	public void removeMember(Connection conn, String memberNo) throws Exception {

		PreparedStatement pstmt = null;	
		
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("update member								 ");
			sql.append("set iswithdraw = 'Y', withdraw_day = sysdate ");
			sql.append("where member_no = ?							 ");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, memberNo);
			
			pstmt.executeQuery();
			
		} finally {
			if(pstmt != null) pstmt.close();
		}
		
	}
	
	
	
	
	
	
	//회원관리: 회원 목록 출력
	public ArrayList<MemberVO> selectMemberList(Map<String, Object> map) throws Exception {
		ArrayList<MemberVO> members = new ArrayList<MemberVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String keyfield = (String)map.get("keyfield");
		String keyword = (String)map.get("keyword");
		int startRow = (Integer)map.get("startRow");
		int endRow = (Integer)map.get("endRow");
		String sort = (String)map.get("sort");
		int sortType = (Integer)map.get("sortType");
		
			
		try {
			conn = DBConn.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select member_no, member_id, member_pwd, name, gender, phone,   ");
			sql.append("email, address1, address2, zipcode, iswithdraw, 			    ");
			sql.append("to_char(withdraw_day, 'YYYY/MM/DD')   						    ");
			sql.append("from (select rownum as rn, member1.*				   	 		");
			sql.append("from (select *										     		");
			sql.append("from member											     		");
			
			if(keyfield.equals("name")) {
				sql.append("where name like '%' || ? || '%'						   		");
			} else if(keyfield.equals("memberId")) {
				sql.append("where member_id like '%' || ? || '%'			   		    ");
			} 	
			
			if(sort.equals("memberNo")) {
				sql.append("order by member_no 											");	
			} else if(sort.equals("memberId")) {
				sql.append("order by member_id 											");	
			} else if(sort.equals("name")) {
				sql.append("order by name 												");
			} else if(sort.equals("email")) {
				sql.append("order by email 												");
			}
			
			if(sortType == 1) {
				sql.append("asc) member1)     											");	
			} else if(sortType == 2) {
				sql.append("desc) member1)									    		");
			}
			
			
			System.out.println(sort + " " + sortType);
			
			sql.append("where rn >= ? and rn <= ?				  			     		");

			
			if(sort.equals("memberNo")) {
				sql.append("order by member_no					  			     		");
			} else if(sort.equals("memberId")) {
				sql.append("order by member_id					  			     		");
			} else if(sort.equals("name")) {
				sql.append("order by name						  			     		");
			} else if(sort.equals("email")) {
				sql.append("order by email						  			     		");
			}
			
			if(sortType == 1) {
				sql.append("asc     													");
			} else if(sortType == 2) {
				sql.append("desc    													");
			}
	
			
			System.out.println(sort + " " + sortType);
			
			pstmt = conn.prepareStatement(sql.toString());
			
			if(keyfield.equals("all")) {
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
			} else {
				pstmt.setString(1, keyword);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);	
			}	
				
			
			rs = pstmt.executeQuery();
						
			while(rs.next()) {
				MemberVO member = new MemberVO();
				member.setMemberNo(rs.getString(1));
				member.setMemberId(rs.getString(2));
				member.setMemberPwd(rs.getString(3));
				member.setName(rs.getString(4));
				member.setGender(rs.getString(5));
				member.setPhone(rs.getString(6));
				member.setEmail(rs.getString(7));
				member.setAddress1(rs.getString(8));
				member.setAddress2(rs.getString(9));
				member.setZipcode(rs.getString(10));
				member.setIsWithdraw(rs.getString(11));
				member.setWithdrawDay(rs.getString(12));
				members.add(member);
			}
			
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		
		return members;		
		
	}   
	
	
	
	
	//회원 수를 구하다.
	public int selectSearchCount(String keyfield, String keyword) throws Exception {
		int searchCount = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
	
		
		try {
			conn = DBConn.getConnection();
						
			StringBuffer sql = new StringBuffer();
			sql.append("select count(*) 		");
			sql.append("from member 		    ");
			
			if(keyfield.equals("name")) {
				sql.append("where name like '%' || ? || '%'   ");
			} else if(keyfield.equals("memberId")) {
				sql.append("where member_id '%' || ? || '%'   ");				
			}
			
			pstmt = conn.prepareStatement(sql.toString());
						
			if(!keyfield.equals("all")) {
				pstmt.setString(1, keyword);	
			}
									
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				searchCount = rs.getInt(1);
			}
			
			
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		
		return searchCount;
		
	}
	
	
	
}
