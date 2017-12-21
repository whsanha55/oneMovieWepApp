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

//상영 일정 목록 조회 요청을 처리할 커멘드 클래스 구현
public class ListScheduleTurnCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		// 상영 일정 목록 조회 요청 처리

		// 1.현재 페이지 번호를 구한다.
		int currentPage = 1;
		PagingVO paging = new PagingVO();

		// 1.한 페이지에 보여줄 게시글 수를 설정한다.
		paging.setPostPerPage(10);

		// 2.한 페이지에 보여줄 페이지 목록 수
		paging.setPageBlock(5);

		// 3.현재 페이지 번호를 구한다.
		paging.setCurrentPage(currentPage);

		// 4.총 게시글 수를 설정한다.
		ActionForward forward = new ActionForward();
		
		try {
			TheaterService service = TheaterService.getInstance();
			//System.out.println("작동");
			
			//currentPage = Integer.parseInt(req.getParameter("currentPage"));
			
			
			paging.setTotalPost(service.retrieveTotalPost()); 
			String keyfield = req.getParameter("keyfield");//theater_name or movie_title
			String keyword = req.getParameter("keyword");
			//키필드 키워드 수정해야함
			int startRow = paging.getStartRow();
			int endRow = paging.getEndRow();
			System.out.printf("startRow : %d, endRow : %d%n",startRow,endRow);
			System.out.printf("keyfield : %s, keyword : %s%n",keyfield,keyword);
			
			
			List<ScreenScheduleVO>screenSchedule = service.retriveScreenScheduleList(keyfield, keyword, startRow, endRow);
			System.out.println(screenSchedule.size());
			
			req.setAttribute("screenSchedule", screenSchedule);
			req.setAttribute("paging", paging);
			
			//총 게시글 수를 구한다.
			System.out.println("총 게시글 수 : " + paging.getTotalPost());
			
			//총 페이지수를 구한다.
			System.out.println("총 페이지 수 : " + paging.getTotalPage());
			
			//시작 페이지 번호를 구한다.
			System.out.println("시작 페이지 번호 : " + paging.getStartPage());
			
			//마지막 페이지 번호를 구한다.
			System.out.println("마지막 페이지 번호 : " + paging.getEndPage());
			
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
