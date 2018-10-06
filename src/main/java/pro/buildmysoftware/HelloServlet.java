package pro.buildmysoftware;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * This servlet is mapped using web.xml.
 */
public class HelloServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		HttpSession session = req.getSession();
		req.setAttribute("name", session.getAttribute("name"));
		req.getRequestDispatcher("hello.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse
		resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.setAttribute("name", req.getParameter("name"));
		resp.sendRedirect("hello");
	}
}
