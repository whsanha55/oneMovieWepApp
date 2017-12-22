package controller.theater;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.theater.TheaterService;

public class MovieByTheaterAndDateAjaxCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		int theaterNo = -1;
		try {
			theaterNo = Integer.parseInt(req.getParameter("theaterNo"));
		} catch (Exception e) {
			
		}
		String date = req.getParameter("date");
		try {
			if(date.equals("")) {
				date = null;
			}
		} catch (Exception e) {
			date = null;
		}
		ActionForward forward = new ActionForward();
		try {
			TheaterService theaterService = TheaterService.getInstance();
			if(theaterNo < 0 && date == null) {
				req.setAttribute("movieList", theaterService.retrieveMovieAll());
			} else if(theaterNo < 0) {
				req.setAttribute("movieList", theaterService.retrieveMovieByDate(date));
			} else if(date == null) {
				req.setAttribute("movieList", theaterService.retrieveMovieByTheater(theaterNo));
			} else {
				req.setAttribute("movieList", theaterService.retrieveMovie(theaterNo, date));
			}
			forward.setPath("/user/theater/movieByTheaterAndDateView.jsp");
			forward.setRedirect(false);
			return forward;
		} catch (Exception e) {
			req.setAttribute("exception",e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);
			return forward;
			
		}
	}

}
