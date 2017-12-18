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

	//예매  등록
	public int insertBooking(BookingVO bookingVO) throws Exception {
		int ticektNo = 0;
		return ticektNo;
	}

	//예약 조회 (상영관번호, 회차번호)
	public List<BookingVO> selectBookingList(String keyfield, String keyword, int startRow, int endRow)
			throws Exception {
		List<BookingVO> list = null;
		return list;
	}

	//예약 조회(회원번호, 회원이름)
	public List<BookingVO> selectBookingList(String keyfield, String keyword, int condition, int startRow, int endRow)
			throws Exception {
		List<BookingVO> list = null;
		return list;
	}

	//예약 취소
	public void modifyBooking(String ticketNo) throws Exception {
		
	}
	
	
	
	

}
