package controller.movie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.movie.MovieService;

public class RemoveMovieCommand implements Command{
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		
		String movieNo = req.getParameter("movieNo");
		String[] temp = movieNo.split(",");
		
		int[] movieNoList = new int[temp.length];
		for(int i=0; i<temp.length; i++) {
			movieNoList[i] = Integer.parseInt(temp[i]); 
			System.out.println(movieNoList[i]);
		}
		
		//int movieNo = Integer.parseInt(req.getParameter("movieNo"));
		
		ActionForward forward = new ActionForward();
		try {
			//2. ��й�ȣ ��ġ���θ� Ȯ���Ѵ�.
			MovieService service = MovieService.getInstance();
			service.deleteMovie(movieNoList);
				
			//2.1.2.�Խñ� ��� ��ȸ �������� �̵��Ѵ�.
			forward.setPath("/user/movie/successRemoveMovie.jsp");
			forward.setRedirect(true);
			return forward;
			
		} catch (Exception e) {
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);
			return forward;
		}	
	}

}
