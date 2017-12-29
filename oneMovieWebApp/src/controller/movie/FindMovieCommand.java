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

      String keyfield = req.getParameter("keyfield");
      String keyword = req.getParameter("keyword");
      
      int startRow = 1;
      int endRow = 10;
      if(req.getParameter("startRow")!=null) {
         startRow = Integer.parseInt(req.getParameter("startRow"));
      }
      if(req.getParameter("endRow")!=null) {
         endRow = Integer.parseInt(req.getParameter("endRow"));      
      }
      
      ActionForward forward = new ActionForward();    
      try {      
         MovieService service =    MovieService.getInstance();
         List<MovieVO> movies = service.findMovieList(keyfield, keyword, startRow, endRow);
         
         req.setAttribute("movies", movies);
         
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