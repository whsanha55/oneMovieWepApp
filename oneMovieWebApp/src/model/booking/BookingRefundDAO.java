package model.booking;

import domain.booking.BookingRefundVO;

public class BookingRefundDAO {
	private BookingRefundDAO() {

	}

	private static BookingRefundDAO instance = new BookingRefundDAO();

	public static BookingRefundDAO getInstance() {
		return instance;
	}
	
	public void insertRefund(BookingRefundVO refundVO) throws Exception {
		
	}
}
