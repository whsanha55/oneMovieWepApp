package model.theater;


import java.util.List;

import domain.theater.ScheduleTurnVO;

public class ScheduleTurnDAO {
	private ScheduleTurnDAO() {
		
	}
	
	private static ScheduleTurnDAO instance = new ScheduleTurnDAO();
	
	public static ScheduleTurnDAO getInstance() {
		return instance;
	}
	
	//회차 등록   							8
	public void insertTurn(List<ScheduleTurnVO> list) throws Exception {
		
		
	}
	//회차 일정 조회						9
	public List<ScheduleTurnVO> selectScheduleTurnList(int scheduleNo)throws Exception {
		List<ScheduleTurnVO> list = null;
		
		
		return list;
		
	}
	//회차 일정 삭제						10
	public void removeScheduleTurn(int scheduleNo)throws Exception {
		
	}

	
	//영화,날짜,지점 선택시 -> 회차 조회	23
	public List<ScheduleTurnVO> selectScheduleTurn(int screenNo,String screenDate,int movieNo)throws Exception{
		List<ScheduleTurnVO> list = null;
		
		return list;
	}
}
