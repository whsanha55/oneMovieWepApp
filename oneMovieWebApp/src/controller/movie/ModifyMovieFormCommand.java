package controller.movie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.movie.MovieVO;
import model.movie.MovieService;


//영화 수정 폼 요청을 처리할 커맨드 클래스 구현
public class ModifyMovieFormCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
		throws IOException, ServletException {

		int movieNo = Integer.parseInt(req.getParameter("movieNo"));

		ActionForward forward = new ActionForward();
		try {
			MovieService service = MovieService.getInstance();
			MovieVO movie = service.retriveMovie(movieNo);

			req.setAttribute("movie", movie);

			forward.setPath("/admin/movie/modifyMovieForm.jsp");
			forward.setRedirect(false);
			return forward;

		} catch (Exception e) {
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);
			return forward;
		}
	}

}




