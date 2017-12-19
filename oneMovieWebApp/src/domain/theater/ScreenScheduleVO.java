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
	String movieName;	//영화이름
	
	public void addScheduleTurnVO(ScheduleTurnVO scheduleTurnVO) {
		turns.add(scheduleTurnVO);
	}
	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getTheaterName() {
		return theaterName;
	}

	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}

	public ScreenScheduleVO() {
		super();
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

	
	
}
