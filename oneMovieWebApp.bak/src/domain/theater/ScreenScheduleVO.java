package domain.theater;

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
	
	public ScreenScheduleVO() {
		super();
	}
	
	
}
