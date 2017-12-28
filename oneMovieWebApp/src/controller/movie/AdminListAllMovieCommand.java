package controller.movie;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.movie.MovieVO;
import model.movie.MovieService;
  
public class AdminListAllMovieCommand implements Command{
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		int startRow = Integer.parseInt(req.getParameter("startRow"));
		int endRow = Integer.parseInt(req.getParameter("endRow"));
		//�Խñ� ��� ��ȸ ��û ó��
		ActionForward forward = new ActionForward();		
		try {
			//DB���� �Խñ��� ��ȸ�Ѵ�.
			MovieService movieService = MovieService.getInstance();
			List<MovieVO> movies = movieService.retrieveMovieList(startRow, endRow);

			// 2. request������ "movies"��� �Ӽ��̸����� ���ε��Ѵ�.
			req.setAttribute("movies", movies);

			
			forward.setPath("/admin/movie/listMovieView.jsp");
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
