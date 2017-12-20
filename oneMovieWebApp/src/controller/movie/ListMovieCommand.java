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
 
public class ListMovieCommand implements Command{
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		//�Խñ� ��� ��ȸ ��û ó��
		ActionForward forward = new ActionForward();		
		try {
			//DB���� �Խñ��� ��ȸ�Ѵ�.
			MovieService articleService = MovieService.getInstance();
			List<MovieVO> movies = articleService.retrieveMovieList();
			
			//2. request������ "articles"��� �Ӽ��̸����� ���ε��Ѵ�.
			req.setAttribute("movies", movies);
			
			forward.setPath("/user/movie/listMovie.jsp");
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

