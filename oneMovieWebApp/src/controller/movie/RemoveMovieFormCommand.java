package controller.movie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.movie.DetailMovieVO;
import domain.movie.MovieVO;
import model.movie.MovieDAO;


public class RemoveMovieFormCommand implements Command {

	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		// 게시글 수정 폼 요청 처리
		// 1. 수정하고자 하는 게시글 번호를 구한다.
		int movieNo = Integer.parseInt(req.getParameter("movieNo"));

		ActionForward forward = new ActionForward();

		try {		

			// 2. DB에서 게시글 번호에 해당하는 게시글 정보를 구한다.
			MovieDAO dao = MovieDAO.getInstance();
			DetailMovieVO movie = dao.selectMovie(movieNo);

			// 3. request 영역에 "article"라는 속성이름으로 게시글 정보를 바인딩한다.
			req.setAttribute("movie", movie);

			// 3. 게시글 수정폼 페이지(removeArticleForm.jsp)로 이동한다.
			forward.setPath("/removeMovieForm.jsp");
			forward.setRedirect(false);
			return forward;

		} catch (Exception e) {
			req.setAttribute("exception", e);
			forward.setPath("/erorr.jsp");
			forward.setRedirect(false);
			return forward;
		}
	}

}
