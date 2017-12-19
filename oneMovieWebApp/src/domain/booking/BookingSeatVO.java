package domain.booking;

public class BookingSeatVO {
	int bookingSeatNo;
	String ticketNo;
	int seatNo;
	String seatName;

	public BookingSeatVO() {
	}

	public int getBookingSeatNo() {
		return bookingSeatNo;
	}

	public void setBookingSeatNo(int bookingSeatNo) {
		this.bookingSeatNo = bookingSeatNo;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public int getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}

	public String getSeatName() {
		return seatName;
	}

	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}
	

}
