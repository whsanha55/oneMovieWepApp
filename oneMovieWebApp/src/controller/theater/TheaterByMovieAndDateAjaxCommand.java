package controller.theater;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.theater.TheaterService;

public class TheaterByMovieAndDateAjaxCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		int movieNo = -1;
		try {
			movieNo = Integer.parseInt(req.getParameter("movieNo"));
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
		System.out.println("theaterby.." + date);
		ActionForward forward = new ActionForward();
		try {
			TheaterService theaterService = TheaterService.getInstance();
			if(movieNo < 0 && date == null) {
				req.setAttribute("theaterList", theaterService.retrieveTheaterAll());
				System.out.println("1번쨰 movieNo : " + movieNo + "date : " + date );
			} else if(movieNo < 0 ) {
				req.setAttribute("theaterList", theaterService.retrieveTheaterByDate(date));
				System.out.println("2번쨰 movieNo : " + movieNo + "date : " + date );
			} else if(date == null) {
				req.setAttribute("theaterList", theaterService.retrieveTheaterByMovie(movieNo));
				System.out.println("3번쨰 movieNo : " + movieNo + "date : " + date );
			} else {
				req.setAttribute("theaterList", theaterService.retrieveTheater(movieNo, date));
				System.out.println("4번쨰 movieNo : " + movieNo + "date : " + date );
			}
			forward.setPath("/user/theater/theaterByMovieAndDateView.jsp");
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
