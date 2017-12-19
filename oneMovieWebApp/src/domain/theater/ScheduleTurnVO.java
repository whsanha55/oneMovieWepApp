package domain.theater;

import java.util.ArrayList;
import java.util.List;

public class ScheduleTurnVO {
	int turnNo;			//ȸ����ȣ
	String startTime;	//���۽ð�
	String endTime;		//����ð�
	int scheudleNo;		//�󿵹�ȣ
	

	List<ScreenScheduleVO> schedules = new ArrayList<ScreenScheduleVO>();
	//ȸ������ ��ȸ��
	String theaterName;	//���� �̸�
	String screenName; //�󿵰� �̸�
	String movieName;	//��ȭ�̸� 
	
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
