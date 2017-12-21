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
			// �̰ɷ� �ϸ� �ȵǰ� ���� �޾ƿ;� �� jsp���� ������ ������� ��
			//int scheduleNo = Integer.parseInt(req.getParameter("no"));
			//service.deleteSchedule(scheduleNo);

			// ������ ��� ��ȸ�������� �̵�
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
