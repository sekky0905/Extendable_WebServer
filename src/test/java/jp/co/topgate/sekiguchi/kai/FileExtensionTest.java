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
	 * GetRequestLineメソッドをテストするためのメソッド
	 */
	@Test
	public void testGetFileEtension() {
		assertEquals("リクエストラインと指定された拡張子を返すことができるかのテスト", "html", FileExtension.getFileExtension("/next.html"));
	}

}
