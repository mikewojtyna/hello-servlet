package pro.buildmysoftware.twitter;

import pl.sdacademy.twitter.model.Dashboard;
import pl.sdacademy.twitter.model.Tweet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * This servlet is added and mapped in the {@link ServletContextInitializer}
 * class.
 */
public class TwitterServlet extends HttpServlet {

	private final Dashboard dashboard;

	public TwitterServlet(Dashboard dashboard) {
		this.dashboard = dashboard;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		req.setAttribute("tweets", loadTweets());
		req.getRequestDispatcher("twitter.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse
		resp) throws ServletException, IOException {
		createTweet(req);
		resp.sendRedirect("twitter");
	}

	private void createTweet(HttpServletRequest req) {
		dashboard.create(req.getParameter("message"), req.getParameter
			("author"));
	}

	private Collection<Tweet> loadTweets() {
		return dashboard.load().collect(Collectors.toList());
	}
}
