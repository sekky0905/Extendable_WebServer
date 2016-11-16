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
	 * readFileメソッドをテストするクラス
	 */
	@Test
	public void testReadFile() {
		// "HTTP/1.1 200 OK"の場合
		// レスポンス出力用にソケットの代わりにByteArrayOutputStreamを用意する
		OutputStream outputStream = new ByteArrayOutputStream();
		HTTPResponse httpResponse = new HTTPResponse(outputStream);

		// メソッドを使用する
		httpResponse.controlResponse("/next.html");

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

		// "404 Not Found"の場合
		OutputStream outputStream2 = new ByteArrayOutputStream();
		HTTPResponse httpResponse2 = new HTTPResponse(outputStream2);

		httpResponse2.controlResponse("/hoge.html");

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

		assertEquals("リクエストURIを与えると、適切なファイルを読み込むか", "HTTP/1.1 404 Not FoundContent-Type: text/html404 Not Found",
				new String(stringBuilder2));

	}
}