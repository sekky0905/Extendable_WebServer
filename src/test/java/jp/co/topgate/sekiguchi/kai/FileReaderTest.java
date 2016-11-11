package jp.co.topgate.sekiguchi.kai;
import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

import jp.co.topgate.sekiguchi.kai.FileReader;

/**
 * FileReaderクラスのユニットテストをするためのクラス
 * 
 * @author sekiguchikai
 *
 */
public class FileReaderTest {

	/**
	 * FileReaderクラスのインスタンス
	 */
	private FileReader fileReader = new FileReader();

	/**
	 * next.htmlのURI
	 */
	private File HTMLFile = new File("src/main/resources/next.html");
	/**
	 * index.jsのURI
	 */
	private File jsFile = new File("src/main/resources/index.js");
	/**
	 * next.htmlのURI
	 */
	private File CSSegFile = new File("src/main/resources/index.css");
	/**
	 * index.cssのURI
	 */
	private File jpegFile = new File("src/main/resources/cat.jpeg");
	/**
	 * cat.jpegのURI
	 */
	private File pngFile = new File("src/main/resources/cat.png");
	/**
	 * cat.gifのURI
	 */
	private File gifFile = new File("src/main/resources/cat.gif");

	/**
	 * hoge.htmlのURI
	 */
	private File notExistHTMLFile = new File("src/main/resources/hoge.html");
	/**
	 * hoge.jsのURI
	 */
	private File notExistJsFile = new File("src/main/resources/hoge.js");
	/**
	 * hoge.cssのURI
	 */
	private File notExistCSSegFile = new File("src/main/resources/hoge.css");
	/**
	 * hoge.jpegのURI
	 */
	private File notExistJpegFile = new File("src/main/resources/hoge.jpeg");
	/**
	 * hoge.pngのURI
	 */
	private File notExistPngFile = new File("src/main/resources/hoge.png");
	/**
	 * hoge.gifのURI
	 */
	private File notExistGifFile = new File("src/main/resources/hoge.gif");

	@Test
	/**
	 * ConfirmExistence()メソッドをテストするメソッド
	 * 
	 */
	public void testConfirmExistence() {
		// 存在するファイルのテスト
		assertEquals("存在するHTMLファイルを確認できるかのテスト", true, this.fileReader.ConfirmExistence(this.HTMLFile));
		assertEquals("存在するCSSファイルを確認できるかのテスト", true, this.fileReader.ConfirmExistence(this.CSSegFile));
		assertEquals("存在するJavaScriptファイルを確認できるかのテスト", true, this.fileReader.ConfirmExistence(this.jsFile));

		assertEquals("存在するjpegを確認できるかのテスト", true, this.fileReader.ConfirmExistence(this.jpegFile));
		assertEquals("存在するpngファイルを確認できるかのテスト", true, this.fileReader.ConfirmExistence(this.pngFile));
		assertEquals("存在するgifファイルを確認できるかのテスト", true, this.fileReader.ConfirmExistence(this.gifFile));

		// 存在しないファイルのテスト
		assertEquals("存在しないHTMLファイルを確認できるかのテスト", false, this.fileReader.ConfirmExistence(this.notExistHTMLFile));
		assertEquals("存在しないCSSファイルを確認できるかのテスト", false, this.fileReader.ConfirmExistence(this.notExistJsFile));
		assertEquals("存在しないJavaScriptファイルを確認できるかのテスト", false, this.fileReader.ConfirmExistence(this.notExistCSSegFile));

		assertEquals("存在しないjpegファイルを確認できるかのテスト", false, this.fileReader.ConfirmExistence(this.notExistJpegFile));
		assertEquals("存在しないpngファイルを確認できるかのテスト", false, this.fileReader.ConfirmExistence(this.notExistPngFile));
		assertEquals("存在しないgifファイルを確認できるかのテスト", false, this.fileReader.ConfirmExistence(this.notExistGifFile));
	}

}