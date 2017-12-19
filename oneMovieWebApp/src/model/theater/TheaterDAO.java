package model.theater;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conn.DBConn;
import domain.theater.TheaterVO;

public class TheaterDAO {
	private TheaterDAO() {

	}

	private static TheaterDAO instance = new TheaterDAO();

	public static TheaterDAO getInstance() {
		return instance;
	}
	
	//지점 검색				3
	public List<TheaterVO> SelectTheaterList() throws Exception{

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TheaterVO> list = new ArrayList<TheaterVO>();
		 
		try {
			conn = DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			pstmt = conn.prepareStatement(sql.toString());
			sql.append("select theater_no,theater_name					");
			sql.append("from theater 									");
			sql.append("order by theater_name asc						");
			
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				TheaterVO theaterVO = new TheaterVO();
				theaterVO.setTheaterNo(rs.getInt(1));
				theaterVO.setTheaterName(rs.getString(2));
				list.add(theaterVO);
			}
			
			
		} finally {
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}

		
		return list;
	}
}
