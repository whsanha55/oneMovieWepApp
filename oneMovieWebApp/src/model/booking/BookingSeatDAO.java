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
	
	//예매한 좌석번호 일괄 등록
	public void insertBookingSeat(int ticketNo, int[] seatNo) throws Exception {
		
	}
	
	//회차번호에 해당하는 예매된 좌석 조회
	public List<BookingSeatVO> selectBookingSeatList(int turnNo) throws Exception {
		List<BookingSeatVO> list = null;
		return list;
	}
	
	//예약 취소 가능여부 확인
	public boolean selectBookingRefundable(int ticketNo) throws Exception {
		
		return true;
	}
	
	//예약한 좌석정보들 삭제
	public void deleteSeat(String ticketNo) throws Exception {
		
	}
	
	
	
}
