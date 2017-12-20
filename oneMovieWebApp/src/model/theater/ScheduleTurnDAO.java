package model.theater;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conn.DBConn;
import domain.theater.ScheduleTurnVO;
import domain.theater.ScreenScheduleVO;

public class ScheduleTurnDAO {
	private ScheduleTurnDAO() {
		
	}
	
	private static ScheduleTurnDAO instance = new ScheduleTurnDAO();
	
	public static ScheduleTurnDAO getInstance() {
		return instance;
	}
	
	//회차 등록   							8
	//service에서 commit 해야 함
	public void insertTurn(List<ScheduleTurnVO> list,Connection conn,int scheduleNo) throws Exception {
		PreparedStatement pstmt = null;
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("insert into schedule_turn(turn_no,start_time,end_time,schedule_no)			");	
			sql.append("values(TURN_NO_SEQ.NEXTVAL,to_char(?,'hh24:mi'),to_char(?,'hh24:mi'),?)		");	
			
			pstmt = conn.prepareStatement(sql.toString());
			for(ScheduleTurnVO turn : list) {
				pstmt.setString(1, turn.getStartTime());
				pstmt.setString(2, turn.getEndTime());
				pstmt.setInt(3, scheduleNo);
				pstmt.addBatch();
			}
			pstmt.executeBatch();	
			
		} finally {
			if (pstmt != null)pstmt.close();
		}
		
	}
	//회차 일정 조회						9
	public List<ScheduleTurnVO> selectScheduleTurnList(int scheduleNo)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ScheduleTurnVO> list = new ArrayList<ScheduleTurnVO>();
		try {
			conn =DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			pstmt = conn.prepareStatement(sql.toString());
			
			sql.append("select t1.theater_name,ss1.schedule_no,ss1.screen_date,s1.screen_name,m1.movie_title	");
			sql.append(",to_char(t1.start_time,'hh24:mi'),to_char(t1.end_time,'hh24:mi')						");		
			sql.append("from theater t1,screen s1,screen_schedule ss1, movie m1,schedule_turn t1				");	
			sql.append("where t1.theater_no = s1.theater_no										 				");
			sql.append("and m1.movie_no = ss1.movie_no										 					");
			sql.append("and s1.screen_no = ss1.screen_no														");
			sql.append("and ss1.schedule_no = t1.schedule_no										 			");
			sql.append("and t1.schedule_no = ?																	");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, scheduleNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ScheduleTurnVO scheduleTurnVO = new ScheduleTurnVO();
				scheduleTurnVO.setTheaterName(rs.getString(1));
				scheduleTurnVO.setScreenName(rs.getString(4));
				scheduleTurnVO.setMovieName(rs.getString(5));
				scheduleTurnVO.setStartTime(rs.getString(6));
				scheduleTurnVO.setEndTime(rs.getString(7));
				
				ScreenScheduleVO screenScheduleVO = new ScreenScheduleVO();
				screenScheduleVO.setScheduleNo(rs.getInt(2));
				screenScheduleVO.setScreenDate(rs.getString(3));
				
				scheduleTurnVO.addScreenScheduleVO(screenScheduleVO);
				
				list.add(scheduleTurnVO); 
				
			} 
		} finally {
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
		return list;
	}
	//회차 일정 삭제						10
	//서비스에서 commit해야함
	public void removeScheduleTurn(int scheduleNo,Connection conn)throws Exception {
		PreparedStatement pstmt = null;
		
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("delete from scheudle_turn			");
			sql.append("where schedule_no = ?				");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.executeUpdate();
			
		} finally {
			if(pstmt!=null)pstmt.close();
		}

	}

	
	//영화,날짜,상영관 선택시 -> 회차 조회	23
	public List<ScreenScheduleVO> selectScheduleTurnByScreen(int screenNo,String screenDate,int movieNo)throws Exception{
		List<ScreenScheduleVO> list = new ArrayList<ScreenScheduleVO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			pstmt = conn.prepareStatement(sql.toString());
			
			sql.append("select ss1.schedule_no,s1.screen_name,m1.movie_title,st1.turn_no			");
			sql.append(",to_char(st1.start_time,'hh24:mi'),to_char(st1.end_time,'hh24:mi')			");
			sql.append("from screen_schedule ss1,theater t1, screen s1,movie m1,schedule_turn st1	");
			sql.append("where t1.theater_no = s1.theater_no											");
			sql.append("and s1.screen_no = ss1.screen_no											");
			sql.append("and m1.movie_no = ss1.movie_no												");
			sql.append("and ss1.schedule_no = st1.schedule_no										");
			sql.append("and s1.screen_no = ?														");
			sql.append("and m1.movie_no = ?															");
			sql.append("and to_char(ss1.screen_date,'yyyy/mm/dd') = ?								");
			
			pstmt.setInt(1, screenNo);
			pstmt.setInt(2, movieNo);
			pstmt.setString(3, screenDate);
			
			rs = pstmt.executeQuery();
			int turnNo = 0;
			while(rs.next()) {
				ScreenScheduleVO screenScheduleVO = new ScreenScheduleVO();
				if(turnNo!=rs.getInt(4)) {
					screenScheduleVO.setScheduleNo(rs.getInt(1));
					screenScheduleVO.setScreenName(rs.getString(2));
					screenScheduleVO.setMovieTitle(rs.getString(3));
				}
				
				ScheduleTurnVO scheduleTurnVO = new ScheduleTurnVO();
				scheduleTurnVO.setTurnNo(rs.getInt(4));
				scheduleTurnVO.setStartTime(rs.getString(5));
				scheduleTurnVO.setEndTime(rs.getString(6));
				
				screenScheduleVO.addScheduleTurnVO(scheduleTurnVO);
				
				list.add(screenScheduleVO);
				
			}
		} finally {
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
		
		return list;
	}
	
	//영화,날짜,지점 선택시 -> 회차 조회	24
	public List<ScreenScheduleVO> selectScheduleTurnByTheater(int theaterNo,String screenDate,int movieNo)throws Exception{
		List<ScreenScheduleVO> list = new ArrayList<ScreenScheduleVO>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			pstmt = conn.prepareStatement(sql.toString());

			sql.append("select ss1.schedule_no,s1.screen_name,m1.movie_title,st1.turn_no			");
			sql.append(",to_char(st1.start_time,'hh24:mi'),to_char(st1.end_time,'hh24:mi')			");
			sql.append("from screen_schedule ss1,theater t1, screen s1,movie m1,schedule_turn st1	");
			sql.append("where t1.theater_no = s1.theater_no											");
			sql.append("and s1.screen_no = ss1.screen_no											");
			sql.append("and m1.movie_no = ss1.movie_no												");
			sql.append("and ss1.schedule_no = st1.schedule_no										");
			sql.append("and s1.theater_no = ?														");
			sql.append("and m1.movie_no = ?															");
			sql.append("and to_char(ss1.screen_date,'yyyy/mm/dd') = ?								");

			pstmt.setInt(1, theaterNo);
			pstmt.setInt(2, movieNo);
			pstmt.setString(3, screenDate);

			rs = pstmt.executeQuery();
			int turnNo = 0;
			while(rs.next()) {
				ScreenScheduleVO screenScheduleVO = new ScreenScheduleVO();
				if(turnNo!=rs.getInt(4)) {
					screenScheduleVO.setScheduleNo(rs.getInt(1));
					screenScheduleVO.setScreenName(rs.getString(2));
					screenScheduleVO.setMovieTitle(rs.getString(3));
				}

				ScheduleTurnVO scheduleTurnVO = new ScheduleTurnVO();
				scheduleTurnVO.setTurnNo(rs.getInt(4));
				scheduleTurnVO.setStartTime(rs.getString(5));
				scheduleTurnVO.setEndTime(rs.getString(6));

				screenScheduleVO.addScheduleTurnVO(scheduleTurnVO);

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
