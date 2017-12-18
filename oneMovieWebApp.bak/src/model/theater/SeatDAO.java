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
	
	//������ �ش��ϰ� �󿵰� ��ȣ�� �ش��ϴ� �󿵰� �¼� ���� ��ȸ		5
	public List<SeatVO> selectSeatList(int screenNo)throws Exception{
		List<SeatVO> list = null;
		
		return list;
	}
}
