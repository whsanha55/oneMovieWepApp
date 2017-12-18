package domain.theater;

import java.util.ArrayList;
import java.util.List;


public class ScreenScheduleVO {
	
	int scheduleNo;		//�󿵹�ȣ
	int screenNo;		//�󿵰� ��ȣ
	String screenDate;	//�� ��¥
	int movieNo;		//��ȭ��ȣ
	//������ ��ȸ��
	String keyfield;	//�˻�����(������,��ȭ��)
	String keywordl;	//�˻���
	int startRow;		//������
	int endrow;			//��������
	ScheduleTurnVO	sceduleTurnVO;
	int turnNo;			//ȸ����ȣ
	String startTime;	//���۽ð�
	String endTime;		//����ð�
	String theaterName;	//���� �̸�
	String movieName;	//��ȭ�̸�
	private List<ScheduleTurnVO> scheduleTurnVO;
	
	public List<ScheduleTurnVO> getScheduleTurnVO() {
		return scheduleTurnVO;
	}

	public void setScheduleTurnVO(List<ScheduleTurnVO> scheduleTurnVO) {
		this.scheduleTurnVO = scheduleTurnVO;
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

	public String getKeyfield() {
		return keyfield;
	}

	public void setKeyfield(String keyfield) {
		this.keyfield = keyfield;
	}

	public String getKeywordl() {
		return keywordl;
	}

	public void setKeywordl(String keywordl) {
		this.keywordl = keywordl;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndrow() {
		return endrow;
	}

	public void setEndrow(int endrow) {
		this.endrow = endrow;
	}

	public ScheduleTurnVO getSceduleTurnVO() {
		return sceduleTurnVO;
	}

	public void setSceduleTurnVO(ScheduleTurnVO sceduleTurnVO) {
		this.sceduleTurnVO = sceduleTurnVO;
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
	
	public void addScheduleTurnVO(ScheduleTurnVO scheduleTurnVO) {
		this.scheduleTurnVO.add(scheduleTurnVO);
	}
	
	
}
