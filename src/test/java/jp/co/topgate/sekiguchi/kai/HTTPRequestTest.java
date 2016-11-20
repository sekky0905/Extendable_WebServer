package jp.co.topgate.sekiguchi.kai;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Test;

/**
 * HTTPRequestをテストするクラス
 * 
 * @author sekiguchikai
 *
 */
public class HTTPRequestTest {

	String socketContents = "GET /next.html HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
			+ "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
			+ "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
			+ "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
			+ "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

	String socketContents2 = "GET /sample/next.html HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
			+ "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
			+ "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
			+ "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
			+ "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

	String socketContents3 = "GET /next.html?foo=bar HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
			+ "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
			+ "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
			+ "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
			+ "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

	String socketContents4 = "GET /sample/next.html?foo=bar HTTP/1.1\n" + "Host: localhost:8080\n"
			+ "Connection: keep-alive\n"
			+ "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
			+ "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
			+ "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
			+ "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

	String socketContents5 = "GET /next.html?foo=bar.com HTTP/1.1\n" + "Host: localhost:8080\n"
			+ "Connection: keep-alive\n"
			+ "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
			+ "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
			+ "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
			+ "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

	String socketContents6 = "GET /sample/next.html?foo=bar.com HTTP/1.1\n" + "Host: localhost:8080\n"
			+ "Connection: keep-alive\n"
			+ "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
			+ "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
			+ "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
			+ "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

	String socketContents7 = "GET /.sample/next.html HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
			+ "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
			+ "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
			+ "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
			+ "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

	String socketContents8 = "GET /.sample/next.html?foo=bar HTTP/1.1\n" + "Host: localhost:8080\n"
			+ "Connection: keep-alive\n"
			+ "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
			+ "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
			+ "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
			+ "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

	String socketContents9 = "GET /.sample/next.html?foo=bar.com HTTP/1.1\n" + "Host: localhost:8080\n"
			+ "Connection: keep-alive\n"
			+ "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
			+ "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
			+ "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
			+ "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

	String socketContentsArray[] = { socketContents, socketContents2, socketContents3, socketContents4, socketContents5,
			socketContents6, socketContents7, socketContents8, socketContents9 };

	/**
	 * getRequestMethodメソッドをテストするメソッド
	 */
	@Test
	public void testGetRequestMethod() {
		for (int i = 0; i < socketContentsArray.length; i++) {
			InputStream inputStream = new ByteArrayInputStream(socketContentsArray[i].getBytes());
			HTTPRequest httpRequest = new HTTPRequest(inputStream);

			assertEquals("GETリクエストを与えると、適切なリクエストメソッドを返すことができるか", "GET", httpRequest.getRequestMethod());
		}

	}

	/**
	 * getRequestURIメソッドをテストするメソッド
	 */
	@Test
	public void testGetRequestURI() {

		String expectedRequestURI = "/next.html";
		String expectedRequestURI2 = "/sample/next.html";
		String expectedRequestURI3 = "/next.html";
		String expectedRequestURI4 = "/sample/next.html";
		String expectedRequestURI5 = "/next.html";
		String expectedRequestURI6 = "/sample/next.html";
		String expectedRequestURI7 = "/.sample/next.html";
		String expectedRequestURI8 = "/.sample/next.html";
		String expectedRequestURI9 = "/.sample/next.html";
		String expectedRequestURIArray[] = { expectedRequestURI, expectedRequestURI2, expectedRequestURI3,
				expectedRequestURI4, expectedRequestURI5, expectedRequestURI6, expectedRequestURI7, expectedRequestURI8,
				expectedRequestURI9 };

		for (int i = 0; i < socketContentsArray.length; i++) {
			InputStream inputStream = new ByteArrayInputStream(socketContentsArray[i].getBytes());
			HTTPRequest httpRequest = new HTTPRequest(inputStream);

			String requestURI = httpRequest.getRequestURI();
			System.out.println("リクエストURIは" + requestURI);

			assertEquals("GETリクエストを与えると、適切なリクエストURIを返すことができるか", expectedRequestURIArray[i], requestURI);
		}

	}

}
