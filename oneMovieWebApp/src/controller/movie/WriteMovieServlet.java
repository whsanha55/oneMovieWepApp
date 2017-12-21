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
import domain.movie.MovieVO;
import domain.movie.PhotoVO;
import model.movie.MovieService;
import util.UploadFiles;

//���Ͼ��ε� ��û�� ó���� ������ Ŭ���� ����
public class WriteMovieServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
		
		MovieVO movie = new MovieVO();
		ActorVO actor = new ActorVO();
		
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
							PhotoVO photo = UploadFiles.uploadFile(part, sc);
							movie.addPhoto(photo);
							break;
						case "uploadactor":
							ActorPhotoVO actorPhoto = UploadFiles.uploadFile2(part, sc);
							actor.addActorPhoto(actorPhoto);
							break;
					
					}
				}
			}
		}
		
		try {
			//DB�� �Խñ��� ����Ѵ�.
			MovieService movieService = MovieService.getInstance();
			movieService.addMovie(movie);
			
			//�Խñ� ��� ��ȸ �������� �̵��Ѵ�.
			resp.sendRedirect(req.getContextPath() +"/user/movie/listMovie.do");
		} catch (Exception e) {
			req.setAttribute("exception", e);
		    RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
		    dispatcher.forward(req, resp);
		}		
		
	}
	
	
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















