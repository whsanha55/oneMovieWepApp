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
	
	//������ �ش��ϴ� �󿵰� ���� �˻�				4
	public List<TheaterVO> SelectScreenList (int[] theaterNo)throws Exception{
		List<TheaterVO>list = null;
		
		return list;
	}
}
