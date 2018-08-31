package pro.buildmysoftware;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;
import java.nio.file.Files;
import java.util.Random;

public class EmbeddedTomcatFactory {
	/**
	 * Creates a configured embedded Tomcat instance. The server listens
	 * on random port.
	 *
	 * @return the configured Tomcat server listening on random port
	 * @throws Exception if fails
	 * @implNote credits:
	 * <a href="https://devcenter.heroku.com/articles/create-a-java-web-application-using-embedded-tomcat">Heroku documentation</a>
	 */
	public static Tomcat create() throws Exception {
		String webappDirLocation = "src/main/webapp/";
		int port = new Random().nextInt(10000) + 8080;

		Tomcat tomcat = new Tomcat();
		tomcat.setBaseDir(Files.createTempDirectory("tomcat").toString
			());
		tomcat.setPort(port);

		StandardContext ctx = (StandardContext) tomcat.addWebapp("",
			new File(webappDirLocation).getAbsolutePath());
		System.out.println("configuring app with basedir: " + new File
			("./" + webappDirLocation).getAbsolutePath());

		// Declare an alternative location for your "WEB-INF/classes"
		// dir
		// Servlet 3.0 annotation will work
		File additionWebInfClasses = new File("target/classes");
		WebResourceRoot resources = new StandardRoot(ctx);
		resources.addPreResources(new DirResourceSet(resources,
			"/WEB-INF/classes", additionWebInfClasses
			.getAbsolutePath(), "/"));
		ctx.setResources(resources);

		return tomcat;
	}
}
