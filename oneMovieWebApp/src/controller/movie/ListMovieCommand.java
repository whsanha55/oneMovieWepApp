package controller.movie;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.PagingVO;
import domain.movie.DetailMovieVO;
import domain.movie.MovieVO;
import model.movie.MovieDAO;
import model.movie.MovieService;
  
public class ListMovieCommand implements Command{
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		
		//게시글 목록 조회 요청 처리
		ActionForward forward = new ActionForward();		
		try {
			forward.setPath("/layoutUser.jsp?article=/user/movie/listMovie.jsp");
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

