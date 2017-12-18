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

	// �˻����ǿ� �ش��ϴ� ���ų����� ���� ���¿� ���� ��ȸ�Ѵ�.

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

	// ȸ����ȣ�� �ش��ϴ� �����¼��� ��ȸ�Ѵ�.
	public List<BookingSeatVO> retrieveSeatListByTurnNo(int turnNo) throws Exception {
		return null;
	}

	// ���� ��� ���� ���θ� ��ȸ�Ѵ�.
	public boolean retrieveBookingRefundable(int ticketNo) throws Exception {
		return true;
	}

	// ���Ÿ� ����Ѵ�.
	public void deleteBooking(BookingRefundVO refundVO) throws Exception {
		// 1. �����¼� ������ �����Ѵ�.
		// 2. ���Ż��¸� �����ϰ� ��� ��¥�� ����Ѵ�.
		// 3. ����ȯ�� ������ �Է��Ѵ�.
		// 4.Ʈ������� �����Ѵ�.
	}

}
