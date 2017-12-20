
package controller.booking;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.movie.MovieVO;
import domain.theater.TheaterVO;
import model.theater.DateTheaterMovieDAO;

public class memberBookingCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		ActionForward forward = new ActionForward();
		try {
			//수정 필요
			DateTheaterMovieDAO dateTheaterMovieDAO = DateTheaterMovieDAO.getInstance();
			List<MovieVO> movieList =dateTheaterMovieDAO.selectMovieAll();
			List<TheaterVO> theaterList = dateTheaterMovieDAO.selectTheaterAll();
			
			req.setAttribute("movieList", movieList);
			req.setAttribute("theaterList", theaterList);
			forward.setPath("/memberBooking1.jsp");
			forward.setRedirect(false);
			return forward;
		} catch (Exception e) {
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);
			return forward;
		}
		
	}

}
