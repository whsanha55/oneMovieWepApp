package model.theater;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conn.DBConn;
import domain.theater.ScheduleTurnVO;
import domain.theater.ScreenScheduleVO;

public class ScreenScheduleDAO {
	private ScreenScheduleDAO() {

	}

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
			pstmt = conn.prepareStatement(sql.toString());
			sql.append("select t1.theater_name,ss1.screen_date,m1.movie_name,st2.turn_no						");
			sql.append(",to_char(st2.start_time,'hh24:mi'),to_char(st2.end_time,'hh24:mi')						");		
			sql.append("from theater t1,screen s1,screen_schedule ss1, movie m1(select rownum as rn, st1.*		");	
			sql.append("	 from (select *										 								");
			sql.append("	 		from schedule_turn 										 					");
			sql.append("	 		order by turn_no desc) st1)	st2					 							");
			sql.append("where t1.theater_no = s1.theater_no										 				");
			sql.append("and m1.movie_no = ss1.movie_no										 					");
			sql.append("and ss1.schedule_no = st2.schedule_no										 			");
			sql.append("and rn >= ? and rn <= ?													 				");
			
			if(keyfield.equals("theater_name")) {
				sql.append("and t1.theater_name like '%' || ? || '%'											");
			}else if(keyfield.equals("movie_title")) {
				sql.append("and m1.movie_title like '%' || ? || '%'												");
			}
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			pstmt.setString(3, keyword);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ScreenScheduleVO screenScheduleVO = new ScreenScheduleVO();
				if(true) {
					screenScheduleVO.setTheaterName(rs.getString(1));
					screenScheduleVO.setScreenDate(rs.getString(2));
					screenScheduleVO.setMovieName(rs.getString(3)); 
				}else{	
					ScheduleTurnVO scheduleTurnVO = new ScheduleTurnVO();
					scheduleTurnVO.setTurnNo(rs.getInt(4));
					scheduleTurnVO.setStartTime(rs.getString(5));
					scheduleTurnVO.setEndTime(rs.getString(6));
					screenScheduleVO.addScheduleTurnVO(scheduleTurnVO);
				}

				screenList.add(screenScheduleVO);
			}

		} finally {
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
		return screenList;
		
	}
	
	//상영일정 등록 			6
	public void InsertScreenSchedule(List<ScreenScheduleVO>list) throws Exception{
		
	}
	//상영일정 삭제				7
	public void removeScreenSchedule(int scheduleNo) throws Exception{
		
	}
	
}
