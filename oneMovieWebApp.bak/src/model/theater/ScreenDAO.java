package model.theater;

import java.util.List;

import domain.theater.TheaterVO;

public class ScreenDAO {
	private ScreenDAO() {

	}

	private static ScreenDAO instance = new ScreenDAO();

	public static ScreenDAO getInstance() {
		return instance;
	}
	
	//지점에 해당하는 상영관 정보 검색				4
	public List<TheaterVO> SelectScreenList (int[] theaterNo)throws Exception{
		List<TheaterVO>list = null;
		
		return list;
	}
}
