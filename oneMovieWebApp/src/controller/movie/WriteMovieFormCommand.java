package controller.movie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;


//�Խñ� ���� �� ��û�� ó���� Ŀ�ǵ� Ŭ���� ����
public class WriteMovieFormCommand implements Command {
	
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) 
		throws IOException, ServletException {
		
		ActionForward forward = new ActionForward();
		forward.setPath("/user/movie/writeArticleForm.jsp");
		forward.setRedirect(false);
		return forward;		
	}
}

















