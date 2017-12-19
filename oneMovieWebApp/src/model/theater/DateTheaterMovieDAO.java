package model.theater;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conn.DBConn;
import domain.movie.MovieVO;
import domain.theater.ScreenScheduleVO;
import domain.theater.TheaterVO;

public class DateTheaterMovieDAO {
	private DateTheaterMovieDAO() {

	}

	private static DateTheaterMovieDAO instance = new DateTheaterMovieDAO();

	public static DateTheaterMovieDAO getInstance() {
		return instance;
	}
	//��ȭ��ȸ					11
	public List<MovieVO> selectMovieAll()throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MovieVO>list = new ArrayList<MovieVO>();
		
		try {
			conn =DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			pstmt = conn.prepareStatement(sql.toString());
			
			sql.append("select movie_title,movie_no					");
			sql.append("from movie									");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();

			while(rs.next()) {
				MovieVO movieVO = new MovieVO();
				movieVO.setMovieNo(rs.getInt(1));
				movieVO.setMovieTitle(rs.getString(2));
				
				list.add(movieVO);
			}
			
		} finally {
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
		
		return list;
		
	}
	//������ȸ					12
	public List<TheaterVO> selectTheaterAll()throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TheaterVO>	list = new ArrayList<TheaterVO>();
		
		
		try {
			conn =DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			pstmt = conn.prepareStatement(sql.toString());

			sql.append("select theater_name,theater_no					");
			sql.append("from theater									");

			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				TheaterVO theaterVO = new TheaterVO();
				theaterVO.setTheaterName(rs.getString(1));
				theaterVO.setTheaterNo(rs.getInt(2));
				
				list.add(theaterVO);
				
			}

		} finally {
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
		return list;
		
	}
	//��¥��ȸ					13
	public List<ScreenScheduleVO> selectDateAll()throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ScreenScheduleVO> list = new ArrayList<ScreenScheduleVO>();
		try {
			conn =DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			pstmt = conn.prepareStatement(sql.toString());
			
			sql.append("select screen_date,schedule_no						");
			sql.append("from screen_schedule								");
			sql.append("where screen_date > sysdate							");
			
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ScreenScheduleVO screenScheduleVO = new ScreenScheduleVO();
				screenScheduleVO.setScreenDate(rs.getString(1));
				screenScheduleVO.setScheduleNo(rs.getInt(2));
				
				list.add(screenScheduleVO);
			}
		} finally {
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
		return list;
	}
	
	//����->��ȭ ��ȸ			14
	public List<MovieVO> selectMovieByTheater(int theaterNo) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<MovieVO>list =null;
		
		return list;
	}

	// ��¥->��ȭ ��ȸ 15
	public List<MovieVO> selectMovieByDate(String date) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<MovieVO> list = null;

		return list;
	}

	//��¥->���� ��ȸ			16
	public List<TheaterVO> selectTheaterByDate(String date) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TheaterVO>list = null;
		
		return list;
	}
	//��ȭ->���� ��ȸ			17
	public List<TheaterVO> selectTheaterByMovie(int movieNo) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TheaterVO>list = null;

		return list;
	}
	
	//����->��¥ ��ȸ			18
	public String[] selectDateByTheater(int theaterNO) throws Exception{
		
		
		return null;
	}
	//��ȭ->��¥ ��ȸ			19
	public String[] selectDateByMovie(int movieNo) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		return null;
	}
	
	//����,��¥ -> ��ȭ��ȸ		20
	public List<MovieVO>selectMovie(int theaterNo, String date) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<MovieVO>list = null;
		
		return list;
		
	}
	//��ȭ,��¥ -> ������ȸ		21
	public List<TheaterVO> selectTheater(int movieNo,String date) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<TheaterVO>list = null;
		
		return list;
	}
	//��ȭ,���� -> ��¥��ȸ		22
	public List<TheaterVO> selectDate(int movieNo, int theaterNo) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<TheaterVO>list = null;

		return list;
	}
}
