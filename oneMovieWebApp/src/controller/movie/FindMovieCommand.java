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
  
public class FindMovieCommand implements Command{
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		//게시글 검색 요청 처리		
		//1. 검색조건 및 검색어를 구한다.
		String keyfield = req.getParameter("keyfield");
		String keyword = req.getParameter("keyword");
		
		ActionForward forward = new ActionForward();
		
		try {		
			MovieService service = 	MovieService.getInstance();
			List<MovieVO> movies = service.retriveMovieList(keyfield, keyword);
			
			//3. request영역에 "articles"라는 속성이름으로 바인딩한다.
			req.setAttribute("movies", movies);
			
			//4. 게시글 목록(listArticleView.jsp) 페이지로 이동한다.
			forward.setPath("/user/movie/listMovieView.jsp");
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
