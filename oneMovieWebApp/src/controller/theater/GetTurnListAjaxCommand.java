package controller.theater;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.theater.ScreenScheduleVO;
import model.theater.TheaterService;

public class GetTurnListAjaxCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		int movieNo = Integer.parseInt(req.getParameter("movieNo"));
		int theaterNo = Integer.parseInt(req.getParameter("theaterNo"));
		String date = req.getParameter("date");
		
		ActionForward forward = new ActionForward();
		try {
			TheaterService theaterService = TheaterService.getInstance();
			List<ScreenScheduleVO> scheduleList = theaterService.retrieveTurn(movieNo, theaterNo, date);
			req.setAttribute("scheduleList", scheduleList);
			forward.setPath("/user/theater/getTurnListView.jsp");
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
