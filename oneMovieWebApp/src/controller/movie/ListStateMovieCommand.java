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

public class ListStateMovieCommand implements Command{
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{

		String keyfield = req.getParameter("keyfield");

		ActionForward forward = new ActionForward();		
		try {		
			MovieService service = MovieService.getInstance();
			List<MovieVO> movies = service.retrieveStateMovieList(keyfield);
			
			req.setAttribute("movies", movies);

			forward.setPath("/layoutUser.jsp?article=/user/movie/listStateMovie.jsp");
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
