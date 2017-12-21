package controller.theater;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.theater.ScreenScheduleVO;
import model.theater.TheaterService;

public class RemoveScheduleTurnCommand implements Command{
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		ActionForward forward = new ActionForward();

		try {

			TheaterService service = TheaterService.getInstance();
			List<ScreenScheduleVO> list = new ArrayList<ScreenScheduleVO>();
			// 이걸로 하면 안되고 따로 받아와야 함 jsp에서 데이터 보내줘야 함
			//int scheduleNo = Integer.parseInt(req.getParameter("no"));
			//service.deleteSchedule(scheduleNo);

			// 상영일정 목록 조회페이지로 이동
			forward.setPath("/admin/theater/listScheduleTurnForm.do");
			forward.setRedirect(true);

		} catch (Exception e) {
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);
		}

		return forward;
	}
}
