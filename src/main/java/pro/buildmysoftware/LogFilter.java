package pro.buildmysoftware;

import javax.servlet.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse
		servletResponse, FilterChain filterChain) throws IOException,
		ServletException {
		System.out.println("filter log message");
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {

	}
}
