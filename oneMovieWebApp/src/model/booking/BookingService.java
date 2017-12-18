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

	// ��ȭ ���Ÿ� ����Ѵ�.
	public void addBooking(BookingVO bookingVO) throws Exception {
		Connection conn = null;

		// 1. ���� ������ ����Ѵ�.
		// 2. ���� �¼� ������ ����Ѵ�.
		// 3. Ʈ������� �����Ѵ�.
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

	// �˻����ǿ� �ش��ϴ� ���ų����� ���� ���¿� ���� ��ȸ�Ѵ�.

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

	// ȸ����ȣ�� �ش��ϴ� �����¼��� ��ȸ�Ѵ�.
	public List<BookingSeatVO> retrieveSeatListByTurnNo(int turnNo) throws Exception {
		BookingSeatDAO bookingSeatDAO = BookingSeatDAO.getInstance();
		return bookingSeatDAO.selectBookingSeatList(turnNo);
	}

	// ���� ��� ���� ���θ� ��ȸ�Ѵ�.
	public boolean retrieveBookingRefundable(int ticketNo) throws Exception {

		BookingSeatDAO bookingSeatDAO = BookingSeatDAO.getInstance();
		return bookingSeatDAO.selectBookingRefundable(ticketNo);
	}

	
	
	// ���Ÿ� ����Ѵ�.
	public void deleteBooking(BookingRefundVO bookingRefundVO) throws Exception {
		
		Connection conn = null;

		try {
			conn = DBConn.getConnection();
			conn.setAutoCommit(false);
			
			// 1. �����¼� ������ �����Ѵ�.
			BookingSeatDAO bookingSeatDAO = BookingSeatDAO.getInstance();
			bookingSeatDAO.deleteSeat(bookingRefundVO.getTicketNo(), conn);
			
			// 2. ���Ż��¸� �����ϰ� ��� ��¥�� ����Ѵ�.
			BookingDAO bookingDAO = BookingDAO.getInstance();
			bookingDAO.modifyBooking(bookingRefundVO.getTicketNo(), conn);

			// 3. ����ȯ�� ������ �Է��Ѵ�.
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
