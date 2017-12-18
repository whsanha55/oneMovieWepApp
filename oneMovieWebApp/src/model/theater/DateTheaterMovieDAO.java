package model.theater;

import java.util.List;

import domain.movie.MovieVO;
import domain.theater.TheaterVO;

public class DateTheaterMovieDAO {
	private DateTheaterMovieDAO() {

	}

	private static DateTheaterMovieDAO instance = new DateTheaterMovieDAO();

	public static DateTheaterMovieDAO getInstance() {
		return instance;
	}
	//��ȭ��ȸ					11
	public List<MovieVO> selectMovieAll()throws Exception{
		List<MovieVO>list = null;
		
		return list;
		
	}
	//������ȸ					12
	public List<TheaterVO> selectTheaterAll()throws Exception{
		List<TheaterVO>	list = null;
		
		return list;
		
	}
	//��¥��ȸ					13
	public String[] selectDateAll()throws Exception{
		
		return null;
	}
	
	//����->��ȭ ��ȸ			14
	public List<MovieVO> selectMovieByTheater(int theaterNo) throws Exception{
		List<MovieVO>list =null;
		
		return list;
	}

	// ��¥->��ȭ ��ȸ 15
	public List<MovieVO> selectMovieByDate(String date) throws Exception {
		List<MovieVO> list = null;

		return list;
	}

	//��¥->���� ��ȸ			16
	public List<TheaterVO> selectTheaterByDate(String date) throws Exception{
		List<TheaterVO>list = null;
		
		return list;
	}
	//��ȭ->���� ��ȸ			17
	public List<TheaterVO> selectTheaterByMovie(int movieNo) throws Exception{
		List<TheaterVO>list = null;

		return list;
	}
	
	//����->��¥ ��ȸ			18
	public String[] selectDateByTheater(int theaterNO) throws Exception{
		
		
		return null;
	}
	//��ȭ->��¥ ��ȸ			19
	public String[] selectDateByMovie(int movieNo) throws Exception{
		
		return null;
	}
	
	//����,��¥ -> ��ȭ��ȸ		20
	public List<MovieVO>selectMovie(int theaterNo, String date) throws Exception{
		List<MovieVO>list = null;
		
		return list;
		
	}
	//��ȭ,��¥ -> ������ȸ		21
	public List<TheaterVO> selectTheater(int movieNo,String date) throws Exception{
		List<TheaterVO>list = null;
		
		return list;
	}
	//��ȭ,���� -> ��¥��ȸ		22
	public List<TheaterVO> selectDate(int movieNo, int theaterNo) throws Exception{
		List<TheaterVO>list = null;

		return list;
	}
}
