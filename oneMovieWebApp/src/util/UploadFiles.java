package util;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;

import domain.movie.ActorPhotoVO;
import domain.movie.PhotoVO;


public class UploadFiles {	
	
	private static int count = 1;
	
	public static PhotoVO uploadFile(Part part, ServletContext context) throws IOException {
		String moviePhotoOriginalFileName = getMoviePhotoOriginalFileName(part);
		String moviePhotosystemFileName = "";
		
		String path = context.getRealPath("/user/movie/upload");
		System.out.println("path : " + path);
		File file = new File(path + File.separator + moviePhotoOriginalFileName);
		if(file.exists()) {
			moviePhotosystemFileName = moviePhotoOriginalFileName.substring(0, moviePhotoOriginalFileName.lastIndexOf(".")) +
				"_" + count + moviePhotoOriginalFileName.substring(moviePhotoOriginalFileName.lastIndexOf(".")); 
			count++;
			
		} else {
			moviePhotosystemFileName = moviePhotoOriginalFileName;
		}
	
		part.write(path + File.separator + moviePhotosystemFileName);
		
		PhotoVO photo = new PhotoVO();
		photo.setMoviePhotoOriginalFileName(moviePhotoOriginalFileName);
		photo.setMoviePhotoSystemFileName(moviePhotosystemFileName);
		return photo;		
	}
	
	
	public static ActorPhotoVO uploadFile2(Part part, ServletContext context) throws IOException {
		String actorOriginalFileName = getActorOriginalFileName(part);
		String actorSystemFileName = "";
		
		String path = context.getRealPath("/user/movie/upload");
		System.out.println("path : " + path);
		File file = new File(path + File.separator + actorOriginalFileName);
		if(file.exists()) {
			actorSystemFileName = actorOriginalFileName.substring(0, actorOriginalFileName.lastIndexOf(".")) +
				"_" + count + actorOriginalFileName.substring(actorOriginalFileName.lastIndexOf(".")); 
			count++;
			
		} else {
			actorSystemFileName = actorOriginalFileName;
		}
	
		part.write(path + File.separator + actorSystemFileName);
		
		ActorPhotoVO actorPhoto = new ActorPhotoVO();
		actorPhoto.setActorOriginalFileName(actorOriginalFileName);
		actorPhoto.setActorSystemFileName(actorSystemFileName);
		return actorPhoto;		
	}

	
	private static String getActorOriginalFileName(Part part)  throws IOException {
		//form-data; name="upload"; filename="JSP.pptx"
		String contentDisposition = part.getHeader("Content-Disposition");
		String[] str = contentDisposition.split(";");
		for(String temp : str) {
			if(temp.trim().startsWith("filename")) {				
				return temp.trim().substring(temp.trim().indexOf("=") + 1).replaceAll("\"", "");
			}
		}
		return null;
	}


	private static String getMoviePhotoOriginalFileName(Part part)  throws IOException {
		//form-data; name="upload"; filename="JSP.pptx"
		String contentDisposition = part.getHeader("Content-Disposition");
		String[] str = contentDisposition.split(";");
		for(String temp : str) {
			if(temp.trim().startsWith("filename")) {				
				return temp.trim().substring(temp.trim().indexOf("=") + 1).replaceAll("\"", "");
			}
		}
		return null;
	}
	
}
















