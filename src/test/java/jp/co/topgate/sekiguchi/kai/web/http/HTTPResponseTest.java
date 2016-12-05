package jp.co.topgate.sekiguchi.kai.web.http;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.*;
import java.nio.file.Files;

import jp.co.topgate.sekiguchi.kai.web.handler.ResisterMessageHandler;
import jp.co.topgate.sekiguchi.kai.web.http.HTTPResponse;
import jp.co.topgate.sekiguchi.kai.web.util.ResponseHeaderMaker;
import jp.co.topgate.sekiguchi.kai.web.webServer.StaticFileHandler;
import org.junit.Test;

/**
 * HTTPResponseをテストするクラス
 *
 * @author sekiguchikai
 */
public class HTTPResponseTest {

    /**
     * StatusLineメソッドをテストするクラス¥
     */
    @Test
    public void SetStatusLine() {

        OutputStream outputStream = new ByteArrayOutputStream();
        HTTPResponse httpResponse = new HTTPResponse(outputStream);

        httpResponse.setStatusLine("HTTP/1.1 200 OK");
        String statusLine = httpResponse.getStatusLine();

        assertThat(statusLine, is("HTTP/1.1 200 OK"));

        OutputStream outputStream2 = new ByteArrayOutputStream();
        HTTPResponse httpResponse2 = new HTTPResponse(outputStream2);

        httpResponse2.setStatusLine("HTTP/1.1 404 Not Found");
        String statusLine2 = httpResponse2.getStatusLine();

        assertThat(statusLine2, is("HTTP/1.1 404 Not Found"));

    }

    /**
     * SetResponseHeaderメソッドをテストするクラス
     */
    @Test
    public void SetResponseHeader() {

        String fileExtension1 = "html";
        String fileExtension2 = "css";
        String fileExtension3 = "js";
        String fileExtension4 = "jpeg";
        String fileExtension5 = "png";
        String fileExtension6 = "gif";

        String extTextArray[] = {fileExtension1, fileExtension2, fileExtension3};
        String extImageArray[] = {fileExtension4, fileExtension5, fileExtension6};

        for (String ext : extTextArray) {
            OutputStream outputStream = new ByteArrayOutputStream();
            HTTPResponse httpResponse = new HTTPResponse(outputStream);
            httpResponse.setResponseHeader(ResponseHeaderMaker.makeContentType(ext));
            assertThat(httpResponse.getResponseHeader(), is("Content-Type: text/" + ext + "\n"));
        }

        for (String ext : extImageArray) {
            OutputStream outputStream = new ByteArrayOutputStream();
            HTTPResponse httpResponse = new HTTPResponse(outputStream);
            httpResponse.setResponseHeader(ResponseHeaderMaker.makeContentType(ext));
            assertThat(httpResponse.getResponseHeader(), is("Content-Type: image/" + ext + "\n"));
        }

    }
//
//    /**
//     * SetResponseHeaderメソッドをテストするクラス¥
//     */
//    @Test
//    public void testSendResponse() {
//        // リクエストのための処理
//        String socketContents = "GET /next.html HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
//                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
//                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
//                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
//                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";
//
//        String socketContents2 = "GET /next.html?foo=bar HTTP/1.1\n" + "Host: localhost:8080\n"
//                + "Connection: keep-alive\n"
//                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
//                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
//                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
//                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";
//
//        String socketContents3 = "GET /next.html?foo=bar.com HTTP/1.1\n" + "Host: localhost:8080\n"
//                + "Connection: keep-alive\n"
//                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
//                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
//                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
//                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";
//
//        String socketContents4 = "GET /sample/next.html HTTP/1.1\n" + "Host: localhost:8080\n"
//                + "Connection: keep-alive\n"
//                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
//                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
//                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
//                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";
//
//        String socketContents5 = "GET /sample/next.html?foo=bar HTTP/1.1\n" + "Host: localhost:8080\n"
//                + "Connection: keep-alive\n"
//                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
//                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
//                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
//                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";
//
//        String socketContents6 = "GET /sample/next.html?foo=bar.com HTTP/1.1\n" + "Host: localhost:8080\n"
//                + "Connection: keep-alive\n"
//                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
//                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
//                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
//                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";
//
//        String socketContents7 = "GET /.sample/next.html HTTP/1.1\n" + "Host: localhost:8080\n"
//                + "Connection: keep-alive\n"
//                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
//                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
//                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
//                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";
//
//        String socketContents8 = "GET /.sample/next.html?foo=bar HTTP/1.1\n" + "Host: localhost:8080\n"
//                + "Connection: keep-alive\n"
//                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
//                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
//                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
//                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";
//
//        String socketContents9 = "GET /.sample/next.html?foo=bar.com HTTP/1.1\n" + "Host: localhost:8080\n"
//                + "Connection: keep-alive\n"
//                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
//                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
//                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
//                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";
//
//        String socketContentsArray[] = { socketContents, socketContents2, socketContents3, socketContents4,
//                socketContents5, socketContents6, socketContents7, socketContents8, socketContents9 };
//
//        for (int i = 0; i < socketContentsArray.length; i++) {
//
//            InputStream inputStream = new ByteArrayInputStream(socketContentsArray[i].getBytes());
//            HTTPRequest httpRequest = new HTTPRequest(inputStream);
//
//            OutputStream outputStream = new ByteArrayOutputStream();
//            HTTPResponse httpResponse = new HTTPResponse(outputStream);
//
//            httpResponse.setStatusLine("HTTP/1.1 200 OK");
//
//            String requestURI = httpRequest.getRequestURI();
//
//            StaticFileHandler staticFileHandler = new StaticFileHandler();
//
//
//
//
//
//
//
//
//            httpResponse.setResponseHeader(fileExtension);
//
//            byte[] responseBody = staticFileHandler.readFile(file);
//            httpResponse.setResponseBody(responseBody);
//
//            if (file.exists()) {
//                httpResponse.setStatusLine("HTTP/1.1 200 OK");
//            } else {
//                httpResponse.setStatusLine("HTTP/1.1 404 Not Found");
//            }
//
//            httpResponse.sendResponse();
//
//            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
//                    ((ByteArrayOutputStream) outputStream).toByteArray());
//
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(byteArrayInputStream));
//
//            StringBuilder stringBuilder = new StringBuilder();
//            String result = null;
//
//            try {
//                result = bufferedReader.readLine();
//
//                while (result != null) {
//                    System.out.println(result);
//                    stringBuilder.append(result);
//                    result = bufferedReader.readLine();
//                }
//                System.out.println(stringBuilder);
//            } catch (IOException e) {
//                System.err.println("エラー" + e.getMessage());
//                e.printStackTrace();
//            }
//
//            assertEquals("リクエストURIを与えると、適切なファイルを読み込むか", "HTTP/1.1 200 OKContent-Type: text/htmlやる気",
//                    new String(stringBuilder));
//
//        }
//    }


}
