package model.booking;

import java.util.List;

import domain.booking.BookingVO;

public class BookingDAO {
	private BookingDAO() {

	}

	private static BookingDAO instance = new BookingDAO();

	public static BookingDAO getInstance() {
		return instance;
	}

	//����  ���
	public int insertBooking(BookingVO bookingVO) throws Exception {
		int ticektNo = 0;
		return ticektNo;
	}

	//���� ��ȸ (�󿵰���ȣ, ȸ����ȣ)
	public List<BookingVO> selectBookingList(String keyfield, String keyword, int startRow, int endRow)
			throws Exception {
		List<BookingVO> list = null;
		return list;
	}

	//���� ��ȸ(ȸ����ȣ, ȸ���̸�)
	public List<BookingVO> selectBookingList(String keyfield, String keyword, int condition, int startRow, int endRow)
			throws Exception {
		List<BookingVO> list = null;
		return list;
	}

	//���� ���
	public void modifyBooking(String ticketNo) throws Exception {
		
	}
	
	
	
	

}
