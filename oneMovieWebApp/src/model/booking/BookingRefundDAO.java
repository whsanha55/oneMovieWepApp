package model.booking;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;

import domain.booking.BookingRefundVO;

public class BookingRefundDAO {
	private BookingRefundDAO() {

	}

	private static BookingRefundDAO instance = new BookingRefundDAO();

	public static BookingRefundDAO getInstance() {
		return instance;
	}
	
	public void insertRefund(BookingRefundVO bookingRefundVO, Connection conn) throws Exception {
		PreparedStatement pstmt = null;
		CallableStatement cstmt = null;
		try {
			/*
			StringBuilder sql = new StringBuilder();
			sql.append("insert into booking_refund(refund_no, ticket_no, refund_date, refund_price) ");
			sql.append("values (booking_refund_no_seq.nextval, ?, ?, ?                              ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, bookingRefundVO.getTicketNo());
			pstmt.setString(2, bookingRefundVO.getRefundDate());
			pstmt.setInt(3, bookingRefundVO.getRefundPrice());
			
			pstmt.executeUpdate();
			*/
			String sql = "{call  booking_refund_procedure(?,?,?)}";
			cstmt = conn.prepareCall(sql);
			cstmt.setString(1, bookingRefundVO.getTicketNo());
			cstmt.setDouble(2, bookingRefundVO.getRefundDatePolicy());
			cstmt.setDouble(3, bookingRefundVO.getRefundPricePolicy());
			cstmt.execute();
		} finally {
			if(cstmt != null) cstmt.close();
		}
	}
}
