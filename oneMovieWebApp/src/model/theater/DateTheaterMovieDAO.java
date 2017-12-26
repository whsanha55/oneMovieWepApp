package model.theater;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conn.DBConn;
import domain.movie.MovieVO;
import domain.theater.ScheduleTurnVO;
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
		Statement stmt = null;
		ResultSet rs = null;
		List<MovieVO>list = new ArrayList<MovieVO>();
		
		try {
			conn =DBConn.getConnection();
			stmt = conn.createStatement();
			
			StringBuilder sql = new StringBuilder();
			
			sql.append("select distinct t1.movie_no, t1.movie_title      ");
			sql.append("from movie t1, screen_schedule t2       ");
			sql.append("where t1.movie_no = t2.movie_no         ");
			sql.append("and t2.screen_date >= sysdate        ");
			sql.append("order by t1.movie_title asc           ");
			
			
			rs = stmt.executeQuery(sql.toString());

			while(rs.next()) {
				MovieVO movieVO = new MovieVO();
				movieVO.setMovieNo(rs.getInt(1));
				movieVO.setMovieTitle(rs.getString(2));
				
				list.add(movieVO);
			}
			
		} finally {
			if(rs!=null)rs.close();
			if(stmt!=null)stmt.close();
			if(conn!=null)conn.close();
		}
		
		return list;
		
	}
	//������ȸ					12
	public List<TheaterVO> selectTheaterAll()throws Exception{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<TheaterVO>	list = new ArrayList<TheaterVO>();
		
		
		try {
			conn =DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("select theater_no, theater_name            														");
			sql.append("from theater                              														 ");
			sql.append("where theater_no in(select distinct theater_no           										 ");
			sql.append("       				 from screen                                      							 ");
			sql.append("       				 where screen_no in (select screen_no             							 ");
			sql.append("                   						 from screen_schedule                  					");
			sql.append("                  						  where screen_date > sysdate))  ");
			sql.append("order by theater_name asc                                        ");
			
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			
			
			while(rs.next()) {
				TheaterVO theaterVO = new TheaterVO();
				theaterVO.setTheaterNo(rs.getInt(1));
				theaterVO.setTheaterName(rs.getString(2));
				
				list.add(theaterVO);
			}
		} finally {
			if(rs!=null)rs.close();
			if(stmt!=null)stmt.close();
			if(conn!=null)conn.close();
		}
		return list;
		
	}
	//��¥��ȸ					13
	public List<ScreenScheduleVO> selectDateAll()throws Exception{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<ScreenScheduleVO> list = new ArrayList<ScreenScheduleVO>();
		try {
			conn =DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("select distinct to_char(screen_date,'YYYY/MM/DD') s1						");
			sql.append("from screen_schedule								");
			sql.append("where screen_date > to_char(sysdate,'YYYY/MM/DD')						");
			sql.append("order by s1 asc             ");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			
			while(rs.next()) {
				ScreenScheduleVO screenScheduleVO = new ScreenScheduleVO();
				screenScheduleVO.setScreenDate(rs.getString(1));				
				list.add(screenScheduleVO);
			}
		} finally {
			if(rs!=null)rs.close();
			if(stmt!=null)stmt.close();
			if(conn!=null)conn.close();
		}
		return list;
	}
	
	//����->��ȭ ��ȸ			14
	public List<MovieVO> selectMovieByTheater(int theaterNo) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MovieVO>list = new ArrayList<MovieVO>();
		try {
			conn =DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("select  distinct t1.movie_no, t1.movie_title             ");
			sql.append("from movie t1, screen_schedule t2                 ");
			sql.append("where t1.movie_no = t2.movie_no                   ");
			sql.append("and t2.screen_date > sysdate                  ");
			sql.append("and screen_no in(select screen_no                ");
			sql.append("        from screen                        ");
			sql.append("        where theater_no =(select theater_no            ");
			sql.append("                  from theater                    ");
			sql.append("                  where theater_no = ?))              ");
			sql.append("order by t1.movie_title asc           ");
			
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

	// ��¥->��ȭ ��ȸ 15
	public List<MovieVO> selectMovieByDate(String date) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//List<ScreenScheduleVO> dates = new ArrayList<ScreenScheduleVO>();
		List<MovieVO> list = new ArrayList<MovieVO>();
		
		try {
			conn = DBConn.getConnection();
			StringBuilder sql = new StringBuilder();

			sql.append("select distinct m1.movie_no,m1.movie_title			");
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

	//��¥->���� ��ȸ			16
	public List<TheaterVO> selectTheaterByDate(String date) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TheaterVO>list = new ArrayList<TheaterVO>();
		
		try {
			conn = DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("select distinct t1.theater_no,t1.theater_name								");
			sql.append("from theater t1,screen s1, screen_schedule ss1						");
			sql.append("where t1.theater_no = s1.theater_no									");
			sql.append("and s1.screen_no = ss1.screen_no									");
			sql.append("and to_char(ss1.screen_date,'yyyy/mm/dd') = ?	"); 
			sql.append("order by t1.theater_name asc                                        ");
			
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
	//��ȭ->���� ��ȸ			17
	public List<TheaterVO> selectTheaterByMovie(int movieNo) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TheaterVO>list = new ArrayList<TheaterVO>();
		
		try {
			conn = DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("select distinct t1.theater_no, t1.theater_name								");
			sql.append("from theater t1,screen s1, screen_schedule ss1,movie m1				");
			sql.append("where t1.theater_no = s1.theater_no									");
			sql.append("and s1.screen_no = ss1.screen_no									");
			sql.append("and ss1.movie_no = m1.movie_no										");
			sql.append("and m1.movie_no = ?													");
			sql.append("order by t1.theater_name asc                                        ");
			
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
	
	//����->��¥ ��ȸ			18
	public List<ScreenScheduleVO> selectDateByTheater(int theaterNO) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ScreenScheduleVO> list = new ArrayList<ScreenScheduleVO>();
		
		try {
			conn = DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("select distinct to_char(ss1.screen_date,'YYYY/MM/DD') c1							");
			sql.append("from screen_schedule ss1,theater t1, screen s1			");
			sql.append("where t1.theater_no = s1.theater_no						");
			sql.append("and s1.screen_no = ss1.screen_no						");
			sql.append("and ss1.screen_date > to_char(sysdate,'YYYY/MM/DD')						");
			sql.append("and t1.theater_no = ?									");
			sql.append("order by c1 asc             ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, theaterNO);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ScreenScheduleVO screenScheduleVO = new ScreenScheduleVO();
				screenScheduleVO.setScreenDate(rs.getString(1));
				
				list.add(screenScheduleVO);
			}
			
		} finally {
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
		return list;
	}
	//��ȭ->��¥ ��ȸ			19
	public List<ScreenScheduleVO> selectDateByMovie(int movieNo) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ScreenScheduleVO> list = new ArrayList<ScreenScheduleVO>();
		
		try {
			conn = DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("select distinct to_char(ss1.screen_date,'YYYY/MM/DD') c1							");
			sql.append("from screen_schedule ss1,theater t1, screen s1,movie m1 ");
			sql.append("where t1.theater_no = s1.theater_no						");
			sql.append("and s1.screen_no = SS1.screen_no						");
			sql.append("and m1.movie_no = SS1.movie_no							");
			sql.append("and ss1.screen_date > to_char(sysdate,'YYYY/MM/DD')						");
			sql.append("and SS1.movie_no = ?									");
			sql.append("order by c1 asc             ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, movieNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ScreenScheduleVO screenScheduleVO = new ScreenScheduleVO();
				screenScheduleVO.setScreenDate(rs.getString(1));
				
				list.add(screenScheduleVO);
			}
		} finally {
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
		return list;
	}
	
	//����,��¥ -> ��ȭ��ȸ		20
	public List<MovieVO>selectMovie(int theaterNo, String date) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<MovieVO>list = new ArrayList<MovieVO>();
		
		try {
			conn = DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("select distinct m1.movie_no,m1.movie_title											");
			sql.append("from screen_schedule ss1,theater t1, screen s1,movie m1						");
			sql.append("where t1.theater_no = s1.theater_no											");
			sql.append("and s1.screen_no = ss1.screen_no											");
			sql.append("and m1.movie_no = ss1.movie_no												");
			sql.append("and t1.theater_no = ?														");
			sql.append("and to_char(ss1.screen_date,'yyyy/mm/dd') = ?                       		");
			
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
	//��ȭ,��¥ -> ������ȸ		21
	public List<TheaterVO> selectTheater(int movieNo,String date) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TheaterVO>list = new ArrayList<TheaterVO>();
		System.out.println(movieNo);
		try {
			conn = DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("select distinct t1.theater_no,t1.theater_name										");
			sql.append("from screen_schedule ss1,theater t1, screen s1,movie m1						");
			sql.append("where t1.theater_no = s1.theater_no											");
			sql.append("and s1.screen_no = ss1.screen_no											");
			sql.append("and m1.movie_no = ss1.movie_no												");
			sql.append("and m1.movie_no = ?															");
			sql.append("and to_char(ss1.screen_date,'yyyy/mm/dd') = ?                    			");
			sql.append("order by t1.theater_name asc                                        ");
			
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
	//��ȭ,���� -> ��¥��ȸ		22
	public List<ScreenScheduleVO> selectDate(int movieNo, int theaterNo) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ScreenScheduleVO>list = new ArrayList<ScreenScheduleVO>();

		try {
			conn = DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("select distinct to_char(ss1.screen_date,'YYYY/MM/DD') c1										");
			sql.append("from screen_schedule ss1,theater t1, screen s1,movie m1						");
			sql.append("where t1.theater_no = s1.theater_no											");
			sql.append("and s1.screen_no = ss1.screen_no											");
			sql.append("and m1.movie_no = ss1.movie_no												");
			sql.append("and m1.movie_no = ?															");
			sql.append("and t1.theater_no = ?														");
			sql.append("and ss1.screen_date > to_char(sysdate,'YYYY/MM/DD')						");
			sql.append("order by c1 asc             ");
			
			
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, movieNo);
			pstmt.setInt(2, theaterNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ScreenScheduleVO screenScheduleVO = new ScreenScheduleVO();
				screenScheduleVO.setScreenDate(rs.getString(1));
				
				list.add(screenScheduleVO);
			}
		} finally {
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
		
		return list;
	}
	
	//�ӽ÷� �޼ҵ�  ������ �̵� �ʿ�!!
	public List<ScreenScheduleVO> selectTurn(int movieNo, int theaterNo, String date) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ScreenScheduleVO> list = new ArrayList<ScreenScheduleVO>();

		try {
			conn = DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("select t1.turn_no, to_char(t1.start_time,'HH24:MI') ST, to_char(t1.end_time,'HH24:MI'), t3.screen_name   ");
			sql.append("from schedule_turn t1, screen_schedule t2, screen t3        ");
			sql.append("where t1.schedule_no = t2.schedule_no                       ");
			sql.append("and t2.screen_no = t3.screen_no                             ");
			sql.append("and movie_no = ?                                            ");
			sql.append("and t3.theater_no = ?                                       ");
			sql.append("and screen_date = ?                                         ");
			sql.append("order by screen_name asc, ST asc                           ");
			
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, movieNo);
			pstmt.setInt(2, theaterNo);
			pstmt.setString(3, date);
			
			rs = pstmt.executeQuery();
			String screenName = "";
			while(rs.next()) {
				if(!screenName.equals(rs.getString(4))) {
					screenName = rs.getString(4);
					ScreenScheduleVO scheduleVO = new ScreenScheduleVO();
					scheduleVO.setScreenName(rs.getString(4));
					list.add(scheduleVO);
				} else {
					ScheduleTurnVO turnVO = new ScheduleTurnVO();
					turnVO.setTurnNo(rs.getInt(1));
					turnVO.setStartTime(rs.getString(2));
					turnVO.setEndTime(rs.getString(3));
					
					list.get(list.size()-1).addScheduleTurnVO(turnVO);
				}
				
			}
		} finally {
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
		
		return list;
		
	}
}
