package controller.movie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.movie.MovieVO;
import model.movie.MovieService;


//�Խñ� ���� ��û�� ó���� Ŀ�ǵ� Ŭ���� ����
public class WriteMovieCommand implements Command{
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		
		ActionForward forward = new ActionForward();
		
		try {
			
			// 1. �Խñ� ������ ���Ѵ�.
			String movieTitle = req.getParameter("movieTitle");
			int runningTime = Integer.parseInt(req.getParameter("runningTime"));
			String director = req.getParameter("director");
			int gradeNo = Integer.parseInt(req.getParameter("gradeNo"));
			int nationNo = Integer.parseInt(req.getParameter("nationNo"));
			
			//DB�� �Խñ��� ����Ѵ�.
			MovieService movieService = MovieService.getInstance();
			movieService.addMovie(new MovieVO(movieTitle, runningTime, director, gradeNo, nationNo));
			
			//4. �Խñ� ���(listArticleView.jsp) �������� �̵��Ѵ�.
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
