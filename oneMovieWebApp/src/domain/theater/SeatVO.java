package domain.theater;

public class SeatVO {
	int seatNo;			//상영관 좌석 번호
	int screenNo;		//상영관 번호
	String seatName;	//상영관 좌석 이름
	
	public int getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}

	public int getScreenNo() {
		return screenNo;
	}
 
	public void setScreenNo(int screenNo) {
		this.screenNo = screenNo;
	}

	public String getSeatName() {
		return seatName;
	}

	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}

	public SeatVO() {
		super();
	}
	
	
}
