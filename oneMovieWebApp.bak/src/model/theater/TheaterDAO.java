package model.theater;

import java.util.List;

import domain.theater.TheaterVO;

public class TheaterDAO {
	private TheaterDAO() {

	}

	private static TheaterDAO instance = new TheaterDAO();

	public static TheaterDAO getInstance() {
		return instance;
	}
	
	//지점 검색				3
	public List<TheaterVO> SelectTheaterList() throws Exception{
		List<TheaterVO> list = null;
		
		return list;
	}
}
