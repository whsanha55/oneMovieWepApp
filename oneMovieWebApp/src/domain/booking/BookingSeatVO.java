package domain.booking;

public class BookingSeatVO {
	int bookingSeatNo;
	String ticketNo;
	int seatNo;
	String seatName;
	boolean isBooked;
	boolean isFirstSeatLine;
	boolean isLastSeatLine;
	

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

	

	
	public boolean isBooked() {
		return isBooked;
	}

	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}

	public boolean isFirstSeatLine() {
		return isFirstSeatLine;
	}

	public void setFirstSeatLine(boolean isFirstSeatLine) {
		this.isFirstSeatLine = isFirstSeatLine;
	}

	public boolean isLastSeatLine() {
		return isLastSeatLine;
	}

	public void setLastSeatLine(boolean isLastSeatLine) {
		this.isLastSeatLine = isLastSeatLine;
	}

	@Override
	public String toString() {
		return "BookingSeatVO [bookingSeatNo=" + bookingSeatNo + ", ticketNo=" + ticketNo + ", seatNo=" + seatNo
				+ ", seatName=" + seatName + ", isBooked=" + isBooked + ", isFirstSeatLine=" + isFirstSeatLine
				+ ", isLastSeatLine=" + isLastSeatLine + "]";
	}
	

}
