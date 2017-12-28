package util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//web.xml�̳� �ֳ����̼� ó���ϱ�~~~~~!!!!
public class MemberAuthFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		
		HttpSession session = req.getSession();
					
		if(session.getAttribute("memberId") != null) {
			 chain.doFilter(request, response);	//�α��ε� ��� ��û�� �۾��� ��� ��
		} else {	//�α��� �Ǿ����� ���� ��� �α��� ȭ������ �̵�
				String contextPath = req.getContextPath();
				resp.sendRedirect(contextPath + "/user/member/alert.jsp");
		}
		
	  


		
	}

}
