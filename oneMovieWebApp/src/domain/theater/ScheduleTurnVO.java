package domain.theater;

public class ScheduleTurnVO {
	int turnNo;			//ȸ����ȣ
	String startTime;	//���۽ð�
	String endTime;		//����ð�
	int scheudleNo;		//�󿵹�ȣ
	String screenName;	//�󿵰� �̸�
	
	ScreenScheduleVO screenScheudleVO;
	
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
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public ScreenScheduleVO getScreenScheudleVO() {
		return screenScheudleVO;
	}
	public void setScreenScheudleVO(ScreenScheduleVO screenScheudleVO) {
		this.screenScheudleVO = screenScheudleVO;
	}
	
	
}
