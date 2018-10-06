package pro.buildmysoftware;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class AnnotationFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse
		servletResponse, FilterChain filterChain) throws IOException,
		ServletException {
		System.out.println("This filter is configured using " +
			"@WebServlet annotation");
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {

	}
}
