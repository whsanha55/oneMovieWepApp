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
			String[] seatNo = null;
			bookingSeatDAO.insertBookingSeat(ticketNo, seatNo, conn);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	// 검색조건에 해당하는 예매내역을 예매 상태에 따라 조회한다.

	public List<BookingVO> retrieveBookingList(String keyfield, int keyword, int startRow, int endRow)
			throws Exception {

		BookingDAO bookingDAO = BookingDAO.getInstance();
		return bookingDAO.selectBookingList(keyfield, keyword, startRow, endRow);
	}

	public List<BookingVO> retrieveBookingList(String keyfield, String keyword, int status, int startRow, int endRow)
			throws Exception {
		BookingDAO bookingDAO = BookingDAO.getInstance();
		return bookingDAO.selectBookingList(keyfield, keyword, status, startRow, endRow);
	}

	// 회차번호에 해당하는 예약좌석을 조회한다.
	public List<BookingSeatVO> retrieveSeatListByTurnNo(int turnNo) throws Exception {
		BookingSeatDAO bookingSeatDAO = BookingSeatDAO.getInstance();
		return bookingSeatDAO.selectBookingSeatList(turnNo);
	}

	// 예매 취소 가능 여부를 조회한다.
	public boolean retrieveBookingRefundable(int ticketNo) throws Exception {

		BookingSeatDAO bookingSeatDAO = BookingSeatDAO.getInstance();
		return bookingSeatDAO.selectBookingRefundable(ticketNo);
	}

	
	
	// 예매를 취소한다.
	public void deleteBooking(BookingRefundVO bookingRefundVO) throws Exception {
		
		Connection conn = null;

		try {
			conn = DBConn.getConnection();
			conn.setAutoCommit(false);
			
			// 1. 예매좌석 정보를 삭제한다.
			BookingSeatDAO bookingSeatDAO = BookingSeatDAO.getInstance();
			bookingSeatDAO.deleteSeat(bookingRefundVO.getTicketNo(), conn);
			
			// 2. 예매상태를 변경하고 취소 날짜를 기록한다.
			BookingDAO bookingDAO = BookingDAO.getInstance();
			bookingDAO.modifyBooking(bookingRefundVO.getTicketNo(), conn);

			// 3. 예매환불 정보를 입력한다.
			BookingRefundDAO bookingRefundDAO = BookingRefundDAO.getInstance();
			bookingRefundDAO.insertRefund(bookingRefundVO, conn);
		
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			if (conn != null)
				conn.close();
		}
	}

}
