package jp.co.topgate.sekiguchi.kai;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import jp.co.topgate.sekiguchi.kai.ResponseContentsMaker;

/**
 * ResponseContentsMakerクラスのユニットテストをするためのクラス
 * 
 * @author sekiguchikai
 *
 */
public class ResponseContentsMakerTest {

	/**
	 * responseContentMakerクラスのインスタンス
	 */
	ResponseContentsMaker responseContentMaker = new ResponseContentsMaker();

	@Test
	/**
	 * MakeStatusLineメソッドをテストするための
	 */
	public void testMakeStatusLine() {
		boolean validFileExistence = true;
		boolean invalidFileExistence = false;

		assertEquals("有効な拡張子を送信したら適切なステータスラインを設定するか確認するテスト", "HTTP/1.1 200 OK",
				this.responseContentMaker.makeStatusLine(validFileExistence));
		assertEquals("無効な拡張子を送信したら適切なステータスラインを設定するか確認するテスト", "HTTP/1.1 400 Not Found",
				this.responseContentMaker.makeStatusLine(invalidFileExistence));

	}

	@Test
	/**
	 * MakeResponseHeaderメソッドをテストするための
	 */
	public void testMakeResponseHeader() {
		String HTMLFileExtension = "html";
		String CSSFileExtension = "css";
		String jsFileExtension = "js";

		String jpegFileExtension = "jpeg";
		String pngFileExtension = "png";
		String gifFileExtension = "gif";

		assertEquals("html拡張子を送信したら適切なレスポンスヘッダを設定するか確認するテスト", "Content-Type: text/" + HTMLFileExtension,
				this.responseContentMaker.makeResponseHeader(HTMLFileExtension));
		assertEquals("html拡張子を送信したら適切なレスポンスヘッダを設定するか確認するテスト", "Content-Type: text/" + CSSFileExtension,
				this.responseContentMaker.makeResponseHeader(CSSFileExtension));
		assertEquals("html拡張子を送信したら適切なレスポンスヘッダを設定するか確認するテスト", "Content-Type: text/" + jsFileExtension,
				this.responseContentMaker.makeResponseHeader(jsFileExtension));

		assertEquals("html拡張子を送信したら適切なレスポンスヘッダを設定するか確認するテスト", "Content-Type: image/" + jpegFileExtension,
				this.responseContentMaker.makeResponseHeader(jpegFileExtension));
		assertEquals("html拡張子を送信したら適切なレスポンスヘッダを設定するか確認するテスト", "Content-Type: image/" + pngFileExtension,
				this.responseContentMaker.makeResponseHeader(pngFileExtension));
		assertEquals("html拡張子を送信したら適切なレスポンスヘッダを設定するか確認するテスト", "Content-Type: image/" + gifFileExtension,
				this.responseContentMaker.makeResponseHeader(gifFileExtension));

	}

}