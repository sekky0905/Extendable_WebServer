package jp.co.topgate.sekiguchi.kai;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import jp.co.topgate.sekiguchi.kai.HTTPRequest;

/**
 * 
 * HTTPRequestクラスのユニットテストをするためのクラス
 * 
 * @author sekiguchikai
 */
public class HTTPRequestTest {
	/**
	 * リクエストライン
	 */
	private String requestLine = "GET /cat.jpeg HTTP/1.1";
	/**
	 * リクエストURI
	 */
	private String requestURI = "/cat.jpeg";
	/**
	 * クライアントからのリクエストURIのjava.io.Fileインスタンス
	 */
	private File file = new File("src/main/resources/" + this.requestURI);
	/**
	 * 拡張子
	 */
	private String extension = "jpeg";

	/**
	 * HTTPRequestクラスのインスタンス
	 */
	private HTTPRequest httpRequest = new HTTPRequest();

	private String requestContents = "GET /cat.jpeg HTTP/1.1\n Host: localhost:8080\n Connection: keep-alive\n"
			+ "Cache-Control: max-age=0\n" + "Upgrade-Insecure-Requests: 1\n"
			+ "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
			+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\n"
			+ "Accept-Encoding: gzip, deflate, sdch, br2\n" + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
			+ "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

	@Test
	/**
	 * GetRequestLineメソッドをテストするためのメソッド
	 */
	public void testGetRequestLine() throws IOException {

		byte[] requestContentntsByte = requestContents.getBytes();
		InputStream byteStreamArray = new ByteArrayInputStream(requestContentntsByte);

		assertEquals("クライアントからのリクエストからリクエストラインを取得できるか確認できるかのテスト", this.requestLine,
				this.httpRequest.getRequestLine(byteStreamArray));
	}

	@Test
	/**
	 * GetgetRequestURIメソッドをテストするためのメソッド
	 */
	public void testGetgetRequestURI() {

		assertEquals("リクエストラインからリクエストURIを取得できるか確認できるかのテスト", this.requestURI,
				this.httpRequest.getRequestURI(this.requestLine));
	}

	@Test
	/**
	 * GetRequestResourceメソッドをテストするためのメソッド
	 */
	public void testGetRequestResource() {
		assertEquals("リクエストURIからファイルを取得できるか確認できるかのテスト", this.file,
				this.httpRequest.getRequestResource(this.requestURI));
	}

	@Test
	/**
	 * testGetResourceExtメソッドをテストするためのメソッド
	 */
	public void testGetResourceExt() {
		assertEquals("リクエストURIからファイルを取得できるか確認できるかのテスト", this.extension,
				this.httpRequest.getResourceExt(this.requestURI));
	}
}