package pro.buildmysoftware.twitter;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * A class used to initialize and configure servlets that require simple form
 * of dependency injection.
 */
@WebListener
public class ServletContextInitializer implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent event) {
		registerTwitterServlet(event.getServletContext());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

	private void registerTwitterServlet(ServletContext context) {
		context.addServlet("TwitterServlet", new TwitterServlet
			(DashboardFactory.createDataSource())).addMapping
			("/twitter");
	}
}
