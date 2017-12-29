package controller.movie;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import domain.movie.ActorPhotoVO;
import domain.movie.ActorVO;
import domain.movie.DetailMovieVO;
import domain.movie.MovieVO;
import domain.movie.PhotoVO;
import model.movie.MovieService;
import util.UploadFiles;


public class ModifyMovieServlet extends HttpServlet {

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      
      System.out.println("call ModifyMovieServlet doPost");
      // 2. 게시글 번호를 구한다.
      int movieNo = Integer.parseInt(getStringFromStream(req.getPart("movieNo").getInputStream()));

      try {
         MovieService service = MovieService.getInstance();
         DetailMovieVO movie = service.retriveMovie(movieNo);      
         ActorVO actor = null;
         
         movie.getPhotos().clear();
         movie.getActors().clear();
         
         Collection<Part> parts = req.getParts();
         for (Part part : parts) {
            if (part.getContentType() == null) {
               switch (part.getName()) { // field-name
               case "movieTitle":
                  movie.setMovieTitle(getStringFromStream(part.getInputStream()));
                  break;
               case "runningTime":
                  movie.setRunningTime(Integer.parseInt(getStringFromStream(part.getInputStream())));
                  break;
               case "director":
                  movie.setDirector(getStringFromStream(part.getInputStream()));
                  break;
               case "gradeNo":
                  movie.setGradeNo(Integer.parseInt(getStringFromStream(part.getInputStream())));
               case "nationNo":
                  movie.setNationNo(Integer.parseInt(getStringFromStream(part.getInputStream())));
                  break;
               //////////////////////////////////// actor //////////////////////////////////// 
               case "actorName":
                  actor.setActorName(getStringFromStream(part.getInputStream()));                  
                  break;
               case "characterName":
                  actor.setCharacterName(getStringFromStream(part.getInputStream()));                  
                  break;
               case "roleNo":
                  int roleNo = Integer.parseInt(getStringFromStream(part.getInputStream()));                  
                  actor.setRoleNo(roleNo);                  
                  break;
               }
            } else {   //upload file인경우
               if(part.getSize() > 0) {
                  ServletContext sc = getServletContext();
                  switch(part.getName()) {  //field-name            
                     case "upload":
                        PhotoVO photo = UploadFiles.uploadFile(part, sc);
                        movie.addPhoto(photo);                  
                        break;
                     case "uploadactor":                                          
                        actor = new ActorVO();         //actor랑 actorPhoto순서 바꾸지 말 것.
                        ActorPhotoVO actorPhoto = UploadFiles.uploadFile2(part, sc);                  
                        actor.setActorPhoto(actorPhoto);
                        System.out.println("call");
                        movie.addActor1(actor);
                        break;         
                  }
               }
            }
         }
         
         service.updateMovie(movie);
         resp.sendRedirect(req.getContextPath() + "/admin/movie/listMovie.do");

      } catch (Exception e) {
         req.setAttribute("exception", e);
         RequestDispatcher dispather = req.getRequestDispatcher("/error.jsp");
         dispather.forward(req, resp);
      }

   }

   private String getStringFromStream(InputStream is) throws IOException {
      StringBuilder str = new StringBuilder();

      InputStreamReader isr = null;
      try {
         isr = new InputStreamReader(is, "utf-8");
         char[] cbuf = new char[256];
         int readChar = 0;
         while ((readChar = isr.read(cbuf)) != -1) {
            str.append(new String(cbuf, 0, readChar));
         }
      } finally {
         if (isr != null)
            isr.close();
      }
      return str.toString();

   }

}