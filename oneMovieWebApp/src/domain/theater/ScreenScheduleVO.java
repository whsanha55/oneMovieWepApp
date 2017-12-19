package domain.theater;

import java.util.ArrayList;
import java.util.List;

public class ScreenScheduleVO {
	
	int scheduleNo;		//상영번호
	int screenNo;		//상영관 번호
	String screenDate;	//상영 날짜
	int movieNo;		//영화번호
	List<ScheduleTurnVO> turns = new ArrayList<ScheduleTurnVO>();
	//상영일정 조회시

	String theaterName;	//지점 이름
	String movieTitle;	//영화이름
	String screenName;	//상영관 이름
 

	public String getScreenName() {
		return screenName;
	}


	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}


	public void addScheduleTurnVO(ScheduleTurnVO scheduleTurnVO) {
		turns.add(scheduleTurnVO);
	}


	public int getScheduleNo() {
		return scheduleNo;
	}


	public void setScheduleNo(int scheduleNo) {
		this.scheduleNo = scheduleNo;
	}


	public int getScreenNo() {
		return screenNo;
	}


	public void setScreenNo(int screenNo) {
		this.screenNo = screenNo;
	}


	public String getScreenDate() {
		return screenDate;
	}


	public void setScreenDate(String screenDate) {
		this.screenDate = screenDate;
	}


	public int getMovieNo() {
		return movieNo;
	}


	public void setMovieNo(int movieNo) {
		this.movieNo = movieNo;
	}


	public List<ScheduleTurnVO> getTurns() {
		return turns;
	}


	public void setTurns(List<ScheduleTurnVO> turns) {
		this.turns = turns;
	}


	public String getTheaterName() {
		return theaterName;
	}


	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}


	public String getMovieTitle() {
		return movieTitle;
	}


	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}




}
