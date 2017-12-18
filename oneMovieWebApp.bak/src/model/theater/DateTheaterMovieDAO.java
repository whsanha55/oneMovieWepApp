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
	//영화조회					11
	public List<MovieVO> selectMovieAll()throws Exception{
		List<MovieVO>list = null;
		
		return list;
		
	}
	//지점조회					12
	public List<TheaterVO> selectTheaterAll()throws Exception{
		List<TheaterVO>	list = null;
		
		return list;
		
	}
	//날짜조회					13
	public String[] selectDateAll()throws Exception{
		
		return null;
	}
	
	//지점->영화 조회			14
	public List<MovieVO> selectMovieByTheater(int theaterNo) throws Exception{
		List<MovieVO>list =null;
		
		return list;
	}

	// 날짜->영화 조회 15
	public List<MovieVO> selectMovieByDate(String date) throws Exception {
		List<MovieVO> list = null;

		return list;
	}

	//날짜->지점 조회			16
	public List<TheaterVO> selectTheaterByDate(String date) throws Exception{
		List<TheaterVO>list = null;
		
		return list;
	}
	//영화->지점 조회			17
	public List<TheaterVO> selectTheaterByMovie(int movieNo) throws Exception{
		List<TheaterVO>list = null;

		return list;
	}
	
	//지점->날짜 조회			18
	public String[] selectDateByTheater(int theaterNO) throws Exception{
		
		
		return null;
	}
	//영화->날짜 조회			19
	public String[] selectDateByMovie(int movieNo) throws Exception{
		
		return null;
	}
	
	//지점,날짜 -> 영화조회		20
	public List<MovieVO>selectMovie(int theaterNo, String date) throws Exception{
		List<MovieVO>list = null;
		
		return list;
		
	}
	//영화,날짜 -> 지점조회		21
	public List<TheaterVO> selectTheater(int movieNo,String date) throws Exception{
		List<TheaterVO>list = null;
		
		return list;
	}
	//영화,지점 -> 날짜조회		22
	public List<TheaterVO> selectDate(int movieNo, int theaterNo) throws Exception{
		List<TheaterVO>list = null;

		return list;
	}
}
