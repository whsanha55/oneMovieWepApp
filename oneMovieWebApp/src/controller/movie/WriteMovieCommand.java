package controller.movie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.movie.MovieVO;
import model.movie.MovieService;


//게시글 쓰기 요청을 처리할 커맨드 클래스 구현
public class WriteMovieCommand implements Command{
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		
		ActionForward forward = new ActionForward();
		
		try {
			
			// 1. 게시글 정보를 구한다.
			String movieTitle = req.getParameter("movieTitle");
			int runningTime = Integer.parseInt(req.getParameter("runningTime"));
			String director = req.getParameter("director");
			int gradeNo = Integer.parseInt(req.getParameter("gradeNo"));
			int nationNo = Integer.parseInt(req.getParameter("nationNo"));
			
			//DB에 게시글을 등록한다.
			MovieService movieService = MovieService.getInstance();
			movieService.addMovie(new MovieVO(movieTitle, runningTime, director, gradeNo, nationNo));
			
			//4. 게시글 목록(listArticleView.jsp) 페이지로 이동한다.
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
