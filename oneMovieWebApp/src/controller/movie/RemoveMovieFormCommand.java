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
		
		// �Խñ� ���� �� ��û ó��
		// 1. �����ϰ��� �ϴ� �Խñ� ��ȣ�� ���Ѵ�.
		int movieNo = Integer.parseInt(req.getParameter("movieNo"));

		ActionForward forward = new ActionForward();

		try {		

			// 2. DB���� �Խñ� ��ȣ�� �ش��ϴ� �Խñ� ������ ���Ѵ�.
			MovieDAO dao = MovieDAO.getInstance();
			DetailMovieVO movie = dao.selectMovie(movieNo);

			// 3. request ������ "article"��� �Ӽ��̸����� �Խñ� ������ ���ε��Ѵ�.
			req.setAttribute("movie", movie);

			// 3. �Խñ� ������ ������(removeArticleForm.jsp)�� �̵��Ѵ�.
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
