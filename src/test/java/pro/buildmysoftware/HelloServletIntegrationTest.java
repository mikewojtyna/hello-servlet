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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloServletIntegrationTest {

	private Tomcat tomcat;

	@BeforeEach
	public void beforeEach() throws Exception {
		tomcat = EmbeddedTomcatFactory.create(randomPort());
		tomcat.start();
	}

	private int randomPort() {
		return new Random().nextInt(10000) + 8080;
	}

	@AfterEach
	public void afterEach() throws Exception {
		tomcat.stop();
	}

	@DisplayName("should return hello page with null name when get")
	@Test
	public void test0() throws Exception {
		// given
		String uri = uri("/hello");

		// when
		CloseableHttpResponse response = HttpClientBuilder.create()
			.build().execute(new HttpGet(uri));

		// then
		assertThat(response.getStatusLine().getStatusCode()).isEqualTo
			(SC_OK);
		assertThat(EntityUtils.toString(response.getEntity()).contains
			("hello null!"));
	}

	@DisplayName("should store name in session when post")
	@Test
	public void test1() throws Exception {
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
		assertThat(cookieStore.getCookies()).hasSize(1);
		assertThat(cookieStore.getCookies().get(0).getName())
			.isEqualTo("JSESSIONID");
		HttpResponse getResponse = httpClient.execute(new HttpGet
			(uri));
		assertThat(EntityUtils.toString(getResponse.getEntity())
			.contains("hello goobar!"));
	}

	private String uri(String endpoint) {
		return String.format("http://localhost:%s/%s", tomcat
			.getConnector().getPort(), endpoint);
	}
}
