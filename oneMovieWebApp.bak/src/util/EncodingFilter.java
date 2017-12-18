package util;

import javax.servlet.*;
import javax.servlet.http.*;

public class EncodingFilter implements Filter {
	private String encoding; 

	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("call init");
		String temp = filterConfig.getInitParameter("encoding");
		if (temp != null) {
			encoding = temp;
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws java.io.IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String method = req.getMethod();
		System.out.println("method : " + method);

		if (method.equalsIgnoreCase("POST")) {
			req.setCharacterEncoding(encoding);
		}
		chain.doFilter(request, response);
	}

	public void destroy() {
		System.out.println("call destroy");
	}

}
