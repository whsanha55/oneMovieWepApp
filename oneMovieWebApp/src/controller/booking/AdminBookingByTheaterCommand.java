package controller.booking;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.theater.TheaterVO;
import model.theater.TheaterService;

public class AdminBookingByTheaterCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		ActionForward forward = new ActionForward();
		
		try {
			TheaterService theaterService = TheaterService.getInstance();
			List<TheaterVO> theaterList = theaterService.retrieveTheaterList();
			req.setAttribute("theaterList", theaterList);
			forward.setPath("/admin/layoutAdmin.jsp?article=/admin/booking/adminBookingByTheater.jsp");
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
