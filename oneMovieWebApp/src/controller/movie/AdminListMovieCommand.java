package controller.movie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
  
public class AdminListMovieCommand implements Command{
   public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{

      ActionForward forward = new ActionForward();      
      try {
         forward.setPath("/admin/layoutAdmin.jsp?article=/admin/movie/listMovie.jsp");
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
