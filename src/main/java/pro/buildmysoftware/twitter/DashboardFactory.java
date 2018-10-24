package pro.buildmysoftware.twitter;

import pl.sdacademy.twitter.db.DataSourceFactory;
import pl.sdacademy.twitter.db.SqlTweetRepository;
import pl.sdacademy.twitter.db.executor.ClasspathSqlScriptExecutor;
import pl.sdacademy.twitter.model.Dashboard;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * A simple wrapper to create instances of {@link Dashboard} configured to
 * the needs of the application.
 */
public class DashboardFactory {
	public static Dashboard createDashboard() {
		try {
			DataSource dataSource = DataSourceFactory
				.createH2DataSource();
			new ClasspathSqlScriptExecutor(dataSource).execute
				("db/twitter-0.sql");
			return new Dashboard(new SqlTweetRepository
				(dataSource));
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
