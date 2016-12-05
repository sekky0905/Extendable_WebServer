package jp.co.topgate.sekiguchi.kai.web.handler;

import jp.co.topgate.sekiguchi.kai.web.http.HTTPRequest;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * DeleteMessageHandlerクラスをテストするためのクラス
 * Created by sekiguchikai on 2016/12/05.
 */
public class DeleteMessageHandlerTest {

//    String socketContents1 = "GET /../../test/resources/test.html HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
//            + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
//            + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
//            + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
//            + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";
//
//    String socketContents2 = "GET /../../test/resources/sample/test.html HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
//            + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
//            + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
//            + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
//            + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";
//
//    String socketContents3 = "GET /../../test/resources/test.html?foo=bar HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
//            + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
//            + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
//            + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
//            + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";
//
//    String socketContents4 = "GET /../../test/resources/sample/test.html?foo=bar HTTP/1.1\n" + "Host: localhost:8080\n"
//            + "Connection: keep-alive\n"
//            + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
//            + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
//            + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
//            + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";
//
//    String socketContents5 = "GET /../../test/resources/test.html?foo=bar.com HTTP/1.1\n" + "Host: localhost:8080\n"
//            + "Connection: keep-alive\n"
//            + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
//            + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
//            + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
//            + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";
//
//    String socketContents6 = "GET /../../test/resources/sample/test.html?foo=bar.com HTTP/1.1\n" + "Host: localhost:8080\n"
//            + "Connection: keep-alive\n"
//            + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
//            + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
//            + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
//            + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";
//
//    String socketContents7 = "GET /../../test/resources/.sample/test.html HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
//            + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
//            + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
//            + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
//            + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";
//
//    String socketContents8 = "GET /../../test/resources/.sample/test.html?foo=bar HTTP/1.1\n" + "Host: localhost:8080\n"
//            + "Connection: keep-alive\n"
//            + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
//            + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
//            + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
//            + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";
//
//    String socketContents9 = "GET /../../test/resources/.sample/test.html?foo=bar.com HTTP/1.1\n" + "Host: localhost:8080\n"
//            + "Connection: keep-alive\n"
//            + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
//            + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
//            + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
//            + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";
//
//
//    String socketContents10 = "POST /../../test/resources/test.html HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
//            + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
//            + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
//            + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
//            + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n"
//            + "\n"
//            + "name=a&comment=a";
//
//
//    String socketContentsArray[] = {socketContents1, socketContents2, socketContents3, socketContents4, socketContents5,
//            socketContents6, socketContents7, socketContents8, socketContents9, socketContents10};
//
//
//    /**
//     * handlePOSTメソッドをテストするクラス
//     */
//    @Test
//    public void handlePOST() {
//        HTTPRequest httpRequest = new HTTPRequest();
//
// }


}