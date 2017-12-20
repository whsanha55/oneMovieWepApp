package controller.movie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;


//게시글 쓰기 폼 요청을 처리할 커맨드 클래스 구현
public class WriteMovieFormCommand implements Command {
	
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) 
		throws IOException, ServletException {
		
		ActionForward forward = new ActionForward();
		forward.setPath("/user/movie/writeArticleForm.jsp");
		forward.setRedirect(false);
		return forward;		
	}
}

















