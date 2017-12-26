package model.booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conn.DBConn;
import domain.booking.BookingRefundVO;
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
			sql.append("insert into BOOKING(ticket_no, member_no, turn_no, payment_code, price)    ");
			sql.append("values('D' || to_char(sysdate,'MMDD') || 'N' || lpad(ticket_no_seq.nextval,5,0)   ");
			sql.append(" ,?,?,?,? )");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, bookingVO.getMemberNo());
			pstmt.setInt(2, bookingVO.getTurnNo());
			pstmt.setString(3, bookingVO.getPaymentCode());
			pstmt.setInt(4, bookingVO.getPrice());

			pstmt.executeUpdate();
			pstmt.close();

			sql.setLength(0);

			stmt = conn.createStatement();
			sql.append("select 'D' || to_char(sysdate,'MMDD') || 'N' || lpad(ticket_no_seq.currval,5,0) from dual  ");

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

	// 예약 조회 (상영관번호 및 상영날짜)
	public List<BookingVO> selectBookingList(ArrayList<Integer> screenList, String screenDate, int startRow, int endRow)
			throws Exception {
		// 회원번호, 이름,지점,상영관,영화제목, 상영날짜, 회차시간,좌석번호,예매번호,승인코드

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BookingVO> list = new ArrayList<BookingVO>();
		try {
			conn = DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select *                                                                       ");
			sql.append("from (select  ticket_no, member_no, member_name, theater_name, movie_title,     ");
			sql.append("           screen_name, screen_date, start_time, end_time,                      ");
			sql.append("           payment_code, seat_name ,rownum rn                                   ");
			sql.append("      from booking_view                                                         ");
			sql.append("      where status =0                                                           ");
			sql.append("      and screen_date = ?                                                       ");

			sql.append("      and screen_no in (?                                                       ");
			for (int i = 1; i < screenList.size(); i++) {
				sql.append(",?            ");
			}
			sql.append(")                 ");
			sql.append(") where rn between ? and ?                                              ");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, screenDate);
			int num = 0;
			while(num<screenList.size()) {
				pstmt.setInt(num+2, screenList.get(num));
				num++;
			}
			pstmt.setInt(num+2, startRow);
			pstmt.setInt(num+3, endRow);
			
			rs = pstmt.executeQuery();
			String ticketNo = null;
			while (rs.next()) {
				BookingVO bookingVO = new BookingVO();
				if (!rs.getString(1).equals(ticketNo)) {
					ticketNo = rs.getString(1);

					bookingVO.setTicketNo(rs.getString(1));
					bookingVO.setMemberNo(rs.getString(2));
					bookingVO.setMemberName(rs.getString(3));
					bookingVO.setTheaterName(rs.getString(4));
					bookingVO.setMovieTitle(rs.getString(5));
					bookingVO.setScreenName(rs.getString(6));
					bookingVO.setScreenDate(rs.getString(7));
					bookingVO.setStartTime(rs.getString(8));
					bookingVO.setEndTime(rs.getString(9));
					bookingVO.setPaymentCode(rs.getString(10));
					bookingVO.addSeatName(rs.getString(11));
				
					list.add(bookingVO);
				} else {
					list.get(list.size() - 1).addSeatName(rs.getString(11));
				}

			}

		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

		return list;
	}

	// 예약 조회 (회차번호)
	public List<BookingVO> selectBookingList(ArrayList<Integer> turnList, int startRow, int endRow) throws Exception {
		// 회원번호, 이름,지점,상영관,영화제목, 상영날짜, 회차시간,좌석번호,예매번호,승인코드

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BookingVO> list = new ArrayList<BookingVO>();
		try {
			conn = DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select *                                                                       ");
			sql.append("from (select  ticket_no, member_no, member_name, theater_name, movie_title,     ");
			sql.append("           screen_name, screen_date, start_time, end_time,                      ");
			sql.append("           payment_code, seat_name ,rownum rn                                   ");
			sql.append("      from booking_view                                                         ");
			sql.append("      where status =0                                                           ");

			sql.append("      and turn_no in (?                                                       ");
			for (int i = 1; i < turnList.size(); i++) {
				sql.append(",?            ");
			}
			sql.append(")                 ");
			sql.append(") where rn between ? and ?                                              ");
			pstmt = conn.prepareStatement(sql.toString());
			
			int num = 0;
			while(num<turnList.size()) {
				pstmt.setInt(num+1, turnList.get(num));
				num++;
			}
			pstmt.setInt(num+1, startRow);
			pstmt.setInt(num+2, endRow);
			
			rs = pstmt.executeQuery();
			String ticketNo = null;
			while (rs.next()) {
				BookingVO bookingVO = new BookingVO();
				if (!rs.getString(1).equals(ticketNo)) {
					ticketNo = rs.getString(1);

					bookingVO.setTicketNo(rs.getString(1));
					bookingVO.setMemberNo(rs.getString(2));
					bookingVO.setMemberName(rs.getString(3));
					bookingVO.setTheaterName(rs.getString(4));
					bookingVO.setMovieTitle(rs.getString(5));
					bookingVO.setScreenName(rs.getString(6));
					bookingVO.setScreenDate(rs.getString(7));
					bookingVO.setStartTime(rs.getString(8));
					bookingVO.setEndTime(rs.getString(9));
					bookingVO.setPaymentCode(rs.getString(10));
					bookingVO.addSeatName(rs.getString(11));
				
					list.add(bookingVO);
				} else {
					list.get(list.size() - 1).addSeatName(rs.getString(11));
				}

			}

		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

		return list;
	}

	// 예약 조회(회원번호, 회원이름)
	// 상영예정, 상영완료, 예매취소, 모든예매
	// 모든예매 0, 예매취소 1, 상영예정 2, 상영완료 3
	public List<BookingVO> selectBookingList(String keyfield, String keyword, int status, int startRow, int endRow)
			throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BookingVO> list = new ArrayList<BookingVO>();
		try {
			conn = DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select *                                                                ");
			sql.append("from (select ticket_no, member_no, member_name, theater_name,           ");
			sql.append("        movie_title, screen_name, screen_date, start_time, end_time ,   ");
			sql.append("        payment_code, status, withdraw_date, seat_name,        ");
			sql.append("        refund_price, refund_date, rownum rn                            ");
			sql.append("      from booking_view                                                 ");

			if (keyfield.equals("memberNo")) {
				sql.append("where member_no like '%' || ? || '%'                          ");
			} else if (keyfield.equals("memberName")) {
				sql.append("where member_name like '%' || ? || '%'                               ");
			}

			if (status == 1) {
				sql.append("and status = 1          ");
			} else if (status == 2) {
				sql.append("and status = 0          ");
				sql.append("and screen_date || start_time >= to_char(sysdate,'YYYY/MM/DD HH24:MI')     ");
			} else if (status == 3) {
				sql.append("and status = 0          ");
				sql.append("and screen_date || start_time < to_char(sysdate,'YYYY/MM/DD HH24:MI')     ");
			}

			sql.append(") where rn between ? and ?                                              ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, keyword);

			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);

			rs = pstmt.executeQuery();
			String ticketNo = null;
			while (rs.next()) {
				BookingVO bookingVO = new BookingVO();
				if (!rs.getString(1).equals(ticketNo)) {
					ticketNo = rs.getString(1);

					bookingVO.setTicketNo(rs.getString(1));
					bookingVO.setMemberNo(rs.getString(2));
					bookingVO.setMemberName(rs.getString(3));
					bookingVO.setTheaterName(rs.getString(4));
					bookingVO.setMovieTitle(rs.getString(5));
					bookingVO.setScreenName(rs.getString(6));
					bookingVO.setScreenDate(rs.getString(7));
					bookingVO.setStartTime(rs.getString(8));
					bookingVO.setEndTime(rs.getString(9));
					bookingVO.setPaymentCode(rs.getString(10));
					bookingVO.setStatus(rs.getInt(11));
					bookingVO.setWithdrawDate(rs.getString(12));
					bookingVO.addSeatName(rs.getString(13));
					if (rs.getInt(11) != 0) {
						BookingRefundVO bookingRefundVO = new BookingRefundVO();
						bookingRefundVO.setRefundPrice(rs.getInt(14));
						bookingRefundVO.setRefundDate(rs.getString(15));
						bookingVO.setBookingRefundVO(bookingRefundVO);
					}
					list.add(bookingVO);
				} else {
					list.get(list.size() - 1).addSeatName(rs.getString(13));
				}

			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null)
				pstmt.close();

			if (conn != null)
				conn.close();
		}

		return list;
	}

	// 예약 취소 가능여부 확인
	public boolean selectBookingRefundable(String ticketNo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean refundable = false;
		try {
			conn = DBConn.getConnection();
			StringBuilder sql = new StringBuilder();

			sql.append("select distinct 1                                          ");
			sql.append("from booking_view                                          ");
			sql.append("where screen_date || start_time                            ");
			sql.append("     >= to_char(sysdate + 1/24/2,'YYYY/MM/DD HH24:MI')     ");
			sql.append("and ticket_no = ?                                          ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, ticketNo);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				refundable = true;
			}

		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return refundable;
	}

	// 예약 취소 (status 0->1, 날짜 기록)
	public void modifyBooking(String ticketNo, Connection conn) throws Exception {
		PreparedStatement pstmt = null;

		try {
			StringBuilder sql = new StringBuilder();
			sql.append("update booking                              ");
			sql.append("set status = 1, withdraw_date = sysdate     ");
			sql.append("where ticket_no = ?                         ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, ticketNo);
			pstmt.executeUpdate();
		} finally {
			if (pstmt != null)
				pstmt.close();
		}
	}

}
