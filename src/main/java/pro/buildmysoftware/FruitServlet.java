package pro.buildmysoftware;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/fruits")
public class FruitServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		req.setAttribute("fruits", Arrays.asList("orange", "banana",
			"apple"));
		req.getRequestDispatcher("fruits.jsp").forward(req, resp);
	}
}
