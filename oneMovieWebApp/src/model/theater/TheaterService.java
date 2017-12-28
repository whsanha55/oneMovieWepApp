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
	//비지니스 로직을 담당
	//싱글톤으로 생성
	private static TheaterService instance = new TheaterService();
	
	private TheaterService() {
		
	}
	
	public static TheaterService getInstance() {
		return instance;
	}
	
	//검색조건(지점명,영화명)에 따라 상영일정을 조회한다.				2
	public List<ScreenScheduleVO> retriveScreenScheduleList(String keyfield, String keyword,int startRow, int endRow) 
		throws Exception{
		
		ScreenScheduleDAO screenScheduleDAO = ScreenScheduleDAO.getInstance();
		return screenScheduleDAO.selectScreenScheduleList(keyfield, keyword, startRow, endRow);
	}
	
	//지점을 조회한다.													3
	public List<TheaterVO>retrieveTheaterList() throws Exception{
		TheaterDAO theaterDAO = TheaterDAO.getInstance();
		return theaterDAO.SelectTheaterList();
	}
	
	//극장번호에 해당하는 상영관 목록을 조회한다.						4
	public List<ScreenVO>retrieveScreenList(int theaterNo) throws Exception{
		ScreenDAO screenDAO = ScreenDAO.getInstance();
		return screenDAO.SelectScreenList(theaterNo);
	}

	//상영관 번호에 해당하는 상영관 좌석을 조회한다.					5
	public List<SeatVO>retrieveSeatList(int screenNo)throws Exception{
		SeatDAO seatDAO = SeatDAO.getInstance();
		return seatDAO.selectSeatList(screenNo);
	}
	
	//1.상영일정을 등록한다.
	//2.회차일정을 등록한다.
	//3.트랜잭션을 수행한다.										6
	public void addSchedule(List<ScreenScheduleVO> list)throws Exception{
		Connection conn = null;
		List<ScheduleTurnVO>turnList = new ArrayList<ScheduleTurnVO>();
		for(ScreenScheduleVO schedule : list) {
			turnList = schedule.getTurns();
		}
		try {
			conn = DBConn.getConnection();
			// tx.begin(트랜젝션 시작)
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
	//1.상영번호에 해당하는 회차를 삭제한다.
	//2.상영번호에 해당하는 상영일정을 삭제한다.
	//3.트랜잭션을 수행한다.								7
	public void deleteSchedule(int scheduleNo)throws Exception{
		Connection conn = null;
		try {
			conn = DBConn.getConnection();
			// tx.begin(트랜젝션 시작)
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
	
	//상영번호에 해당하는 회차일정 목록을 조회한다.					8
	public List<ScheduleTurnVO> retrieveScheduleTurnList(int scheduleNo)throws Exception{
		ScheduleTurnDAO scheduleTurnDAO = ScheduleTurnDAO.getInstance();
		return scheduleTurnDAO.selectScheduleTurnList(scheduleNo);
	}
	
	//상영중인 영화제목을 보여준다.									9
	public List<MovieVO>retrieveMovieAll()throws Exception{
		DateTheaterMovieDAO dateTheaterMovieDAO = DateTheaterMovieDAO.getInstance();
		return dateTheaterMovieDAO.selectMovieAll();
	}
	
	//모든 지점 목록을 보여준다.									10
	public List<TheaterVO>retrieveTheaterAll()throws Exception{
		DateTheaterMovieDAO dateTheaterMovieDAO = DateTheaterMovieDAO.getInstance();
		return dateTheaterMovieDAO.selectTheaterAll();
	}
	
	//상영 일정을 보여준다.											11
	public List<ScreenScheduleVO>retrieveDateAll()throws Exception{
		DateTheaterMovieDAO dateTheaterMovieDAO = DateTheaterMovieDAO.getInstance();
		return dateTheaterMovieDAO.selectDateAll();
	}
	
	//극장번호에 해당하는 영화목록을 보여준다.						12
	public List<MovieVO>retrieveMovieByTheater(int theaterNo)throws Exception{
		DateTheaterMovieDAO dateTheaterMovieDAO = DateTheaterMovieDAO.getInstance();
		return dateTheaterMovieDAO.selectMovieByTheater(theaterNo);
	}
	
	//상영 날짜에 해당하는 영화목록을 보여준다.						13
	public List<MovieVO>retrieveMovieByDate(String date)throws Exception{
		DateTheaterMovieDAO dateTheaterMovieDAO = DateTheaterMovieDAO.getInstance();
		return dateTheaterMovieDAO.selectMovieByDate(date);
	}
	
	//상영 날짜에 해당하는 지점목록을 보여준다.						14
	public List<TheaterVO>retrieveTheaterByDate(String date)throws Exception{
		DateTheaterMovieDAO dateTheaterMovieDAO = DateTheaterMovieDAO.getInstance();
		return dateTheaterMovieDAO.selectTheaterByDate(date);
	}
	
	//영화번호에 해당하는 지점목록을 보여준다.						15
	public List<TheaterVO>retrieveTheaterByMovie(int movieNo)throws Exception{
		DateTheaterMovieDAO dateTheaterMovieDAO = DateTheaterMovieDAO.getInstance();
		return dateTheaterMovieDAO.selectTheaterByMovie(movieNo);
	}
	
	//지점번호에 해당하는 상영날짜 리스트를 보여준다.				16
	public List<ScreenScheduleVO>retrieveDateByTheater(int theaterNo)throws Exception{
		DateTheaterMovieDAO dateTheaterMovieDAO = DateTheaterMovieDAO.getInstance();
		return dateTheaterMovieDAO.selectDateByTheater(theaterNo);
	}
	
	//영화번호에 해당하는 상영날짜 리스트를 보여준다.				17
	public List<ScreenScheduleVO>retrieveDateByMovie(int movieNo)throws Exception{
		DateTheaterMovieDAO dateTheaterMovieDAO = DateTheaterMovieDAO.getInstance();
		return dateTheaterMovieDAO.selectDateByMovie(movieNo);
	}
	
	//지점번호와 날짜에 해당하는 영화리스트를 보여준다.				18
	public List<MovieVO>retrieveMovie(int theaterNo,String date) throws Exception{
		DateTheaterMovieDAO dateTheaterMovieDAO = DateTheaterMovieDAO.getInstance();
		return dateTheaterMovieDAO.selectMovie(theaterNo, date);
	}
	
	//영화번호와 날짜에 해당하는 지점 리스트를 보여준다.			19
	public List<TheaterVO>retrieveTheater(int movieNo,String date)throws Exception{
		DateTheaterMovieDAO dateTheaterMovieDAO = DateTheaterMovieDAO.getInstance();
		return dateTheaterMovieDAO.selectTheater(movieNo, date);
	}
	
	//영화번호와 지점에 해당하는 상영날짜 리스트를 보여준다.		20
	public List<ScreenScheduleVO>retrieveDate(int movieNo,int theaterNo)throws Exception{
		DateTheaterMovieDAO dateTheaterMovieDAO = DateTheaterMovieDAO.getInstance();
		return dateTheaterMovieDAO.selectDate(movieNo, theaterNo);
	}
	//지점번호,영화번호 및 상영 날짜에 해당하는 회차 목록을 보여준다.	21
	public List<ScreenScheduleVO>retrieveScheduleTurnByTheater(int theaterNo,String screenDate,int movieNo)throws Exception{
		ScheduleTurnDAO scheduleTurnDao = ScheduleTurnDAO.getInstance();
		return scheduleTurnDao.selectScheduleTurnByTheater(theaterNo, screenDate, movieNo);
	}

	//상영관번호,영화번호 및 상영 날짜에 해당하는 회차 목록을 보여준다.	22
	public List<ScreenScheduleVO>retrieveScheduleTurnByScreen(int screenNo,String screenDate,int movieNo)throws Exception{
		ScheduleTurnDAO scheduleTurnDao = ScheduleTurnDAO.getInstance();
		return scheduleTurnDao.selectScheduleTurnByScreen(screenNo, screenDate, movieNo);
	} 
	
	//총 게시글 수를 구한다.
	public int retrieveTotalPost() throws Exception{
		ScheduleTurnDAO scheduleTurnDao = ScheduleTurnDAO.getInstance();
		return scheduleTurnDao.selectTotalPost();
	}
	public List<ScreenScheduleVO> retrieveTurn(int movieNo, int theaterNo, String date) throws Exception {
		DateTheaterMovieDAO dateTheaterMovieDAO = DateTheaterMovieDAO.getInstance();
		return dateTheaterMovieDAO.selectTurn(movieNo, theaterNo, date);
	}
	
	//booking에서 사용
	public List<ScheduleTurnVO> retrieveScheduleTurn(int screenNo, String screenDate )throws Exception{
		ScheduleTurnDAO scheduleTurnDAO = ScheduleTurnDAO.getInstance();
		return scheduleTurnDAO.selectScheduleTurn(screenNo, screenDate);
	}
}//TheaterService
