package model.booking;

import java.util.List;

import domain.booking.BookingSeatVO;

public class BookingSeatDAO {

	private BookingSeatDAO() {

	}

	private static BookingSeatDAO instance = new BookingSeatDAO();

	public static BookingSeatDAO getInstance() {
		return instance;
	}
	
	//������ �¼���ȣ �ϰ� ���
	public void insertBookingSeat(int ticketNo, int[] seatNo) throws Exception {
		
	}
	
	//ȸ����ȣ�� �ش��ϴ� ���ŵ� �¼� ��ȸ
	public List<BookingSeatVO> selectBookingSeatList(int turnNo) throws Exception {
		List<BookingSeatVO> list = null;
		return list;
	}
	
	//���� ��� ���ɿ��� Ȯ��
	public boolean selectBookingRefundable(int ticketNo) throws Exception {
		
		return true;
	}
	
	//������ �¼������� ����
	public void deleteSeat(String ticketNo) throws Exception {
		
	}
	
	
	
}
