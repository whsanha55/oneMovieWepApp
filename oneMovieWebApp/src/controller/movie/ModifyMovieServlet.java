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

import domain.movie.MovieVO;
import domain.movie.PhotoVO;
import model.movie.MovieService;
import util.UploadFiles;

@WebServlet(urlPatterns = { "/uploadFile" })
@MultipartConfig(location = "C:\\tempUpload", maxFileSize = 1024 * 1024 * 10, fileSizeThreshold = 1024 * 1024)
public class ModifyMovieServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 2. 게시글 번호를 구한다.
		int movieNo = Integer.parseInt(getStringFromStream(req.getPart("movieNo").getInputStream()));

		try {
			MovieService service = MovieService.getInstance();
			MovieVO movie = service.retriveMovie(movieNo);

			movie.getPhotos().clear();

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
					}
				} else { // upload file인경우
					if (part.getSize() > 0) {
						ServletContext sc = getServletContext();
						PhotoVO photo = UploadFiles.uploadFile(part, sc);
						movie.addPhoto(photo);
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
