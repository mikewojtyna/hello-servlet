package pro.buildmysoftware;

import org.apache.catalina.startup.Tomcat;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class HelloServletIntegrationTest {

	private Tomcat tomcat;

	@Before
	public void beforeEach() throws Exception {
		tomcat = EmbeddedTomcatFactory.create();
		tomcat.start();
	}

	@After
	public void afterEach() throws Exception {
		tomcat.stop();
	}

	@Test
	public void should_ReturnHelloPageWithNullName_When_Get() throws
		Exception {
		// given
		String uri = uri("/hello");

		// when
		CloseableHttpResponse response = HttpClientBuilder.create()
			.build().execute(new HttpGet(uri));

		// then
		assertThat(response.getStatusLine().getStatusCode(), is(org
			.apache.http.HttpStatus.SC_OK));
		assertThat(EntityUtils.toString(response.getEntity()),
			containsString("hello null!"));
	}

	@Test
	public void should_StoreNameInSession_When_Post() throws Exception {
		// given
		String uri = uri("/hello");
		CookieStore cookieStore = new BasicCookieStore();
		HttpClient httpClient = HttpClientBuilder.create().
			setDefaultCookieStore(cookieStore).build();
		List<NameValuePair> postParameters = Collections.singletonList
			(new BasicNameValuePair("name", "goobar"));
		HttpPost post = new HttpPost(uri);
		post.setEntity(new UrlEncodedFormEntity(postParameters,
			"UTF-8"));

		// when
		httpClient.execute(post);

		// then
		assertThat(cookieStore.getCookies(), hasSize(1));
		assertThat(cookieStore.getCookies().get(0).getName(), is
			("JSESSIONID"));
		HttpResponse getResponse = httpClient.execute(new HttpGet
			(uri));
		assertThat(EntityUtils.toString(getResponse.getEntity()),
			containsString("hello goobar!"));
	}

	private String uri(String endpoint) {
		return String.format("http://localhost:%s/%s", tomcat
			.getConnector().getPort(), endpoint);
	}
}
