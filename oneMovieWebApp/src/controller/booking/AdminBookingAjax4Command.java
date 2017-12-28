package controller.booking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.booking.BookingVO;
import model.booking.BookingService;

public class AdminBookingAjax4Command implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		String screenNoArray = req.getParameter("screenNoArray");
		String turnNoArray = req.getParameter("turnNoArray");
		
		ActionForward forward = new ActionForward();
		try {
			BookingService bookingService = BookingService.getInstance();

			//상영관으로 조회
			if(turnNoArray.equals("")) {
				String screenDate = req.getParameter("screenDate");
				
				ArrayList<Integer> screenNoList = new ArrayList<Integer>();
				for(String screenNo : screenNoArray.split(",")) {
					screenNoList.add(Integer.parseInt(screenNo));
				}
				List<BookingVO> bookingList = bookingService.retrieveBookingList(screenNoList,screenDate, 0, 100);
				req.setAttribute("bookingList",bookingList);
				
				
			//회차번호로 조회
			} else {
				
				ArrayList<Integer> turnNoList = new ArrayList<Integer>();
				for(String turnNo : turnNoArray.split(",")) {
					turnNoList.add(Integer.parseInt(turnNo));
				}
				List<BookingVO> bookingList = bookingService.retrieveBookingList(turnNoList, 0, 100);
				req.setAttribute("bookingList",bookingList);
				

			}
			forward.setPath("/admin/booking/adminBookingByTheaterViewResult.jsp");
			forward.setRedirect(false);

		} catch (Exception e) {
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);

		}

		return forward;
	}


}
