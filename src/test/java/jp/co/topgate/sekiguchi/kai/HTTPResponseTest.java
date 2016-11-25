package jp.co.topgate.sekiguchi.kai;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

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

        assertEquals("リクエストURIを与えると、適切なファイルを読み込むか", "HTTP/1.1 200 OK", statusLine);

        OutputStream outputStream2 = new ByteArrayOutputStream();
        HTTPResponse httpResponse2 = new HTTPResponse(outputStream2);

        httpResponse2.setStatusLine("HTTP/1.1 404 Not Found");
        String statusLine2 = httpResponse2.getStatusLine();

        assertEquals("リクエストURIを与えると、適切なファイルを読み込むか", "HTTP/1.1 404 Not Found", statusLine2);

    }

    /**
     * SetResponseHeaderメソッドをテストするクラス
     */
    @Test
    public void SetResponseHeader() {

        // Content-Type: text/hogeの場合
        String fileExtensionArray[] = {"html", "css", "js"};
        List<String> requestResourceList = new ArrayList<String>();

        // requestURIListにfileExtensionArray[]の中身を拡張子として代入
        for (int i = 0; i < fileExtensionArray.length; i++) {
            requestResourceList.add("/index." + fileExtensionArray[i]);
        }

        // requestURIListにfileExtensionArray[]の中身を拡張子として代入
        for (int j = 0; j < fileExtensionArray.length; j++) {
            requestResourceList.add("/.sample/index." + fileExtensionArray[j]);
        }

        List<String> extArray = new ArrayList<>();
        for (int m = 0; m < requestResourceList.size(); m++) {

            for (int n = 0; n < fileExtensionArray.length; n++) {
                extArray.add(fileExtensionArray[n]);
            }
        }

        for (int o = 0; o < requestResourceList.size(); o++) {

            OutputStream outputStream = new ByteArrayOutputStream();
            HTTPResponse httpResponse = new HTTPResponse(outputStream);

            File file = new File("src/main/resources" + requestResourceList.get(o));
            httpResponse.setResponseHeader(requestResourceList.get(o), file);

            assertEquals("リクエストURIを与えると、適切なファイルを読み込むか", "Content-Type: text/" + extArray.get(o),
                    httpResponse.getResponseHeader());
        }

        String fileExtensionArray2[] = {"jpeg", "png", "gif"};
        List<String> requestResourceList2 = new ArrayList<String>();

        for (int i = 0; i < fileExtensionArray2.length; i++) {
            requestResourceList2.add("/index." + fileExtensionArray2[i]);
        }

        for (int j = 0; j < fileExtensionArray2.length; j++) {
            requestResourceList2.add("/.sample/index." + fileExtensionArray2[j]);
        }

        List<String> extArray2 = new ArrayList<>();
        for (int m = 0; m < requestResourceList2.size(); m++) {

            for (int n = 0; n < fileExtensionArray2.length; n++) {
                extArray2.add(fileExtensionArray2[n]);
            }
        }

        for (int o = 0; o < requestResourceList2.size(); o++) {
            // "HTTP/1.1 200 OK"の場合
            // レスポンス出力用にソケットの代わりにByteArrayOutputStreamを用意する
            OutputStream outputStream2 = new ByteArrayOutputStream();
            HTTPResponse httpResponse2 = new HTTPResponse(outputStream2);

            // System.out.println("リクエストURIは" + requestURIList.get(j));
            // メソッドを使用する
            File file = new File("src/main/resources" + requestResourceList2.get(o));
            httpResponse2.setResponseHeader(requestResourceList2.get(o), file);

            assertEquals("リクエストURIを与えると、適切なファイルを読み込むか", "Content-Type: image/" + extArray2.get(o),
                    httpResponse2.getResponseHeader());
        }
    }

    /**
     * SetResponseHeaderメソッドをテストするクラス¥
     */
    @Test
    public void SendResponse() {
        // リクエストのための処理
        String socketContents = "GET /next.html HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

        String socketContents2 = "GET /next.html?foo=bar HTTP/1.1\n" + "Host: localhost:8080\n"
                + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

        String socketContents3 = "GET /next.html?foo=bar.com HTTP/1.1\n" + "Host: localhost:8080\n"
                + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

        String socketContents4 = "GET /sample/next.html HTTP/1.1\n" + "Host: localhost:8080\n"
                + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

        String socketContents5 = "GET /sample/next.html?foo=bar HTTP/1.1\n" + "Host: localhost:8080\n"
                + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

        String socketContents6 = "GET /sample/next.html?foo=bar.com HTTP/1.1\n" + "Host: localhost:8080\n"
                + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

        String socketContents7 = "GET /.sample/next.html HTTP/1.1\n" + "Host: localhost:8080\n"
                + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

        String socketContents8 = "GET /.sample/next.html?foo=bar HTTP/1.1\n" + "Host: localhost:8080\n"
                + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

        String socketContents9 = "GET /.sample/next.html?foo=bar.com HTTP/1.1\n" + "Host: localhost:8080\n"
                + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

        String socketContentsArray[] = {socketContents, socketContents2, socketContents3, socketContents4,
                socketContents5, socketContents6, socketContents7, socketContents8, socketContents9};

        for (int i = 0; i < socketContentsArray.length; i++) {

            InputStream inputStream = new ByteArrayInputStream(socketContentsArray[i].getBytes());
            HTTPRequest httpRequest = new HTTPRequest(inputStream);

            OutputStream outputStream = new ByteArrayOutputStream();
            HTTPResponse httpResponse = new HTTPResponse(outputStream);

            httpResponse.setStatusLine("HTTP/1.1 200 OK");

            String requestURI = httpRequest.getRequestURI();
            Files files = new Files();

            File file;
            String requestResource;

            if (requestURI.substring(requestURI.length() - 1).equals("/")
                    || requestURI.substring(requestURI.lastIndexOf("/"), requestURI.length()).indexOf(".") == -1) {
                requestResource = "src/main/resources" + requestURI + "index.html";
                file = new File(requestResource);
            } else {
                requestResource = "src/main/resources" + requestURI;
                file = new File(requestResource);
            }

            httpResponse.setResponseHeader(requestResource, file);

            byte[] responseBody = files.readFile(file);
            httpResponse.setResponseBody(responseBody);

            if (file.exists()) {
                httpResponse.setStatusLine("HTTP/1.1 200 OK");
            } else {
                httpResponse.setStatusLine("HTTP/1.1 404 Not Found");
            }

            httpResponse.sendResponse();

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

        }
    }

}
