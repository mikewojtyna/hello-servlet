package pro.buildmysoftware.translator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/translator")
public class TranslatorServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Word> words = Arrays.asList(new Word("slowo", "word"),
			new Word("pies", "dog"));
		req.setAttribute("words", words);
		req.getRequestDispatcher("translator.jsp").forward(req, resp);
	}
}
