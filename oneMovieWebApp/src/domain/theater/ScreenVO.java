package domain.theater;

public class ScreenVO {
	int screenNo;		//상영관번호
	int theaterNo;		//극장번호
	String screenName;	//상영관이름
	
	
	public int getScreenNo() {
		return screenNo;
	}


	public void setScreenNo(int screenNo) {
		this.screenNo = screenNo;
	}


	public int getTheaterNo() {
		return theaterNo;
	}


	public void setTheaterNo(int theaterNo) {
		this.theaterNo = theaterNo;
	}


	public String getScreenName() {
		return screenName;
	}


	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}


	public ScreenVO() {
		super();
	}
	
	
	
}
