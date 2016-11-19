package jp.co.topgate.sekiguchi.kai;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

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

	/**
	 * SetResponseHeaderメソッドをテストするクラス¥
	 */
	@Test
	public void testSetResponseHeader() {

		String fileExtensionArray[] = { "html", "css", "js" };
		List<String> requestURIList = new ArrayList<String>();

		// requestURIListにfileExtensionArray[]の中身を拡張子として代入
		for (int i = 0; i < fileExtensionArray.length; i++) {
			requestURIList.add("/index." + fileExtensionArray[i]);
		}

		for (int j = 0; j < requestURIList.size(); j++) {
			// "HTTP/1.1 200 OK"の場合
			// レスポンス出力用にソケットの代わりにByteArrayOutputStreamを用意する
			OutputStream outputStream = new ByteArrayOutputStream();
			HTTPResponse httpResponse = new HTTPResponse(outputStream);

			// System.out.println("リクエストURIは" + requestURIList.get(j));
			// メソッドを使用する
			httpResponse.setResponseHeader(requestURIList.get(j));

			assertEquals("リクエストURIを与えると、適切なファイルを読み込むか", "Content-Type: text/" + fileExtensionArray[j],
					httpResponse.getResponseHeader());
		}

		// requestURIList.add("/.sample/index." + fileExtensionArray[i]);
		// requestURIList.add("/sample/index." + fileExtensionArray[i] +
		// "?foo=bar");
		// requestURIList.add("/index." + fileExtensionArray[i] + "?foo=bar");
		//
		//
		// // requestURIListの中身の個数分、
		//
		//
		// }

	}

}
