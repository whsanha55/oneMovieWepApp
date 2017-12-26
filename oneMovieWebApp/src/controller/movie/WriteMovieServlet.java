package controller.movie;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import domain.movie.ActorPhotoVO;
import domain.movie.ActorVO;
import domain.movie.GenreVO;
import domain.movie.MovieGenreVO;
import domain.movie.MovieVO;
import domain.movie.PhotoVO;
import model.movie.MovieService;
import util.UploadFiles;

//���Ͼ��ε� ��û�� ó���� ���� Ŭ���� ����
public class WriteMovieServlet extends HttpServlet {

<<<<<<< HEAD
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
		
		MovieVO movie = new MovieVO();	
		MovieGenreVO genre = new MovieGenreVO();	
		ActorVO actor = null;		
		
		Collection<Part> parts = req.getParts();		
		for(Part part : parts) {
			if(part.getContentType() == null) {				
				switch(part.getName()) {  //field-name				
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
					//////////////////////////////////// movie genre /////////////////////////////////////
					case "genreNo":
						genre.setGenreNo(Integer.parseInt(getStringFromStream(part.getInputStream())));
						movie.addMovieGenre(genre);
						break;	
					//////////////////////////////////// actor //////////////////////////////////////	
					case "actorName":
						actor.setActorName(getStringFromStream(part.getInputStream()));
						break;
					case "characterName":
						actor.setCharacterName(getStringFromStream(part.getInputStream()));
						break;
					case "roleNo":
						actor.setRoleNo(Integer.parseInt(getStringFromStream(part.getInputStream())));			
						
				}		
			} else {   //upload file�ΰ��
				if(part.getSize() > 0) {
					ServletContext sc = getServletContext();
					switch(part.getName()) {  //field-name				
						case "upload":
							PhotoVO photo = UploadFiles.uploadFile(req.getPart("upload"), sc);
							movie.addPhoto(photo);						
							break;
						case "uploadactor":														
							actor = new ActorVO();			//actor�� actorPhoto���� �ٲ��� �� ��.
							ActorPhotoVO actorPhoto = UploadFiles.uploadFile2(req.getPart("uploadactor"), sc);						
							actor.setActorPhoto(actorPhoto);
							movie.addActor(actor);
							break;			
					}
				}
			}
		}
		
		try {
			//DB�� �Խñ��� ����Ѵ�.
			MovieService movieService = MovieService.getInstance();
			System.out.println("movie : " + movie);
			movieService.addMovie(movie);
			
			//�Խñ� ��� ��ȸ �������� �̵��Ѵ�.
			resp.sendRedirect(req.getContextPath() +"/user/movie/listMovie.do");
		} catch (Exception e) {
			req.setAttribute("exception", e);
		    RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
		    dispatcher.forward(req, resp);
		}	
	}
=======
   
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
      throws ServletException, IOException {
      
      MovieVO movie = new MovieVO();   
      MovieGenreVO genre = new MovieGenreVO();   
      ActorVO actor = null;      
      
      Collection<Part> parts = req.getParts();      
      for(Part part : parts) {
         if(part.getContentType() == null) {            
            switch(part.getName()) {  //field-name            
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
               //////////////////////////////////// movie genre /////////////////////////////////////
               case "genreNo":
                  genre.setGenreNo(Integer.parseInt(getStringFromStream(part.getInputStream())));
                  movie.addMovieGenre(genre);
                  break;   
               //////////////////////////////////// actor //////////////////////////////////////   
               case "actorName":
                  actor.setActorName(getStringFromStream(part.getInputStream()));
                  break;
               case "characterName":
                  actor.setCharacterName(getStringFromStream(part.getInputStream()));
                  break;
               case "roleNo":
                  actor.setRoleNo(Integer.parseInt(getStringFromStream(part.getInputStream())));         
                  
            }      
         } else {   //upload file�ΰ��
            if(part.getSize() > 0) {
               ServletContext sc = getServletContext();
               switch(part.getName()) {  //field-name            
                  case "upload":
                     PhotoVO photo = UploadFiles.uploadFile(req.getPart("upload"), sc);
                     movie.addPhoto(photo);                  
                     break;
                  case "uploadactor":                                          
                     actor = new ActorVO();         //actor�� actorPhoto���� �ٲ��� �� ��.
                     ActorPhotoVO actorPhoto = UploadFiles.uploadFile2(req.getPart("uploadactor"), sc);                  
                     actor.setActorPhoto(actorPhoto);
                     movie.addActor(actor);
                     break;         
               }
            }
         }
      }
      
      try {
         //DB�� �Խñ��� ����Ѵ�.
         MovieService movieService = MovieService.getInstance();
         System.out.println("movie : " + movie);
         movieService.addMovie(movie);
         
         //�Խñ� ��� ��ȸ �������� �̵��Ѵ�.
         resp.sendRedirect(req.getContextPath() +"/user/movie/listMovie.do");
      } catch (Exception e) {
         req.setAttribute("exception", e);
          RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
          dispatcher.forward(req, resp);
      }   
   }
>>>>>>> branch 'master' of https://github.com/whsanha55/oneMovieWepApp.git

   
   private String getStringFromStream(InputStream is) throws IOException {
      StringBuilder str = new StringBuilder();
      
      InputStreamReader isr = null;      
      try {
         isr = new InputStreamReader(is, "utf-8");
         char[] cbuf = new char[256];
         int readChar = 0;
         while((readChar = isr.read(cbuf)) != -1) {
            str.append(new String(cbuf, 0, readChar));
         }
      } finally {
         if(isr != null) isr.close();
      }
      return str.toString();
      
   }

}















