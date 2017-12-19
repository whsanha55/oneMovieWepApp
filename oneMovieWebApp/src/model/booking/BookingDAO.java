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
			sql.append("insert into BOOKING(ticket_no, member_no, turn_no, payment_code, price) ");
			sql.append("values(to_char(sysdate,'YYMMDD') || lpad(ticket_no_seq.nextval,5,0) ");
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
	public List<BookingVO> selectBookingList(String keyfield, int keyword, int startRow, int endRow) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BookingVO> list = new ArrayList<BookingVO>();
		try {
			conn = DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s13,s15                           ");
			sql.append("from (select booking.*,  rownum as rn                                               ");
			sql.append("  from(select t4.member_no s1, t4.name s2, t6.theater_name s3,                      ");
			sql.append("          t7.screen_name s4, to_char(t8.screen_date,'YYYY/MM/DD') s5,               ");
			sql.append("          to_char(t9.start_time,'HH24:MI') s6,  to_char(t9.end_time,'HH24:MI') s7,  ");
			sql.append("          t1.ticket_no s8, t5.movie_title s9, t1.payment_code s10, t1.status s11,   ");
			sql.append("          t1.withdraw_date s12, t10.seat_name s13, t3.refund_price s14,t3.refund_date s15   ");
			sql.append("       from booking t1, booking_seat t2, booking_refund t3, member t4, movie t5,    ");
			sql.append("           theater t6, screen t7, screen_schedule t8, schedule_turn t9, seat t10    ");
			sql.append("       where t1.ticket_no = t2.ticket_no             ");
			sql.append("           and t1.ticket_no = t3.ticket_no(+)        ");
			sql.append("           and t1.member_no = t4.member_no           ");
			sql.append("            and t1.turn_no = t9.turn_no              ");
			sql.append("          and t9.schedule_no = t8.schedule_no        ");
			sql.append("           and t8.movie_no = t5.movie_no             ");
			sql.append("         and t8.screen_no = t7.screen_no             ");
			sql.append("          and t7.theater_no = t6.theater_no          ");
			sql.append("          and t2.seat_no = t10.seat_no               ");
			if (keyfield.equals("seatNo")) {
				sql.append("and t9.turn_no = ?                               ");
			} else if (keyfield.equals("screenNo")) {
				sql.append("and t7.screen_no = ?                             ");
			}

			// 날짜 내림차순, 회차 오름차순, 예매번호 오름차순
			sql.append("       order by s5 desc ,s6 asc, s8 asc) booking)    ");

			sql.append("   where rn between ? and ?                          ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, keyword);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			String ticketNo = null;
			while (rs.next()) {
				BookingVO bookingVO = new BookingVO();
				if (!ticketNo.equals(rs.getString(8))) {
					ticketNo = rs.getString(8);
					bookingVO.setMemberNo(rs.getString(1));
					bookingVO.setMemberName(rs.getString(2));
					bookingVO.setTheaterName(rs.getString(3));
					bookingVO.setScreenName(rs.getString(4));
					bookingVO.setScreenDate(rs.getString(5));
					bookingVO.setStartTime(rs.getString(6));
					bookingVO.setEndTime(rs.getString(7));
					bookingVO.setTicketNo(rs.getString(8));
					bookingVO.setMovieTitle(rs.getString(9));
					bookingVO.setPaymentCode(rs.getString(10));
					bookingVO.setStatus(rs.getInt(11));
					bookingVO.setWithdrawDate(rs.getString(12));
					bookingVO.addSeatName(rs.getString(13));
					if (rs.getInt(11) != 0) {
						BookingRefundVO bookingRefundVO = new BookingRefundVO();
						bookingRefundVO.setRefundPrice(rs.getInt(14));
						bookingRefundVO.setRufundDate(rs.getString(15));
						bookingVO.setBookingRefundVO(bookingRefundVO);
					}
					list.add(bookingVO);
				} else {
					list.get(list.size() - 1).addSeatName(rs.getString(13));
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
			sql.append("select s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s13,s15                           ");
			sql.append("from (select booking.*,  rownum as rn                                               ");
			sql.append("  from(select t4.member_no s1, t4.name s2, t6.theater_name s3,                      ");
			sql.append("          t7.screen_name s4, to_char(t8.screen_date,'YYYY/MM/DD') s5,               ");
			sql.append("          to_char(t9.start_time,'HH24:MI') s6,  to_char(t9.end_time,'HH24:MI') s7,  ");
			sql.append("          t1.ticket_no s8, t5.movie_title s9, t1.payment_code s10, t1.status s11,   ");
			sql.append("          t1.withdraw_date s12, t10.seat_name s13, t3.refund_price s14,t3.refund_date s15   ");
			sql.append("       from booking t1, booking_seat t2, booking_refund t3, member t4, movie t5,    ");
			sql.append("           theater t6, screen t7, screen_schedule t8, schedule_turn t9, seat t10    ");
			sql.append("       where t1.ticket_no = t2.ticket_no             ");
			sql.append("           and t1.ticket_no = t3.ticket_no(+)        ");
			sql.append("           and t1.member_no = t4.member_no           ");
			sql.append("            and t1.turn_no = t9.turn_no              ");
			sql.append("          and t9.schedule_no = t8.schedule_no        ");
			sql.append("           and t8.movie_no = t5.movie_no             ");
			sql.append("         and t8.screen_no = t7.screen_no             ");
			sql.append("          and t7.theater_no = t6.theater_no          ");
			sql.append("          and t2.seat_no = t10.seat_no               ");
			
			if (keyfield.equals("memberNo")) {
				System.out.println(keyfield+1);
				sql.append("and t4.member_no like '%' || ? || '%'                          ");
			} else if (keyfield.equals("memberName")) {
				sql.append("and t4.name like '%' || ? || '%'                               ");
			}
			
			if (status == 1) {
				sql.append("and t1.status = 1          ");
			} else if (status == 2) {
				sql.append(" and to_char(t8.screen_date,'yyyy/mm/dd') || ' '|| to_char(t9.start_time,'HH24:MI')   ");
				sql.append("> to_char(sysdate,'yyyy/mm/dd hh24:mi')                                               ");
			} else if (status == 3) {
				sql.append(" and to_char(t8.screen_date,'yyyy/mm/dd') || ' '|| to_char(t9.start_time,'HH24:MI')   ");
				sql.append("< to_char(sysdate,'yyyy/mm/dd hh24:mi')                                               ");
			}

			// 날짜 내림차순, 회차 오름차순, 회원번호 오름차순
			sql.append("       order by s5 desc ,s6 asc, s1 asc) booking)    ");

			sql.append("   where rn between ? and ?                        ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, keyword);
			
			
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			 
			rs = pstmt.executeQuery();
			String ticketNo = null;
			while (rs.next()) {
				BookingVO bookingVO = new BookingVO();
				if (!rs.getString(8).equals(ticketNo)) {
					ticketNo = rs.getString(8);
					System.out.println(ticketNo);
					bookingVO.setMemberNo(rs.getString(1));
					bookingVO.setMemberName(rs.getString(2));
					bookingVO.setTheaterName(rs.getString(3));
					bookingVO.setScreenName(rs.getString(4));
					bookingVO.setScreenDate(rs.getString(5));
					bookingVO.setStartTime(rs.getString(6));
					bookingVO.setEndTime(rs.getString(7));
					bookingVO.setTicketNo(rs.getString(8));
					bookingVO.setMovieTitle(rs.getString(9));
					bookingVO.setPaymentCode(rs.getString(10));
					bookingVO.setStatus(rs.getInt(11));
					bookingVO.setWithdrawDate(rs.getString(12));
					bookingVO.addSeatName(rs.getString(13));
					if (rs.getInt(11) != 0) {
						BookingRefundVO bookingRefundVO = new BookingRefundVO();
						bookingRefundVO.setRefundPrice(rs.getInt(14));
						bookingRefundVO.setRufundDate(rs.getString(15));
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

	// 예약 취소 (status 0->1, 날짜 기록)
	public void modifyBooking(String ticketNo, Connection conn) throws Exception {
		PreparedStatement pstmt = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("update booking                              ");
			sql.append("set status = 1, withdraw_date = sysdate     ");
			sql.append("where ticekt_no = ?                         ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, ticketNo);
			pstmt.executeUpdate();
		} finally {
			if(pstmt != null) pstmt.close();
		}
	}

}
