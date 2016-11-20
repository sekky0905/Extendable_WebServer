// package jp.co.topgate.sekiguchi.kai;
//
// import static org.junit.Assert.assertEquals;
//
// import java.io.ByteArrayOutputStream;
// import java.io.File;
// import java.io.OutputStream;
// import java.util.ArrayList;
// import java.util.List;
//
// import org.junit.Test;
//
/// **
// * HTTPResponseをテストするクラス
// *
// * @author sekiguchikai
// *
// */
// public class HTTPResponseTest {
//
// /**
// * StatusLineメソッドをテストするクラス¥
// */
// @Test
// public void testSetStatusLine() {
//
// // "HTTP/1.1 200 OK"の場合
// // レスポンス出力用にソケットの代わりにByteArrayOutputStreamを用意する
// OutputStream outputStream = new ByteArrayOutputStream();
// HTTPResponse httpResponse = new HTTPResponse(outputStream);
//
// // メソッドを使用する
// httpResponse.setStatusLine("HTTP/1.1 200 OK");
// String statusLine = httpResponse.getStatusLine();
//
// assertEquals("リクエストURIを与えると、適切なファイルを読み込むか", "HTTP/1.1 200 OK", statusLine);
//
// // "HTTP/1.1 404 Not Found"
// // "HTTP/1.1 200 OK"の場合
// // レスポンス出力用にソケットの代わりにByteArrayOutputStreamを用意する
// OutputStream outputStream2 = new ByteArrayOutputStream();
// HTTPResponse httpResponse2 = new HTTPResponse(outputStream2);
//
// // メソッドを使用する
// httpResponse2.setStatusLine("HTTP/1.1 404 Not Found");
// String statusLine2 = httpResponse2.getStatusLine();
//
// assertEquals("リクエストURIを与えると、適切なファイルを読み込むか", "HTTP/1.1 404 Not Found",
// statusLine2);
//
// }
//
// /**
// * SetResponseHeaderメソッドをテストするクラス¥
// */
// @Test
// public void testSetResponseHeader() {
//
// // Content-Type: text/hogeの場合
// String fileExtensionArray[] = { "html", "css", "js" };
// List<String> requestURIList = new ArrayList<String>();
//
// // requestURIListにfileExtensionArray[]の中身を拡張子として代入
// for (int i = 0; i < fileExtensionArray.length; i++) {
// requestURIList.add("/index." + fileExtensionArray[i]);
// }
//
// // requestURIListにfileExtensionArray[]の中身を拡張子として代入
// for (int j = 0; j < fileExtensionArray.length; j++) {
// requestURIList.add("/.sample/index." + fileExtensionArray[j]);
// }
//
// // requestURIListにfileExtensionArray[]の中身を拡張子として代入
// for (int k = 0; k < fileExtensionArray.length; k++) {
// requestURIList.add("/sample/index." + fileExtensionArray[k] + "?foo=bar");
// }
//
// // requestURIListにfileExtensionArray[]の中身を拡張子として代入
// for (int l = 0; l < fileExtensionArray.length; l++) {
// requestURIList.add("/index." + fileExtensionArray[l] + "?foo=bar");
// }
//
// List<String> extArray = new ArrayList<>();
// for (int m = 0; m < requestURIList.size(); m++) {
//
// for (int n = 0; n < fileExtensionArray.length; n++) {
// extArray.add(fileExtensionArray[n]);
// }
// }
//
// for (int o = 0; o < requestURIList.size(); o++) {
// // "HTTP/1.1 200 OK"の場合
// // レスポンス出力用にソケットの代わりにByteArrayOutputStreamを用意する
// OutputStream outputStream = new ByteArrayOutputStream();
// HTTPResponse httpResponse = new HTTPResponse(outputStream);
//
// // System.out.println("リクエストURIは" + requestURIList.get(j));
// // メソッドを使用する
// File file = new File("src/main/resources" + requestURIList.get(o));
// httpResponse.setResponseHeader(requestURIList.get(o), file);
//
// assertEquals("リクエストURIを与えると、適切なファイルを読み込むか", "Content-Type: text/" +
// extArray.get(o),
// httpResponse.getResponseHeader());
// }
//
// // Content-Type: text/hogeの場合
// String fileExtensionArray2[] = { "jpeg", "png", "gif" };
// List<String> requestURIList2 = new ArrayList<String>();
//
// // requestURIListにfileExtensionArray[]の中身を拡張子として代入
// for (int i = 0; i < fileExtensionArray2.length; i++) {
// requestURIList2.add("/index." + fileExtensionArray2[i]);
// }
//
// // requestURIListにfileExtensionArray[]の中身を拡張子として代入
// for (int j = 0; j < fileExtensionArray2.length; j++) {
// requestURIList2.add("/.sample/index." + fileExtensionArray2[j]);
// }
//
// // requestURIListにfileExtensionArray[]の中身を拡張子として代入
// for (int k = 0; k < fileExtensionArray2.length; k++) {
// requestURIList2.add("/sample/index." + fileExtensionArray2[k] + "?foo=bar");
// }
//
// // requestURIListにfileExtensionArray[]の中身を拡張子として代入
// for (int l = 0; l < fileExtensionArray2.length; l++) {
// requestURIList2.add("/index." + fileExtensionArray2[l] + "?foo=bar");
// }
//
// List<String> extArray2 = new ArrayList<>();
// for (int m = 0; m < requestURIList2.size(); m++) {
//
// for (int n = 0; n < fileExtensionArray2.length; n++) {
// extArray2.add(fileExtensionArray2[n]);
// }
// }
//
// for (int o = 0; o < requestURIList2.size(); o++) {
// // "HTTP/1.1 200 OK"の場合
// // レスポンス出力用にソケットの代わりにByteArrayOutputStreamを用意する
// OutputStream outputStream2 = new ByteArrayOutputStream();
// HTTPResponse httpResponse2 = new HTTPResponse(outputStream2);
//
// // System.out.println("リクエストURIは" + requestURIList.get(j));
// // メソッドを使用する
// File file = new File("src/main/resources" + requestURIList2.get(o));
// httpResponse2.setResponseHeader(requestURIList2.get(o), file);
//
// assertEquals("リクエストURIを与えると、適切なファイルを読み込むか", "Content-Type: image/" +
// extArray2.get(o),
// httpResponse2.getResponseHeader());
// }
//
// }
//
// /**
// * SetResponseHeaderメソッドをテストするクラス¥
// */
// // @Test
// // public void testSendResponse() {
// //
// // String requestURIArry[] = { "/next.html", "/sample/next.html",
// // "/.sample/next.html", "/next.html?foo=bar",
// // "/sample/next.html?foo=bar", "/next.html?foo=bar.com" };
// //
// // for (int i = 0; i < requestURIArry.length; i++) {
// //
// // // "HTTP/1.1 200 OK"の場合
// // // レスポンス出力用にソケットの代わりにByteArrayOutputStreamを用意する
// // OutputStream outputStream = new ByteArrayOutputStream();
// // HTTPResponse httpResponse = new HTTPResponse(outputStream);
// //
// // setStatusLine(String status)
// //
// //
// // // メソッドを使用する
// // httpResponse.sendResponse();
// //
// // ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
// // ((ByteArrayOutputStream) outputStream).toByteArray());
// //
// // BufferedReader bufferedReader = new BufferedReader(new
// // InputStreamReader(byteArrayInputStream));
// //
// // StringBuilder stringBuilder = new StringBuilder();
// // String result = null;
// //
// // try {
// // result = bufferedReader.readLine();
// //
// // while (result != null) {
// // System.out.println(result);
// // stringBuilder.append(result);
// // result = bufferedReader.readLine();
// // }
// // System.out.println(stringBuilder);
// // } catch (IOException e) {
// // System.err.println("エラー" + e.getMessage());
// // e.printStackTrace();
// // }
// //
// // assertEquals("リクエストURIを与えると、適切なファイルを読み込むか", "HTTP/1.1 200 OKContent-Type:
// // text/htmlやる気",
// // new String(stringBuilder));
// //
//
// }
