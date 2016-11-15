package jp.co.topgate.sekiguchi.kai;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

/**
 * HTTPRequestクラスのユニットテストをするためのクラス
 * 
 * @author sekiguchikai
 */
public class HTTPRequestTest {

	/**
	 * GetRequestLineメソッドをテストするためのメソッド
	 */
	@Test
	public void testGetRequeURI() {

		String socketContents = "GET /next.html HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
				+ "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
				+ "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
				+ "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
				+ "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

		InputStream inputStream = new ByteArrayInputStream(socketContents.getBytes());
		HTTPRequest httpRequest = new HTTPRequest();

		assertEquals("リクエストURIを返すことができるかのテスト", "/next.html", httpRequest.getRequestURI(inputStream));
		try {
			inputStream.close();
		} catch (IOException e) {
			System.err.println("エラー" + e.getMessage());
			e.printStackTrace();
		}

		String socketContents2 = "GET /index.html HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
				+ "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
				+ "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
				+ "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
				+ "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

		InputStream inputStream2 = new ByteArrayInputStream(socketContents2.getBytes());
		HTTPRequest httpRequest2 = new HTTPRequest();

		assertEquals("リクエストURIを返すことができるかのテスト", "/index.html", httpRequest2.getRequestURI(inputStream2));

		try {
			inputStream.close();
		} catch (IOException e) {
			System.err.println("エラー" + e.getMessage());
			e.printStackTrace();
		}

		String socketContents3 = "GET /cat.jpeg HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
				+ "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
				+ "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
				+ "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
				+ "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

		InputStream inputStream3 = new ByteArrayInputStream(socketContents3.getBytes());
		HTTPRequest httpRequest3 = new HTTPRequest();

		assertEquals("リクエストURIを返すことができるかのテスト", "/cat.jpeg", httpRequest3.getRequestURI(inputStream3));

		try {
			inputStream.close();
		} catch (IOException e) {
			System.err.println("エラー" + e.getMessage());
			e.printStackTrace();
		}

		String socketContents4 = "GET /cat.png HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
				+ "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
				+ "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
				+ "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
				+ "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

		InputStream inputStream4 = new ByteArrayInputStream(socketContents4.getBytes());
		HTTPRequest httpRequest4 = new HTTPRequest();

		assertEquals("リクエストURIを返すことができるかのテスト", "/cat.png", httpRequest4.getRequestURI(inputStream4));

		try {
			inputStream.close();
		} catch (IOException e) {
			System.err.println("エラー" + e.getMessage());
			e.printStackTrace();
		}

		String socketContents5 = "GET /cat.gif HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
				+ "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
				+ "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
				+ "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
				+ "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

		InputStream inputStream5 = new ByteArrayInputStream(socketContents5.getBytes());
		HTTPRequest httpRequest5 = new HTTPRequest();

		assertEquals("リクエストURIを返すことができるかのテスト", "/cat.gif", httpRequest5.getRequestURI(inputStream5));

		try {
			inputStream.close();
		} catch (IOException e) {
			System.err.println("エラー" + e.getMessage());
			e.printStackTrace();
		}

	}

}
