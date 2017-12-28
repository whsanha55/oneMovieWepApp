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
  
public class ListAllMovieCountCommand implements Command{
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		
		//게시글 목록 조회 요청 처리
		ActionForward forward = new ActionForward();		
		try {
			//DB에서 게시글을 조회한다.
			MovieService movieService = MovieService.getInstance();
			int count = movieService.retrieveMovieCount();
			
			//2. request영역에 "movies"라는 속성이름으로 바인딩한다.
			req.setAttribute("count", count);
			
			forward.setPath("/admin/movie/successCountMovie.jsp");
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
