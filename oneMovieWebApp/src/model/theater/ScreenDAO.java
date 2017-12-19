package model.theater;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conn.DBConn;
import domain.theater.ScreenVO;
import domain.theater.TheaterVO;

public class ScreenDAO {
	private ScreenDAO() {

	}

	private static ScreenDAO instance = new ScreenDAO();

	public static ScreenDAO getInstance() {
		return instance;
	}
	
	//지점에 해당하는 상영관 정보 검색				4
	public List<TheaterVO> SelectScreenList (int[] theaterNo)throws Exception{

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TheaterVO>list = new ArrayList<TheaterVO>();

		try { 
			conn =DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			pstmt = conn.prepareStatement(sql.toString());
			sql.append("select t1.theater_name,s1.screen_no,s1.screen_name			");
			sql.append("from theater t1, screen s1									");
			sql.append("where t1.theater_no = s1.theater_no 						");
			sql.append("and s1.theater_no in (										");
			for(int i=0;i<theaterNo.length;i++) {
				
				sql.append(" ?														");
				if(i < theaterNo.length - 1) {
					sql.append(" ,													");
				}else {
					sql.append(")													");
				}
			}
			
			/* 위와 동일
			 * for(int i=0;i<theaterNo.length;i++) {
			 * 	sql.append("?,");
			 * }
			 * sql.deleteCharAt(sql.length() -1).toString();
			 * */
			
			pstmt = conn.prepareStatement(sql.toString());
			int index = 1;
			for(int o : theaterNo) {
				pstmt.setObject(index++, o);
			}
			rs = pstmt.executeQuery();
			int theaterNum = 0;
			while(rs.next()) {
				TheaterVO theaterVO = new TheaterVO();
				if(theaterNum!=0) {
					theaterVO.setTheaterName(rs.getString(1));
				}
				ScreenVO screenVO = new ScreenVO();
				screenVO.setScreenNo(rs.getInt(2));
				screenVO.setScreenName(rs.getString(3));
				theaterVO.addScreenVO(screenVO);
				list.add(theaterVO);
			}
			
		}finally {
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}

		return list;
	}
}
