package pro.buildmysoftware.jetty;

import com.github.mjeanroy.junit.servers.annotations.TestServer;
import com.github.mjeanroy.junit.servers.jetty.EmbeddedJetty;
import com.github.mjeanroy.junit.servers.runner.JunitServerRunner;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

@RunWith(JunitServerRunner.class)
public class HelloServletIntegrationTest {
	@TestServer
	public static EmbeddedJetty jetty;

	@Test
	public void should_ReturnHelloPageWithNullName_When_Get() throws
		IOException {
		// when
		CloseableHttpResponse response = HttpClientBuilder.create()
			.build().execute(new HttpGet(jetty.getUrl() +
				"/hello"));

		// then
		assertThat(response.getStatusLine().getStatusCode(), is(org
			.apache.http.HttpStatus.SC_OK));
		assertThat(EntityUtils.toString(response.getEntity()),
			containsString("hello null!"));
	}

	@Test
	public void should_StoreNameInSession_When_Post() throws Exception {
		// given
		String uri = jetty.getUrl() + "/hello";
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
}
