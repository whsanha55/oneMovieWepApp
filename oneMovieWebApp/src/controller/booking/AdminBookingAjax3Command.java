package controller.booking;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.theater.ScheduleTurnVO;
import model.theater.TheaterService;

public class AdminBookingAjax3Command implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		int screenNo = Integer.parseInt(req.getParameter("screenNo"));
		String screenDate = req.getParameter("screenDate");

		ActionForward forward = new ActionForward();
		try {
		
			
			TheaterService theaterService = TheaterService.getInstance();
			List<ScheduleTurnVO> turnList = theaterService.retrieveScheduleTurn(screenNo, screenDate);
			req.setAttribute("turnList", turnList);
			forward.setPath("/admin/booking/adminBookingByTheaterViewTurn.jsp");
			forward.setRedirect(false);

		} catch (Exception e) {
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);

		}

		return forward;
	}

}
