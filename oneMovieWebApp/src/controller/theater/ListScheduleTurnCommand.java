package controller.theater;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.PagingVO;
import domain.theater.ScreenScheduleVO;
import model.theater.TheaterService;

//�� ���� ��� ��ȸ ��û�� ó���� Ŀ��� Ŭ���� ����
public class ListScheduleTurnCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		// �� ���� ��� ��ȸ ��û ó��

		// 1.���� ������ ��ȣ�� ���Ѵ�.
		int currentPage = 1;
		PagingVO paging = new PagingVO();

		// 1.�� �������� ������ �Խñ� ���� �����Ѵ�.
		paging.setPostPerPage(10);

		// 2.�� �������� ������ ������ ��� ��
		paging.setPageBlock(5);

		// 3.���� ������ ��ȣ�� ���Ѵ�.
		paging.setCurrentPage(currentPage);

		// 4.�� �Խñ� ���� �����Ѵ�.
		ActionForward forward = new ActionForward();
		
		try {
			TheaterService service = TheaterService.getInstance();
			//System.out.println("�۵�");
			
			//currentPage = Integer.parseInt(req.getParameter("currentPage"));
			
			
			paging.setTotalPost(service.retrieveTotalPost()); 
			String keyfield = req.getParameter("keyfield");//theater_name or movie_title
			String keyword = req.getParameter("keyword");
			//Ű�ʵ� Ű���� �����ؾ���
			int startRow = paging.getStartRow();
			int endRow = paging.getEndRow();
			System.out.printf("startRow : %d, endRow : %d%n",startRow,endRow);
			System.out.printf("keyfield : %s, keyword : %s%n",keyfield,keyword);
			
			
			List<ScreenScheduleVO>screenSchedule = service.retriveScreenScheduleList(keyfield, keyword, startRow, endRow);
			System.out.println(screenSchedule.size());
			
			req.setAttribute("screenSchedule", screenSchedule);
			req.setAttribute("paging", paging);
			
			//�� �Խñ� ���� ���Ѵ�.
			System.out.println("�� �Խñ� �� : " + paging.getTotalPost());
			
			//�� ���������� ���Ѵ�.
			System.out.println("�� ������ �� : " + paging.getTotalPage());
			
			//���� ������ ��ȣ�� ���Ѵ�.
			System.out.println("���� ������ ��ȣ : " + paging.getStartPage());
			
			//������ ������ ��ȣ�� ���Ѵ�.
			System.out.println("������ ������ ��ȣ : " + paging.getEndPage());
			
			forward.setPath("/admin/theater/listScheduleTurnForm.jsp");
			forward.setRedirect(false);
			
		}catch (Exception e) {
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);
		}
		return forward;

		
	}

}
