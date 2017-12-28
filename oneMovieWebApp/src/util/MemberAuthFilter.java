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

//web.xml이나 애노테이션 처리하기~~~~~!!!!
public class MemberAuthFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		
		HttpSession session = req.getSession();
					
		if(session.getAttribute("memberId") != null) {
			 chain.doFilter(request, response);	//로그인된 경우 요청된 작업을 계속 함
		} else {	//로그인 되어있지 않은 경우 로그인 화면으로 이동
				String contextPath = req.getContextPath();
				resp.sendRedirect(contextPath + "/user/member/alert.jsp");
		}
		
	  


		
	}

}
