package model.theater;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import conn.DBConn;
import domain.movie.MovieVO;
import domain.theater.ScheduleTurnVO;
import domain.theater.ScreenScheduleVO;
import domain.theater.ScreenVO;
import domain.theater.SeatVO;
import domain.theater.TheaterVO;

public class TheaterService {
	//�����Ͻ� ������ ���
	//�̱������� ����
	private static TheaterService instance = new TheaterService();
	
	private TheaterService() {
		
	}
	
	public static TheaterService getInstance() {
		return instance;
	}
	
	//�˻�����(������,��ȭ��)�� ���� �������� ��ȸ�Ѵ�.				2
	public List<ScreenScheduleVO> retriveScreenScheduleList(String keyfield, String keyword,int startRow, int endRow) 
		throws Exception{
		
		ScreenScheduleDAO screenScheduleDAO = ScreenScheduleDAO.getInstance();
		return screenScheduleDAO.selectScreenScheduleList(keyfield, keyword, startRow, endRow);
	}
	
	//������ ��ȸ�Ѵ�.													3
	public List<TheaterVO>retrieveTheaterList() throws Exception{
		TheaterDAO theaterDAO = TheaterDAO.getInstance();
		return theaterDAO.SelectTheaterList();
	}
	
	//�����ȣ�� �ش��ϴ� �󿵰� ����� ��ȸ�Ѵ�.						4
	public List<ScreenVO>retrieveScreenList(int theaterNo) throws Exception{
		ScreenDAO screenDAO = ScreenDAO.getInstance();
		return screenDAO.SelectScreenList(theaterNo);
	}

	//�󿵰� ��ȣ�� �ش��ϴ� �󿵰� �¼��� ��ȸ�Ѵ�.					5
	public List<SeatVO>retrieveSeatList(int screenNo)throws Exception{
		SeatDAO seatDAO = SeatDAO.getInstance();
		return seatDAO.selectSeatList(screenNo);
	}
	
	//1.�������� ����Ѵ�.
	//2.ȸ�������� ����Ѵ�.
	//3.Ʈ������� �����Ѵ�.										6
	public void addSchedule(List<ScreenScheduleVO> list)throws Exception{
		Connection conn = null;
		List<ScheduleTurnVO>turnList = new ArrayList<ScheduleTurnVO>();
		for(ScreenScheduleVO schedule : list) {
			turnList = schedule.getTurns();
		}
		try {
			conn = DBConn.getConnection();
			// tx.begin(Ʈ������ ����)
			conn.setAutoCommit(false);
			
			
			ScreenScheduleDAO screenScheduleDAO = ScreenScheduleDAO.getInstance();
			int scheduleNo = screenScheduleDAO.InsertScreenSchedule(list, conn);
			System.out.println("test");
			ScheduleTurnDAO scheduleTurnDAO = ScheduleTurnDAO.getInstance();
			scheduleTurnDAO.insertTurn(turnList, conn, scheduleNo);
			
			
			conn.commit();
			
		} catch (Exception e) {
			conn.rollback();
			throw e;
		}finally {
			if (conn != null)conn.close();
		}
	}
	//1.�󿵹�ȣ�� �ش��ϴ� ȸ���� �����Ѵ�.
	//2.�󿵹�ȣ�� �ش��ϴ� �������� �����Ѵ�.
	//3.Ʈ������� �����Ѵ�.								7
	public void deleteSchedule(int scheduleNo)throws Exception{
		Connection conn = null;
		try {
			conn = DBConn.getConnection();
			// tx.begin(Ʈ������ ����)
			conn.setAutoCommit(false);
			
			ScheduleTurnDAO scheduleTurnDAO = ScheduleTurnDAO.getInstance();
			scheduleTurnDAO.removeScheduleTurn(scheduleNo, conn);
			
			ScreenScheduleDAO screenScheduleDAO = ScreenScheduleDAO.getInstance();
			screenScheduleDAO.removeScreenSchedule(scheduleNo, conn);
			
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		}finally {
			if (conn != null)conn.close();
		}
	}
	
	//�󿵹�ȣ�� �ش��ϴ� ȸ������ ����� ��ȸ�Ѵ�.					8
	public List<ScheduleTurnVO> retrieveScheduleTurnList(int scheduleNo)throws Exception{
		ScheduleTurnDAO scheduleTurnDAO = ScheduleTurnDAO.getInstance();
		return scheduleTurnDAO.selectScheduleTurnList(scheduleNo);
	}
	
	//������ ��ȭ������ �����ش�.									9
	public List<MovieVO>retrieveMovieAll()throws Exception{
		DateTheaterMovieDAO dateTheaterMovieDAO = DateTheaterMovieDAO.getInstance();
		return dateTheaterMovieDAO.selectMovieAll();
	}
	
	//��� ���� ����� �����ش�.									10
	public List<TheaterVO>retrieveTheaterAll()throws Exception{
		DateTheaterMovieDAO dateTheaterMovieDAO = DateTheaterMovieDAO.getInstance();
		return dateTheaterMovieDAO.selectTheaterAll();
	}
	
	//�� ������ �����ش�.											11
	public List<ScreenScheduleVO>retrieveDateAll()throws Exception{
		DateTheaterMovieDAO dateTheaterMovieDAO = DateTheaterMovieDAO.getInstance();
		return dateTheaterMovieDAO.selectDateAll();
	}
	
	//�����ȣ�� �ش��ϴ� ��ȭ����� �����ش�.						12
	public List<MovieVO>retrieveMovieByTheater(int theaterNo)throws Exception{
		DateTheaterMovieDAO dateTheaterMovieDAO = DateTheaterMovieDAO.getInstance();
		return dateTheaterMovieDAO.selectMovieByTheater(theaterNo);
	}
	
	//�� ��¥�� �ش��ϴ� ��ȭ����� �����ش�.						13
	public List<MovieVO>retrieveMovieByDate(String date)throws Exception{
		DateTheaterMovieDAO dateTheaterMovieDAO = DateTheaterMovieDAO.getInstance();
		return dateTheaterMovieDAO.selectMovieByDate(date);
	}
	
	//�� ��¥�� �ش��ϴ� ��������� �����ش�.						14
	public List<TheaterVO>retrieveTheaterByDate(String date)throws Exception{
		DateTheaterMovieDAO dateTheaterMovieDAO = DateTheaterMovieDAO.getInstance();
		return dateTheaterMovieDAO.selectTheaterByDate(date);
	}
	
	//��ȭ��ȣ�� �ش��ϴ� ��������� �����ش�.						15
	public List<TheaterVO>retrieveTheaterByMovie(int movieNo)throws Exception{
		DateTheaterMovieDAO dateTheaterMovieDAO = DateTheaterMovieDAO.getInstance();
		return dateTheaterMovieDAO.selectTheaterByMovie(movieNo);
	}
	
	//������ȣ�� �ش��ϴ� �󿵳�¥ ����Ʈ�� �����ش�.				16
	public List<ScreenScheduleVO>retrieveDateByTheater(int theaterNo)throws Exception{
		DateTheaterMovieDAO dateTheaterMovieDAO = DateTheaterMovieDAO.getInstance();
		return dateTheaterMovieDAO.selectDateByTheater(theaterNo);
	}
	
	//��ȭ��ȣ�� �ش��ϴ� �󿵳�¥ ����Ʈ�� �����ش�.				17
	public List<ScreenScheduleVO>retrieveDateByMovie(int movieNo)throws Exception{
		DateTheaterMovieDAO dateTheaterMovieDAO = DateTheaterMovieDAO.getInstance();
		return dateTheaterMovieDAO.selectDateByMovie(movieNo);
	}
	
	//������ȣ�� ��¥�� �ش��ϴ� ��ȭ����Ʈ�� �����ش�.				18
	public List<MovieVO>retrieveMovie(int theaterNo,String date) throws Exception{
		DateTheaterMovieDAO dateTheaterMovieDAO = DateTheaterMovieDAO.getInstance();
		return dateTheaterMovieDAO.selectMovie(theaterNo, date);
	}
	
	//��ȭ��ȣ�� ��¥�� �ش��ϴ� ���� ����Ʈ�� �����ش�.			19
	public List<TheaterVO>retrieveTheater(int movieNo,String date)throws Exception{
		DateTheaterMovieDAO dateTheaterMovieDAO = DateTheaterMovieDAO.getInstance();
		return dateTheaterMovieDAO.selectTheater(movieNo, date);
	}
	
	//��ȭ��ȣ�� ������ �ش��ϴ� �󿵳�¥ ����Ʈ�� �����ش�.		20
	public List<ScreenScheduleVO>retrieveDate(int movieNo,int theaterNo)throws Exception{
		DateTheaterMovieDAO dateTheaterMovieDAO = DateTheaterMovieDAO.getInstance();
		return dateTheaterMovieDAO.selectDate(movieNo, theaterNo);
	}
	//������ȣ,��ȭ��ȣ �� �� ��¥�� �ش��ϴ� ȸ�� ����� �����ش�.	21
	public List<ScreenScheduleVO>retrieveScheduleTurnByTheater(int theaterNo,String screenDate,int movieNo)throws Exception{
		ScheduleTurnDAO scheduleTurnDao = ScheduleTurnDAO.getInstance();
		return scheduleTurnDao.selectScheduleTurnByTheater(theaterNo, screenDate, movieNo);
	}

	//�󿵰���ȣ,��ȭ��ȣ �� �� ��¥�� �ش��ϴ� ȸ�� ����� �����ش�.	22
	public List<ScreenScheduleVO>retrieveScheduleTurnByScreen(int screenNo,String screenDate,int movieNo)throws Exception{
		ScheduleTurnDAO scheduleTurnDao = ScheduleTurnDAO.getInstance();
		return scheduleTurnDao.selectScheduleTurnByScreen(screenNo, screenDate, movieNo);
	} 
	
	//�� �Խñ� ���� ���Ѵ�.
	public int retrieveTotalPost() throws Exception{
		ScheduleTurnDAO scheduleTurnDao = ScheduleTurnDAO.getInstance();
		return scheduleTurnDao.selectTotalPost();
	}
	public List<ScreenScheduleVO> retrieveTurn(int movieNo, int theaterNo, String date) throws Exception {
		DateTheaterMovieDAO dateTheaterMovieDAO = DateTheaterMovieDAO.getInstance();
		return dateTheaterMovieDAO.selectTurn(movieNo, theaterNo, date);
	}
	
	//booking���� ���
	public List<ScheduleTurnVO> retrieveScheduleTurn(int screenNo, String screenDate )throws Exception{
		ScheduleTurnDAO scheduleTurnDAO = ScheduleTurnDAO.getInstance();
		return scheduleTurnDAO.selectScheduleTurn(screenNo, screenDate);
	}
}//TheaterService
