package controller.movie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.movie.MovieService;

public class RemoveMoviePhotoCommand implements Command{
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		 
		int moviePhotoNo = Integer.parseInt(req.getParameter("moviePhotoNo"));
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@ moviePhotoNo : "+moviePhotoNo);
		 
		ActionForward forward = new ActionForward();
		try {
			MovieService service = MovieService.getInstance();
			
			service.deleteMoviePhoto(moviePhotoNo);
			
				
			//forward.setPath("/user/movie/successRemoveMovie.jsp");
			forward.setRedirect(true);
			forward.setPath("/admin/movie/listMovie.jsp");
			return forward;
			
		} catch (Exception e) {
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);
			return forward;
		}	
	}
}
