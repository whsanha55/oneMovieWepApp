package domain.theater;

public class ScheduleTurnVO {
	int turnNo;			//회차번호
	String startTime;	//시작시간
	String endTime;		//종료시간
	int scheudleNo;		//상영번호
	String screenName;	//상영관 이름
	
	ScreenScheduleVO screenScheudleVO;
	public ScheduleTurnVO() {
		super();
	}
	
}
