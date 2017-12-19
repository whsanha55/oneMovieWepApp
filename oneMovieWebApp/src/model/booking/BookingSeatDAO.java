package model.booking;

import java.util.List;

import domain.booking.BookingSeatVO;

public class BookingSeatDAO {

	private BookingSeatDAO() {

	}

	private static BookingSeatDAO instance = new BookingSeatDAO();

	public static BookingSeatDAO getInstance() {
		return instance;
	}
	
	//������ �¼���ȣ �ϰ� ���
	public void insertBookingSeat(String ticketNo, int[] seatNo) throws Exception {
		
	}
	
	//ȸ����ȣ�� �ش��ϴ� ���ŵ� �¼� ��ȸ
	public List<BookingSeatVO> selectBookingSeatList(int turnNo) throws Exception {
		List<BookingSeatVO> list = null;
		return list;
	}
	
	//���� ��� ���ɿ��� Ȯ��
	public boolean selectBookingRefundable(int ticketNo) throws Exception {
<<<<<<< HEAD
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean refundable = false;
		try {
			conn = DBConn.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select 1                                      ");
			sql.append("from screen_schedule                          ");
			sql.append("where screen_date >= sysdate+ 1/24/2          ");
			sql.append("  and schedule_no = (select schedule_no       ");
			sql.append("                    from schedule_turn                    ");
			sql.append("                    where turn_no = (select turn_no       ");
			sql.append("                                     from booking         ");
			sql.append("                                     where ticket_no = ?)) ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, ticketNo);

			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt(1) == 1) {
					refundable = false;
				} 
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

	// ������ �¼������� ����
	public void deleteSeat(String ticketNo, Connection conn) throws Exception {
=======
>>>>>>> branch 'master' of https://github.com/whsanha55/oneMovieWepApp.git
		
		return true;
	}
	
	//������ �¼������� ����
	public void deleteSeat(String ticketNo) throws Exception {
		
	}
	
	
	
}
