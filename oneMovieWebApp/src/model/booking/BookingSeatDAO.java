package model.booking;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conn.DBConn;
import domain.booking.BookingSeatVO;
import oracle.jdbc.internal.OracleTypes;

public class BookingSeatDAO {

	private BookingSeatDAO() {

	}

	private static BookingSeatDAO instance = new BookingSeatDAO();

	public static BookingSeatDAO getInstance() {
		return instance;
	}

	// 예매한 좌석번호 일괄 등록
	public void insertBookingSeat(String ticketNo, List<BookingSeatVO> bookingSeatVO, Connection conn) throws Exception {
		PreparedStatement pstmt = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("insert into booking_seat(booking_seat_no, ticket_no, seat_no)  ");
			sql.append("values(booking_seat_no_seq.nextval, ?,?) ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, ticketNo);
			System.out.println(ticketNo);
			for (BookingSeatVO seat : bookingSeatVO) {
				
				pstmt.setInt(2, seat.getSeatNo());
				pstmt.addBatch();
			}
			pstmt.executeBatch();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
		}
	}

	// 회차번호에 해당하는 예매된 좌석 조회
	public List<BookingSeatVO> selectBookingSeatList(int turnNo) throws Exception {
		/*Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BookingSeatVO> list = new ArrayList<BookingSeatVO>();

		try {
			conn = DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select  t1.seat_no, t1.seat_name ,                            ");
			sql.append("   (case when t1.seat_no = t2.seat_no then 1 else 0 end)      ");
			sql.append("from seat t1, booking_seat t2                                 ");
			sql.append("where t1.seat_no = t2.seat_no(+)                              ");
			sql.append("   and SCREEN_NO = (select screen_no                          ");
			sql.append("              from screen_schedule                            ");
			sql.append("              where schedule_no = (select schedule_no         ");
			sql.append("                       from schedule_turn                     ");
			sql.append("                        where turn_no = ?))                   ");
			sql.append("order by t1.seat_name                                         ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, turnNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				BookingSeatVO bookingSeatVO = new BookingSeatVO();
				bookingSeatVO.setSeatNo(rs.getInt(1));
				bookingSeatVO.setSeatName(rs.getString(2));
				if(rs.getInt(3) == 1) {
					bookingSeatVO.setBooked(true);
				} else {
					bookingSeatVO.setBooked(false);					
				}
				list.add(bookingSeatVO);

			}

		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

		return list;*/
		
		//연습
		Connection conn = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		List<BookingSeatVO> list = new ArrayList<BookingSeatVO>();
		try {
			conn = DBConn.getConnection();
			String sql = "{call booking_seat_procedure(?,?)}";
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, turnNo);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
			cstmt.executeUpdate();
			rs = (ResultSet) cstmt.getObject(2);
			while(rs.next()) {
				BookingSeatVO bookingSeatVO = new BookingSeatVO();
				bookingSeatVO.setSeatNo(rs.getInt(1));
				bookingSeatVO.setSeatName(rs.getString(2));
				if(rs.getInt(3) == 1) {
					bookingSeatVO.setBooked(true);
				} else {
					bookingSeatVO.setBooked(false);					
				}
				list.add(bookingSeatVO);
			}
			
		}finally {
			if(rs != null) rs.close();
			if(cstmt != null) cstmt.close();
			if(conn != null) conn.close();
		}
		
		
		return list;
	}

	
	// 예약한 좌석정보들 삭제
	public void deleteSeat(String ticketNo, Connection conn) throws Exception {

		PreparedStatement pstmt = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("delete booking_seat     ");
			sql.append("where ticket_no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, ticketNo);
			pstmt.executeUpdate();
		} finally {
			if (pstmt != null)
				pstmt.close();
		}
	}

}
