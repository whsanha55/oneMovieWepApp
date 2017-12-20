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


//게시글 삭제 요청을 처리할 커맨드 클래스 구현
public class RemoveMovieCommand implements Command {

	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
		throws IOException, ServletException {
		
		int movieNo = Integer.parseInt(req.getParameter("no"));
		String pwd = req.getParameter("pwd");
		
		ActionForward forward = new ActionForward();
		try {
			MovieService service = MovieService.getInstance();
			//MovieVO movie = service.deleteMovie(movieNo);
			if(false) {			//movie.getPwd().equals(pwd)
				
				/*service.deleteMovie(movieNo);
				
				if(article.getArticleFiles().size() != 0) {
					String path = req.getServletContext().getRealPath("/upload");
					System.out.println("path : " + path);
					File file = null;
					for(ArticleFileVO articleFile : article.getArticleFiles()) {
						String systemFileName = articleFile.getSystemFileName();
						file = new File(path + File.separator + systemFileName);
						if(file.exists()) {
							file.delete();
						}
					}
				}*/
				
				forward.setPath("/listMovie.do");
				forward.setRedirect(true);	
				
			} else {
				forward.setPath("/removeMovieForm.jsp");
				forward.setRedirect(false);				
			}
			return forward;
			
		} catch (Exception e) {
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);
			return forward;
		}
		
		
	}

}


















