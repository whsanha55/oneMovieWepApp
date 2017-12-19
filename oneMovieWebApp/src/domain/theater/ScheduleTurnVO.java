package domain.theater;

import java.util.ArrayList;
import java.util.List;

public class ScheduleTurnVO {
	int turnNo;			//회차번호
	String startTime;	//시작시간
	String endTime;		//종료시간
	int scheudleNo;		//상영번호
	

	List<ScreenScheduleVO> schedules = new ArrayList<ScreenScheduleVO>();
	//회차일정 조회시
	String theaterName;	//지점 이름
	String screenName; //상영관 이름
	String movieName;	//영화이름 
	
	public void addScreenScheduleVO(ScreenScheduleVO screenScheduleVO) {
		schedules.add(screenScheduleVO);
	}
	 
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public String getTheaterName() {
		return theaterName;
	}
	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	

	public ScheduleTurnVO() {
		super();
	}
	public int getTurnNo() {
		return turnNo;
	}
	public void setTurnNo(int turnNo) {
		this.turnNo = turnNo;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getScheudleNo() {
		return scheudleNo;
	}
	public void setScheudleNo(int scheudleNo) {
		this.scheudleNo = scheudleNo;
	}
	public List<ScreenScheduleVO> getSchedules() {
		return schedules;
	}
	public void setSchedules(List<ScreenScheduleVO> schedules) {
		this.schedules = schedules;
	}

}
