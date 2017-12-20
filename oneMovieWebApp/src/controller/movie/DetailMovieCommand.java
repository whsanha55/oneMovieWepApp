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
		//게시글 상세 조회 요청 처리		
		//1. 상세히 조회하고자 하는 게시글 번호를 구한다.
		int movieNo = Integer.parseInt(req.getParameter("movieNo"));
		
		ActionForward forward = new ActionForward();
		
		try {		
			//2. DB에서 게시글 번호에 해당하는 게시글의 조회수를 증가한다.
			MovieService articleService = MovieService.getInstance();
			
			DetailMovieVO movie = articleService.retriveMovie(movieNo);
			
			//3. request영역에 "article"라는 속성이름으로 바인딩한다.
			req.setAttribute("movie", movie);
			
			//4. 게시글 상세조회(detailArticle.jsp) 페이지로 이동한다.
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
