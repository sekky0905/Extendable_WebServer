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
		OutputStream outputStream = new ByteArrayOutputStream();
		HTTPResponse httpResponse = new HTTPResponse(outputStream);

		httpResponse.controlResponse("/next.html");

		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
				((ByteArrayOutputStream) outputStream).toByteArray());

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(byteArrayInputStream));

		String result = null;
		try {
			result = bufferedReader.readLine();
		} catch (IOException e) {
			System.err.println("エラー" + e.getMessage());
			e.printStackTrace();
		}

		assertEquals("リクエストURIを与えると、適切なファイルを読み込むか", "やる気", result);

		// "404 Not Found"の場合
		OutputStream outputStream2 = new ByteArrayOutputStream();
		HTTPResponse httpResponse2 = new HTTPResponse(outputStream2);

		httpResponse2.controlResponse("/hoge.html");

		ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(
				((ByteArrayOutputStream) outputStream2).toByteArray());

		BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(byteArrayInputStream2));

		String result2 = null;
		try {
			result2 = bufferedReader2.readLine();
		} catch (IOException e) {
			System.err.println("エラー" + e.getMessage());
			e.printStackTrace();
		}

		assertEquals("不正なリクエストURIを与えると、404を返すか", "404 Not Found", result2);
	}

}
