package model.theater;

import java.util.List;

import domain.theater.ScreenScheduleVO;

public class ScreenScheduleDAO {
	private ScreenScheduleDAO() {

	}

	private static ScreenScheduleDAO instance = new ScreenScheduleDAO();

	public static ScreenScheduleDAO getInstance() {
		return instance;
	}
	
	//상영일정 조회				2
	public List<ScreenScheduleVO> selectScreenScheduleList(String keyfield,String keyword,int StartRow,int endRow)throws Exception{
		List<ScreenScheduleVO> list = null;
		
		return list;
		
	}
	//상영일정 등록 			6
	public void InsertScreenSchedule(List<ScreenScheduleVO>list) throws Exception{
		
	}
	//상영일정 삭제				7
	public void removeScreenSchedule(int scheduleNo) throws Exception{
		
	}
	
}
