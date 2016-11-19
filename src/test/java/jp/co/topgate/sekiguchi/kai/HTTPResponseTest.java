package jp.co.topgate.sekiguchi.kai;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import org.junit.Test;

/**
 * HTTPResponseをテストするクラス
 * 
 * @author sekiguchikai
 *
 */

public class HTTPResponseTest {

	/**
	 * StatusLineメソッドをテストするクラス¥
	 */
	@Test
	public void testSetStatusLine() {

		// "HTTP/1.1 200 OK"の場合
		// レスポンス出力用にソケットの代わりにByteArrayOutputStreamを用意する
		OutputStream outputStream = new ByteArrayOutputStream();
		HTTPResponse httpResponse = new HTTPResponse(outputStream);

		// メソッドを使用する
		httpResponse.setStatusLine("HTTP/1.1 200 OK");
		String statusLine = httpResponse.getStatusLine();

		assertEquals("リクエストURIを与えると、適切なファイルを読み込むか", "HTTP/1.1 200 OK", statusLine);

		// "HTTP/1.1 404 Not Found"
		// "HTTP/1.1 200 OK"の場合
		// レスポンス出力用にソケットの代わりにByteArrayOutputStreamを用意する
		OutputStream outputStream2 = new ByteArrayOutputStream();
		HTTPResponse httpResponse2 = new HTTPResponse(outputStream2);

		// メソッドを使用する
		httpResponse2.setStatusLine("HTTP/1.1 404 Not Found");
		String statusLine2 = httpResponse2.getStatusLine();

		assertEquals("リクエストURIを与えると、適切なファイルを読み込むか", "HTTP/1.1 404 Not Found", statusLine2);

	}
	//
	// /**
	// * SetResponseHeaderメソッドをテストするクラス¥
	// */
	// @Test
	// public void testSetResponseHeader() {
	//
	// String fileExtensionArray[] = { "html", "css", "js", "jpeg", "png", "gif"
	// };
	//
	// for (int i = 0; i < fileExtensionArray.length; i++) {
	// String requestURIArray[] = { "/index." + fileExtensionArray[i],
	// "/.sample/index." + fileExtensionArray[i],
	// "/sample/index." + fileExtensionArray[i] + "?foo=bar",
	// "/index." + fileExtensionArray[i] + "?foo=bar" };
	// }
	//
	// for (int i = 0; i < fileExtensionArray.length; i++) {
	// // "HTTP/1.1 200 OK"の場合
	// // レスポンス出力用にソケットの代わりにByteArrayOutputStreamを用意する
	// OutputStream outputStream = new ByteArrayOutputStream();
	// HTTPResponse httpResponse = new HTTPResponse(outputStream);
	//
	// httpResponse.responseHeader = "Content-Type: image/" + fileExtension;
	// // メソッドを使用する
	// httpResponse.setResponseBody(responseBody);
	// String statusLine = httpResponse.getStatusLine();
	// }
	//
	// }

}
