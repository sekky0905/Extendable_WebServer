package jp.co.topgate.sekiguchi.kai;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
	 * readFileメソッドをテストするクラス"/text/next.html/?foo=bar",
	 * "/index.next.html/?foo=bar"
	 */
	@Test
	public void testReadFile() {

		String requestURIArry[] = { "/next.html", "/.foo/next.html", "/text/next.html?foo=bar",
				"/index.next.html?foo=bar" };

		for (int i = 0; i < requestURIArry.length; i++) {

			// "HTTP/1.1 200 OK"の場合
			// レスポンス出力用にソケットの代わりにByteArrayOutputStreamを用意する
			OutputStream outputStream = new ByteArrayOutputStream();
			HTTPResponse httpResponse = new HTTPResponse(outputStream);

			// メソッドを使用する
			httpResponse.controlResponse(requestURIArry[i]);

			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
					((ByteArrayOutputStream) outputStream).toByteArray());

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(byteArrayInputStream));

			StringBuilder stringBuilder = new StringBuilder();
			String result = null;

			try {
				result = bufferedReader.readLine();

				while (result != null) {
					System.out.println(result);
					stringBuilder.append(result);
					result = bufferedReader.readLine();
				}
				System.out.println(stringBuilder);
			} catch (IOException e) {
				System.err.println("エラー" + e.getMessage());
				e.printStackTrace();
			}

			assertEquals("リクエストURIを与えると、適切なファイルを読み込むか", "HTTP/1.1 200 OKContent-Type: text/htmlやる気",
					new String(stringBuilder));

		}

		// 「/」の場合
		String requestURIArry2[] = { "/", "/image/", "/image/hoge.css/?foo=bar", "/hoge.jpeg/?foo=bar" };

		for (int i = 0; i < requestURIArry2.length; i++) {

			// レスポンス出力用にソケットの代わりにByteArrayOutputStreamを用意する
			OutputStream outputStream2 = new ByteArrayOutputStream();
			HTTPResponse httpResponse2 = new HTTPResponse(outputStream2);

			// メソッドを使用する
			httpResponse2.controlResponse(requestURIArry2[i]);

			ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(
					((ByteArrayOutputStream) outputStream2).toByteArray());

			BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(byteArrayInputStream2));

			StringBuilder stringBuilder2 = new StringBuilder();
			String result2 = null;

			try {
				result2 = bufferedReader2.readLine();

				while (result2 != null) {
					System.out.println(result2);
					stringBuilder2.append(result2);
					result2 = bufferedReader2.readLine();
				}
				System.out.println(stringBuilder2);
			} catch (IOException e) {
				System.err.println("エラー" + e.getMessage());
				e.printStackTrace();
			}

			assertEquals("リクエストURIを与えると、適切なファイルを読み込むか",
					"HTTP/1.1 200 OKContent-Type: text/html<!DOCTYPE html><html lang=\"ja\"><head>	<meta charset=\"UTF-8\">	<title>Document</title>	<link rel=\"stylesheet\" type=\"text/css\" href=\"index.css\"></head><body>	<p id=\"clickMe\">ここを押してください</p>	</form>	<a href=\"./next.html\">次のヤツ</a>	<link rel=\"js\" type=\"text/javascript\" href=\"index.js\">	<script src=\"./index.js\"></script></body></html>",
					new String(stringBuilder2));

		}

		// "404 Not Found"の場合

		String requestURIArry3[] = { "/hoge.html", "/image/jj.jpeg", "/image/hoge.css?foo=bar", "/hoge.jpeg?foo=bar" };

		for (int i = 0; i < requestURIArry2.length; i++) {

			OutputStream outputStream3 = new ByteArrayOutputStream();
			HTTPResponse httpResponse3 = new HTTPResponse(outputStream3);

			httpResponse3.controlResponse(requestURIArry3[i]);

			ByteArrayInputStream byteArrayInputStream3 = new ByteArrayInputStream(
					((ByteArrayOutputStream) outputStream3).toByteArray());

			BufferedReader bufferedReader3 = new BufferedReader(new InputStreamReader(byteArrayInputStream3));

			StringBuilder stringBuilder3 = new StringBuilder();
			String result3 = null;

			try {
				result3 = bufferedReader3.readLine();

				while (result3 != null) {
					System.out.println(result3);
					stringBuilder3.append(result3);
					result3 = bufferedReader3.readLine();
				}
				System.out.println(stringBuilder3);
			} catch (IOException e) {
				System.err.println("エラー" + e.getMessage());
				e.printStackTrace();
			}

			assertEquals("不正なリクエストURIを与えると、404を返すか", "HTTP/1.1 404 Not FoundContent-Type: text/html404 Not Found",
					new String(stringBuilder3));

		}
	}
}