package controller.booking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.theater.ScreenVO;
import domain.theater.TheaterVO;
import model.theater.TheaterService;

public class AdminBookingAjax2Command implements Command {
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		int theaterNo = Integer.parseInt(req.getParameter("theaterNo"));
		ActionForward forward = new ActionForward();
		try {
		
			
			TheaterService theaterService = TheaterService.getInstance();
			List<ScreenVO> screenList = theaterService.retrieveScreenList(theaterNo);
			req.setAttribute("screenList", screenList);
			forward.setPath("/admin/booking/adminBookingByTheaterView.jsp");
			forward.setRedirect(false);

		} catch (Exception e) {
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);

		}

		return forward;
	}
}
