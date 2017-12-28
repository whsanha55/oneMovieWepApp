package controller.movie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.movie.DetailMovieVO;
import domain.movie.MovieVO;
import model.movie.MovieService;
  
public class AdminDetailMovieCommand implements Command{
   public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{

      int movieNo = Integer.parseInt(req.getParameter("movieNo"));
      
      ActionForward forward = new ActionForward();
      
      try {      
         MovieService movieService = MovieService.getInstance();
         
         DetailMovieVO movie = movieService.retriveMovie(movieNo);
         
         req.setAttribute("movie", movie);
          
         //4. 게시글 상세조회(detailArticle.jsp) 페이지로 이동한다.
         forward.setPath("/layoutAdmin.jsp?article=/admin/movie/detailMovie.jsp");
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