package model.theater;

import java.util.List;

import domain.theater.SeatVO;

public class SeatDAO {
	private SeatDAO() {

	}

	private static SeatDAO instance = new SeatDAO();

	public static SeatDAO getInstance() {
		return instance;
	}
	
	//지점에 해당하고 상영관 번호에 해당하는 상영관 좌석 정보 조회		5
	public List<SeatVO> selectSeatList(int screenNo)throws Exception{
		List<SeatVO> list = null;
		
		return list;
	}
}
