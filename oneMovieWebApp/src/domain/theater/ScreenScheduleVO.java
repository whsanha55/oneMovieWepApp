package domain.theater;

public class ScreenScheduleVO {
	
	int scheduleNo;		//상영번호
	int screenNo;		//상영관 번호
	String screenDate;	//상영 날짜
	int movieNo;		//영화번호
	//상영일정 조회시
	String keyfield;	//검색조건(지점명,영화명)
	String keywordl;	//검색어
	int startRow;		//시작줄
	int endrow;			//마지막줄
	ScheduleTurnVO	sceduleTurnVO;
	int turnNo;			//회차번호
	String startTime;	//시작시간
	String endTime;		//종료시간
	
	public ScreenScheduleVO() {
		super();
	}
	
	
}
