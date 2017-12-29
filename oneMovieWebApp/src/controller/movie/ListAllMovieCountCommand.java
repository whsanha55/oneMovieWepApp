package controller.movie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.movie.MovieService;
  
public class ListAllMovieCountCommand implements Command{
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		
		ActionForward forward = new ActionForward();		
		try {
			MovieService movieService = MovieService.getInstance();
			int count = movieService.retrieveMovieCount();
			
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
