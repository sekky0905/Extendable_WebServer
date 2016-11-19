// package jp.co.topgate.sekiguchi.kai;
//
// import static org.junit.Assert.assertEquals;
//
// import org.junit.Test;
//
/// **
// * FileExtensionをテストするためのクラス
// *
// * @author sekiguchikai
// */
// public class FileExtensionTest {
//
// /**
// * getRequestLineメソッドをテストするためのメソッド
// */
// @Test
// public void testGetFileEtension() {
//
// String ExtArray[] = { "html", "css", "js", "jpeg", "ping", "gif" };
//
// for (int i = 0; i < ExtArray.length; i++) {
// // オーソドックスなリクエスト
// assertEquals("オーソドックスなリクエストで" + ExtArray[i] + "拡張子を返すことができるかのテスト",
// ExtArray[i],
// FileExtension.getFileExtension("/index." + ExtArray[i]));
//
// // 意地悪なテスト
// assertEquals("ファイル名の前に「.」がついたリクエストで" + ExtArray[i] + "拡張子を返すことができるかのテスト",
// ExtArray[i],
// FileExtension.getFileExtension("/.foo/index." + ExtArray[i]));
//
// // 意地悪なテスト
// assertEquals("拡張子の後のリクエストパラメータに「.」がついたリクエストで" + "css" + "拡張子を返すことができるかのテスト",
// "css",
// FileExtension.getFileExtension("/.foo/index.css?foo=bar.com"));
//
// }
//
// }
//
// }
