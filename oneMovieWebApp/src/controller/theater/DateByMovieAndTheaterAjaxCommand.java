package controller.theater;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.theater.TheaterService;

public class DateByMovieAndTheaterAjaxCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		int movieNo = -1;
		try {
			movieNo = Integer.parseInt(req.getParameter("movieNo"));
		} catch (Exception e) {
			
		}
		int theaterNo = -1;
		try {
			theaterNo = Integer.parseInt(req.getParameter("theaterNo"));
		} catch (Exception e) {
			
		}
		ActionForward forward = new ActionForward();
		try {
			TheaterService theaterService = TheaterService.getInstance();
			if(movieNo < 0 && theaterNo <0) {
				req.setAttribute("dateList", theaterService.retrieveDateAll());
			} else if (movieNo <0) {
				req.setAttribute("dateList", theaterService.retrieveDateByTheater(theaterNo));				
			} else if (theaterNo <0) {
				req.setAttribute("dateList", theaterService.retrieveDateByMovie(movieNo));
			} else {
				req.setAttribute("dateList", theaterService.retrieveDate(movieNo, theaterNo));				
			}
			System.out.println("ÀÛµ¿Áß");
			forward.setPath("/user/theater/dateByMovieAndTheaterView.jsp");
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
