package model.booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import conn.DBConn;
import domain.booking.BookingVO;
import sun.security.pkcs11.Secmod.DbMode;

public class BookingDAO {
	private BookingDAO() {

	}

	private static BookingDAO instance = new BookingDAO();

	public static BookingDAO getInstance() {
		return instance;
	}

	// 예매 등록
	public String insertBooking(BookingVO bookingVO, Connection conn) throws Exception {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		String ticektNo = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("insert into BOOKING(ticket_no, member_no, turn_no, payment_code, price) ");
			sql.append("values(to_char(sysdate,'YYYYMMDD') || lpad(ticket_no_seq.nextval,5,0) ");
			sql.append(" ,?,?,?,? )");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, bookingVO.getMemberNo());
			pstmt.setInt(2, bookingVO.getTurnNo());
			pstmt.setInt(3, bookingVO.getPaymentCode());
			pstmt.setInt(4, bookingVO.getPrice());

			pstmt.executeUpdate();
			pstmt.close();

			sql.setLength(0);

			stmt = conn.createStatement();
			sql.append("select ticket_no_seq.currval from dual ");

			ResultSet rs = stmt.executeQuery(sql.toString());
			if (rs.next()) {
				ticektNo = rs.getString(1);
			}

		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}

		return ticektNo;
	}

	// 예약 조회 (상영관번호, 회차번호)
	public List<BookingVO> selectBookingList(String keyfield, int keyword, int startRow, int endRow)
			throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn =DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			pstmt = conn.prepareStatement(sql.toString());
			
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}

		List<BookingVO> list = null;
		return list;
	}

	// 예약 조회(회원번호, 회원이름)
	public List<BookingVO> selectBookingList(String keyfield, String keyword, int condition, int startRow, int endRow)
			throws Exception {
		List<BookingVO> list = null;
		return list;
	}

	// 예약 취소
	public void modifyBooking(String ticketNo) throws Exception {

	}

}
