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

public class AdminBookingAjax2Command implements Command {
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		System.out.println(req.getParameter("theaterNo"));
		int theaterNo = Integer.parseInt(req.getParameter("theaterNo"));
		
		ActionForward forward = new ActionForward();
		try {
			//TheaterService theaterService = TheaterService.getInstance();
			//List<ScreenVO> screenList =  theaterService.어떤것(theaterNo);
			List<ScreenVO> screenList = new ArrayList<ScreenVO>();
			ScreenVO screenVO = new ScreenVO();
			
			//임시용
			screenVO.setScreenName("1관");
			screenVO.setScreenNo(1);
			screenList.add(screenVO);
			screenVO = new ScreenVO();
			screenVO.setScreenName("2관");
			screenVO.setScreenNo(2);
			screenList.add(screenVO);
			screenVO = new ScreenVO();
			screenVO.setScreenName("3관");
			screenVO.setScreenNo(3);
			screenList.add(screenVO);
			
			req.setAttribute("screenList", screenList);
			
			forward.setPath("/admin/booking/adminBookingView2.jsp");
			forward.setRedirect(false);

		} catch (Exception e) {
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);

		}

		return forward;
	}
}
