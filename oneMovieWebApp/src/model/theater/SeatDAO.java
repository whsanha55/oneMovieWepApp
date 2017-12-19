package model.theater;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conn.DBConn;
import domain.theater.SeatVO;

public class SeatDAO {
	private SeatDAO() {

	}

	private static SeatDAO instance = new SeatDAO();

	public static SeatDAO getInstance() {
		return instance;
	}
	
	//지점에 해당하고 상영관 번호에 해당하는 상영관 좌석 정보 조회		5
	public List<SeatVO> selectSeatList(int screenNo)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SeatVO> list = new ArrayList<SeatVO>();
		 
		try { 
			conn = DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			pstmt = conn.prepareStatement(sql.toString());
			
			sql.append("select s1.seat_no,s1.seat_name								");
			sql.append("from seat s1, screen s2  									");
			sql.append("where s1.screen_no = s2.screen_no							");
			sql.append("and s1.screen_no = ? 										");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, screenNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SeatVO seatVO = new SeatVO();
				seatVO.setSeatNo(rs.getInt(1));
				seatVO.setSeatName(rs.getString(2));
				
				list.add(seatVO);
			}
			
		} finally {
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
		return list;
	}
}
