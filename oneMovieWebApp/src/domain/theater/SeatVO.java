package domain.theater;

public class SeatVO {
	int seatNo;			//�󿵰� �¼� ��ȣ
	int screenNo;		//�󿵰� ��ȣ
	String seatName;	//�󿵰� �¼� �̸�
	
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
