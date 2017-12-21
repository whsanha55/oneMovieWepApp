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

//상영일정을 추가하는 커멘드 클래스 구현
public class AddScheduleFormCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		ActionForward forward = new ActionForward();
		
		
		forward.setPath("/admin/theater/addScheduleForm.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
