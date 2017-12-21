package model.theater;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conn.DBConn;
import domain.theater.ScheduleTurnVO;
import domain.theater.ScreenScheduleVO;

public class ScreenScheduleDAO {
	
	private ScreenScheduleDAO() {

	}
	
	// 싱글톤 패턴 - 하나의 객체를 여러사람이 공유
	private static ScreenScheduleDAO instance = new ScreenScheduleDAO();

	public static ScreenScheduleDAO getInstance() {
		return instance;
	}
	
	//상영일정 조회				2
	public List<ScreenScheduleVO> selectScreenScheduleList(String keyfield,String keyword,int startRow,int endRow)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ScreenScheduleVO> screenList = new ArrayList<ScreenScheduleVO>();
		
		try {
			conn =DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("select t1.theater_name,to_char(ss1.screen_date,'yyyy/mm/dd'),m1.movie_title,st2.turn_no,s1.screen_name		");
			sql.append(",to_char(st2.start_time,'hh24:mi'),to_char(st2.end_time,'hh24:mi'),ss1.schedule_no							");		
			sql.append("from theater t1,screen s1,screen_schedule ss1, movie m1,(select rownum as rn, st1.*							");	
			sql.append("	 from (select *										 													");
			sql.append("	 		from schedule_turn 										 										");
			sql.append("	 		order by turn_no desc) st1)	st2					 												");
			sql.append("where t1.theater_no = s1.theater_no										 									");
			sql.append("and m1.movie_no = ss1.movie_no										 										");
			sql.append("and s1.screen_no = ss1.screen_no										 									");
			sql.append("and ss1.schedule_no = st2.schedule_no										 								");
			//sql.append("and rn >= ? and rn <= ?													 								");
			 
			if(keyfield.equals("theaterName")) {
				sql.append("and t1.theater_name like '%' || ? || '%'																");
			}else if(keyfield.equals("movieName")) {
				sql.append("and m1.movie_title like '%' || ? || '%'																	");
			} 
			sql.append("order by t1.theater_name asc, to_char(ss1.screen_date,'yyyy/mm/dd' )asc , s1.screen_name asc,st2.turn_no asc ");
			
			System.out.println(sql.toString());//DBsql 확인
			
			
			pstmt = conn.prepareStatement(sql.toString());
			//pstmt.setInt(1, startRow);
			//pstmt.setInt(2, endRow);
			pstmt.setString(1, keyword);
			
			rs = pstmt.executeQuery();
			int scheduleNo = 0;
			ScreenScheduleVO screenScheduleVO = null;
			
			while(rs.next()) {										
				
				if(scheduleNo!=rs.getInt(8)) {	
					screenScheduleVO = new ScreenScheduleVO();
					//screenScheduleVO.setScheduleNo(rs.getInt(8));
					screenScheduleVO.setTheaterName(rs.getString(1));
					screenScheduleVO.setScreenDate(rs.getString(2));
					screenScheduleVO.setMovieTitle(rs.getString(3)); 
					screenScheduleVO.setScreenName(rs.getString(5));					
					scheduleNo = rs.getInt(8);
					screenList.add(screenScheduleVO);
				}
				
				ScheduleTurnVO scheduleTurnVO = new ScheduleTurnVO();
				scheduleTurnVO.setTurnNo(rs.getInt(4));
				scheduleTurnVO.setStartTime(rs.getString(6));
				scheduleTurnVO.setEndTime(rs.getString(7));
				screenScheduleVO.addScheduleTurnVO(scheduleTurnVO);				
				
			}

		} finally {
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
		return screenList;


	}
	//상영일정 등록 			6
	public int InsertScreenSchedule(List<ScreenScheduleVO>list,Connection conn) throws Exception{
		PreparedStatement pstmt = null;
		Statement stmt = null;
		int scheduleNo = 0;
		try {
			StringBuffer sql = new StringBuffer();
			System.out.println("test1");
			sql.append("insert into screen_schedule(schedule_no,screen_no,screen_date,movie_no)	");
			sql.append("values(SCREEN_NO_SEQ.NEXTVAL,?,to_date(?,'yyyy/mm/dd'),?)				");
			System.out.println("test2");
			pstmt = conn.prepareStatement(sql.toString());
			for(ScreenScheduleVO schedule : list) {
				pstmt.setInt(2, schedule.getScreenNo());
				pstmt.setString(3, schedule.getScreenDate());
				pstmt.setInt(4, schedule.getMovieNo());
				pstmt.addBatch();
			}
			pstmt.executeBatch();	
			pstmt.close();
			sql.setLength(0);
			
			stmt = conn.createStatement();
			sql.append("select SCREEN_NO_SEQ.currval from dual ");
			ResultSet rs = stmt.executeQuery(sql.toString());
			
			if (rs.next()) {
				scheduleNo = rs.getInt(1);
			}	
		} finally {
			if (pstmt != null)pstmt.close();
		}
		return scheduleNo;
	}
	
	//상영일정 삭제				7
	public void removeScreenSchedule(int scheduleNo,Connection conn) throws Exception{
		PreparedStatement pstmt = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("delete from screen_schedule ");
			sql.append("where schedule_no = ?		");
			pstmt = conn.prepareStatement(sql.toString());
		
			pstmt.setInt(1, scheduleNo);

			pstmt.executeUpdate();			

		} finally {
			if (pstmt != null)pstmt.close();
		}
	}
	
}
