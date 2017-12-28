package model.theater;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conn.DBConn;
import domain.theater.ScreenVO;

public class ScreenDAO {
	private ScreenDAO() {

	}

	private static ScreenDAO instance = new ScreenDAO();

	public static ScreenDAO getInstance() {
		return instance;
	}
	
	//지점에 해당하는 상영관 정보 검색				4
	public List<ScreenVO> SelectScreenList (int theaterNo)throws Exception{

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ScreenVO>list = new ArrayList<ScreenVO>();
 
		try { 
			conn =DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("select screen_no, screen_name		                     	");
			sql.append("from screen 								                ");
			sql.append("where theater_no = ? 										");
			sql.append("order by screen_no asc                                      ");
		
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, theaterNo);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				
				ScreenVO screenVO = new ScreenVO();
				screenVO.setScreenNo(rs.getInt(1));
				screenVO.setScreenName(rs.getString(2));
				
				list.add(screenVO);
			}
			
		}finally {
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}

		return list;
	}
}
