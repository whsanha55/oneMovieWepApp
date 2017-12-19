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
	//영화조회					11
	public List<MovieVO> selectMovieAll()throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MovieVO>list = new ArrayList<MovieVO>();
		
		try {
			conn =DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("select movie_title,movie_no								");
			sql.append("from movie m1, screen_schedule ss1,screen s1			");
			sql.append("where movie m1, screen_schedule ss1						");
			sql.append("and screen_date > sysdate								");
			
			
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
	//지점조회					12
	public List<TheaterVO> selectTheaterAll()throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TheaterVO>	list = new ArrayList<TheaterVO>();
		
		
		try {
			conn =DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("select theater_name,theater_no					");
			sql.append("from theater									");
			sql.append("where screen_date > sysdate						");
			
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
	//날짜조회					13
	public List<ScreenScheduleVO> selectDateAll()throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ScreenScheduleVO> list = new ArrayList<ScreenScheduleVO>();
		try {
			conn =DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			
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
	
	//지점->영화 조회			14
	public List<MovieVO> selectMovieByTheater(int theaterNo) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MovieVO>list = new ArrayList<MovieVO>();
		try {
			conn =DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("select m1.movie_no,m1.movie_title							");
			sql.append("from movie m1, theater t1,screen s1,screen_schedule	ss1		");
			sql.append("where t1.theater_no = s1.theater_no							");
			sql.append("and s1.screen_no = ss1.screen_no							");
			sql.append("and ss1.movie_no = m1.movie_no								");
			sql.append("and t1.theater_no = ?										");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, theaterNo);
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

	// 날짜->영화 조회 15
	public List<MovieVO> selectMovieByDate(String date) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//List<ScreenScheduleVO> dates = new ArrayList<ScreenScheduleVO>();
		List<MovieVO> list = new ArrayList<MovieVO>();
		
		try {
			conn = DBConn.getConnection();
			StringBuilder sql = new StringBuilder();

			sql.append("select m1.movie_no,m1.movie_title			");
			sql.append("from movie m1, theater t1,screen s1,screen_schedule	ss1		");
			sql.append("where t1.theater_no = s1.theater_no							");
			sql.append("and s1.screen_no = ss1.screen_no							");
			sql.append("and ss1.movie_no = m1.movie_no								");
			sql.append("and to_char(ss1.screen_date,'yyyy/mm/dd') = ?				");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, date);
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

	//날짜->지점 조회			16
	public List<TheaterVO> selectTheaterByDate(String date) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TheaterVO>list = new ArrayList<TheaterVO>();
		
		try {
			conn = DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("select t1.theater_no,t1.theater_name								");
			sql.append("from theater t1,screen s1, screen_schedule ss1						");
			sql.append("where t1.theater_no = s1.theater_no									");
			sql.append("and s1.screen_no = ss1.screen_no									");
			sql.append("and to_char(ss1.screen_date,'yyyy/mm/dd') = to_char(?,'yyyy/mm/dd')	"); 
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, date);
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
	//영화->지점 조회			17
	public List<TheaterVO> selectTheaterByMovie(int movieNo) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TheaterVO>list = new ArrayList<TheaterVO>();
		
		try {
			conn = DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("select t1.theater_no, t1.theater_name								");
			sql.append("from theater t1,screen s1, screen_schedule ss1,movie m1				");
			sql.append("where t1.theater_no = s1.theater_no									");
			sql.append("and s1.screen_no = ss1.screen_no									");
			sql.append("and ss1.movie_no = m1.movie_no										");
			sql.append("and m1.movie_no = ?													"); 
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, movieNo);
			
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
	
	//지점->날짜 조회			18
	public List<ScreenScheduleVO> selectDateByTheater(int theaterNO) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ScreenScheduleVO> list = new ArrayList<ScreenScheduleVO>();
		
		try {
			conn = DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("select screen_date, schedule_no							");
			sql.append("from screen_schedule ss1,theater t1, screen s1			");
			sql.append("where t1.theater_no = s1.theater_no						");
			sql.append("and s1.screen_no = SS1.screen_no						");
			sql.append("and t1.theater_no = ?									");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, theaterNO);
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
	//영화->날짜 조회			19
	public List<ScreenScheduleVO> selectDateByMovie(int movieNo) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ScreenScheduleVO> list = new ArrayList<ScreenScheduleVO>();
		
		try {
			conn = DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("select screen_date, schedule_no							");
			sql.append("from screen_schedule ss1,theater t1, screen s1,movie m1 ");
			sql.append("where t1.theater_no = s1.theater_no						");
			sql.append("and s1.screen_no = SS1.screen_no						");
			sql.append("and m1.movie_no = SS1.movie_no							");
			sql.append("and SS1.movie_no = ?									");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, movieNo);
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
	
	//지점,날짜 -> 영화조회		20
	public List<MovieVO>selectMovie(int theaterNo, String date) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<MovieVO>list = new ArrayList<MovieVO>();
		
		try {
			conn = DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("select m1.movie_no,m1.movie_title											");
			sql.append("from screen_schedule ss1,theater t1, screen s1,movie m1						");
			sql.append("where t1.theater_no = s1.theater_no											");
			sql.append("and s1.screen_no = ss1.screen_no											");
			sql.append("and m1.movie_no = ss1.movie_no												");
			sql.append("and t1.theater_no = ?														");
			sql.append("and to_char(ss1.screen_date,'yyyy/mm/dd') = to_char(?,'yyyy/mm/dd')			");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, theaterNo);
			pstmt.setString(2, date);
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
	//영화,날짜 -> 지점조회		21
	public List<TheaterVO> selectTheater(int movieNo,String date) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TheaterVO>list = new ArrayList<TheaterVO>();
		
		try {
			conn = DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("select t1.theater_no,t1.theater_name										");
			sql.append("from screen_schedule ss1,theater t1, screen s1,movie m1						");
			sql.append("where t1.theater_no = s1.theater_no											");
			sql.append("and s1.screen_no = ss1.screen_no											");
			sql.append("and m1.movie_no = ss1.movie_no												");
			sql.append("and m1.movie_no = ?															");
			sql.append("and to_char(ss1.screen_date,'yyyy/mm/dd') = to_char(?,'yyyy/mm/dd')			");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, movieNo);
			pstmt.setString(2, date);
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
	//영화,지점 -> 날짜조회		22
	public List<ScreenScheduleVO> selectDate(int movieNo, int theaterNo) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ScreenScheduleVO>list = new ArrayList<ScreenScheduleVO>();

		try {
			conn = DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("select ss1.screen_no,ss1.screen_date										");
			sql.append("from screen_schedule ss1,theater t1, screen s1,movie m1						");
			sql.append("where t1.theater_no = s1.theater_no											");
			sql.append("and s1.screen_no = ss1.screen_no											");
			sql.append("and m1.movie_no = ss1.movie_no												");
			sql.append("and m1.movie_no = ?															");
			sql.append("and t1.theater_no = ?														");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, movieNo);
			pstmt.setInt(2, theaterNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ScreenScheduleVO screenScheduleVO = new ScreenScheduleVO();
				screenScheduleVO.setScreenNo(rs.getInt(1));
				screenScheduleVO.setScreenDate(rs.getString(2));
				
				list.add(screenScheduleVO);
			}
		} finally {
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
		
		return list;
	}
}
