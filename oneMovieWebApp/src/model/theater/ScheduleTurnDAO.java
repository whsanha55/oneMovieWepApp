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
	
	//ȸ�� ���   							8
	public void insertTurn(List<ScheduleTurnVO> list) throws Exception {
		
		
	}
	//ȸ�� ���� ��ȸ						9
	public List<ScheduleTurnVO> selectScheduleTurnList(int scheduleNo)throws Exception {
		List<ScheduleTurnVO> list = null;
		
		
		return list;
		
	}
	//ȸ�� ���� ����						10
	public void removeScheduleTurn(int scheduleNo)throws Exception {
		
	}

	
	//��ȭ,��¥,���� ���ý� -> ȸ�� ��ȸ	23
	public List<ScheduleTurnVO> selectScheduleTurn(int screenNo,String screenDate,int movieNo)throws Exception{
		List<ScheduleTurnVO> list = null;
		
		return list;
	}
}
