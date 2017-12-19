package model.booking;

import java.sql.Connection;
import java.util.List;

import conn.DBConn;
import domain.booking.BookingRefundVO;
import domain.booking.BookingSeatVO;
import domain.booking.BookingVO;

public class BookingService {
	private BookingService() {

	}

	private static BookingService instance = new BookingService();

	public static BookingService getInstance() {
		return instance;
	}

	// 영화 예매를 등록한다.
	public void addBooking(BookingVO bookingVO) throws Exception {
		Connection conn = null;

		// 1. 예매 정보를 등록한다.
		// 2. 예매 좌석 정보를 등록한다.
		// 3. 트랜잭션을 수행한다.
		try {
			conn = DBConn.getConnection();
			conn.setAutoCommit(false);
			
			BookingDAO bookingDAO = BookingDAO.getInstance();
			String ticketNo = bookingDAO.insertBooking(bookingVO, conn);
			BookingSeatDAO bookingSeatDAO = BookingSeatDAO.getInstance();
			int[] seatNo = null;
			bookingSeatDAO.insertBookingSeat(ticketNo, seatNo);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		}finally {
			if(conn != null) conn.close();
		} 
	}

	// 검색조건에 해당하는 예매내역을 예매 상태에 따라 조회한다.

	public List<BookingVO> retrieveBookingList(String keyfield, String keyword, int startRow, int endRow)
			throws Exception {
		List<BookingVO> list = null;
		return list;
	}

	public List<BookingVO> retrieveBookingList(String keyfield, String keyword, int status, int startRow, int endRow)
			throws Exception {
		List<BookingVO> list = null;
		return list;
	}

	// 회차번호에 해당하는 예약좌석을 조회한다.
	public List<BookingSeatVO> retrieveSeatListByTurnNo(int turnNo) throws Exception {
		return null;
	}

	// 예매 취소 가능 여부를 조회한다.
	public boolean retrieveBookingRefundable(int ticketNo) throws Exception {
		return true;
	}

	// 예매를 취소한다.
	public void deleteBooking(BookingRefundVO refundVO) throws Exception {
		// 1. 예매좌석 정보를 삭제한다.
		// 2. 예매상태를 변경하고 취소 날짜를 기록한다.
		// 3. 예매환불 정보를 입력한다.
		// 4.트랜잭션을 수행한다.
	}

}
