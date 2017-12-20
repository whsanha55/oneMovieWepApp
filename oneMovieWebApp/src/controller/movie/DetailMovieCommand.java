package controller.movie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.movie.DetailMovieVO;
import model.movie.MovieService;
  
public class DetailMovieCommand implements Command{
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		//�Խñ� �� ��ȸ ��û ó��		
		//1. ���� ��ȸ�ϰ��� �ϴ� �Խñ� ��ȣ�� ���Ѵ�.
		int movieNo = Integer.parseInt(req.getParameter("movieNo"));
		
		ActionForward forward = new ActionForward();
		
		try {		
			//2. DB���� �Խñ� ��ȣ�� �ش��ϴ� �Խñ��� ��ȸ���� �����Ѵ�.
			MovieService articleService = MovieService.getInstance();
			
			DetailMovieVO movie = articleService.retriveMovie(movieNo);
			
			//3. request������ "article"��� �Ӽ��̸����� ���ε��Ѵ�.
			req.setAttribute("movie", movie);
			
			//4. �Խñ� ����ȸ(detailArticle.jsp) �������� �̵��Ѵ�.
			forward.setPath("/user/movie/detailMovie.jsp");
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
