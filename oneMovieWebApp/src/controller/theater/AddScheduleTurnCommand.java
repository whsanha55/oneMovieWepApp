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

public class AddScheduleTurnCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		ActionForward forward = new ActionForward();

		try {

			TheaterService service = TheaterService.getInstance();
			List<ScreenScheduleVO> list = new ArrayList<ScreenScheduleVO>();
			// �̰ɷ� �ϸ� �ȵǰ� ���� �޾ƿ;� �� jsp���� ������ ������� ��
			service.addSchedule(list);

			// ������ ��� ��ȸ�������� �̵�
			forward.setPath("/admin/theater/listScheduleTurnForm.do");//do�� �����ؾ� ��
			forward.setRedirect(true);

		} catch (Exception e) {
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);
		}

		return forward;
	}

}
