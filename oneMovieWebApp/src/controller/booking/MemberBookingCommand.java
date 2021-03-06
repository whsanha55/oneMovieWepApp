
package controller.booking;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.movie.MovieVO;
import domain.theater.ScreenScheduleVO;
import domain.theater.TheaterVO;
import model.theater.DateTheaterMovieDAO;
import model.theater.TheaterService;

public class MemberBookingCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		ActionForward forward = new ActionForward();
		
		try {
			
			TheaterService theaterService = TheaterService.getInstance();
			
			List<MovieVO> movieList =theaterService.retrieveMovieAll();
			List<TheaterVO> theaterList = theaterService.retrieveTheaterAll();
			List<ScreenScheduleVO> scheduleList = theaterService.retrieveDateAll();
			req.setAttribute("movieList", movieList);
			req.setAttribute("theaterList", theaterList);
			req.setAttribute("scheduleList", scheduleList);
			req.setAttribute("movieNo", req.getParameter("movieNo"));
			req.setAttribute("theaterNo", req.getParameter("theaterNo"));
			req.setAttribute("screenDate", req.getParameter("screenDate"));
			forward.setPath("layoutUser.jsp?article=/user/booking/memberBooking.jsp");
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
