package jp.co.topgate.sekiguchi.kai;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * FileExtensionをテストするためのクラス
 * 
 * @author sekiguchikai
 */
public class FileExtensionTest {

	/**
	 * getRequestLineメソッドをテストするためのメソッド
	 */
	@Test
	public void testGetFileEtension() {

		String ExtArray[] = { "html", "css", "js", "jpeg", "ping", "gif" };

		for (int i = 0; i < ExtArray.length; i++) {
			// オーソドックスなリクエスト
			assertEquals("オーソドックスなリクエストで" + ExtArray[i] + "拡張子を返すことができるかのテスト", ExtArray[i],
					FileExtension.getFileExtension("/index." + ExtArray[i]));

			// 意地悪なテスト
			assertEquals("ディレクトリ配下かつ、クエリリクエストをつけたリクエストで" + ExtArray[i] + "拡張子を返すことができるかのテスト", ExtArray[i],
					FileExtension.getFileExtension("/image/index." + ExtArray[i] + "/?foo=bar"));

			// 意地悪なテスト
			assertEquals("クエリリクエストをつけたリクエストで" + ExtArray[i] + "拡張子を返すことができるかのテスト", ExtArray[i],
					FileExtension.getFileExtension("/index." + ExtArray[i] + "/?foo=bar"));

			// 意地悪なテスト
			assertEquals("ファイル名の前に「.」がついたリクエストで" + ExtArray[i] + "拡張子を返すことができるかのテスト", ExtArray[i],
					FileExtension.getFileExtension("/.foo/index." + ExtArray[i]));

		}
	}

}
