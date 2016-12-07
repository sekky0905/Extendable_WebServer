package jp.co.topgate.sekiguchi.kai.web.http;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import jp.co.topgate.sekiguchi.kai.web.http.HTTPRequest;
import org.junit.Test;

/**
 * HTTPRequestをテストするクラス
 *
 * @author sekiguchikai
 */
public class HTTPRequestTest {

    String socketContents1 = "GET /../../test/resources/test.html HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
            + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
            + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
            + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
            + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

    String socketContents2 = "GET /../../test/resources/sample/test.html HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
            + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
            + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
            + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
            + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

    String socketContents3 = "GET /../../test/resources/test.html?foo=bar HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
            + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
            + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
            + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
            + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

    String socketContents4 = "GET /../../test/resources/sample/test.html?foo=bar HTTP/1.1\n" + "Host: localhost:8080\n"
            + "Connection: keep-alive\n"
            + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
            + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
            + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
            + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

    String socketContents5 = "GET /../../test/resources/test.html?foo=bar.com HTTP/1.1\n" + "Host: localhost:8080\n"
            + "Connection: keep-alive\n"
            + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
            + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
            + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
            + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

    String socketContents6 = "GET /../../test/resources/sample/test.html?foo=bar.com HTTP/1.1\n" + "Host: localhost:8080\n"
            + "Connection: keep-alive\n"
            + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
            + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
            + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
            + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

    String socketContents7 = "GET /../../test/resources/.sample/test.html HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
            + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
            + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
            + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
            + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

    String socketContents8 = "GET /../../test/resources/.sample/test.html?foo=bar HTTP/1.1\n" + "Host: localhost:8080\n"
            + "Connection: keep-alive\n"
            + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
            + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
            + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
            + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

    String socketContents9 = "GET /../../test/resources/.sample/test.html?foo=bar.com HTTP/1.1\n" + "Host: localhost:8080\n"
            + "Connection: keep-alive\n"
            + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
            + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
            + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
            + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";


    String socketContents10 = "POST /../../test/resources/test.html HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
            + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
            + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
            + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
            + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n"
            + "\n"
            + "name=a&comment=a";


    String socketContentsArray[] = {socketContents1, socketContents2, socketContents3, socketContents4, socketContents5,
            socketContents6, socketContents7, socketContents8, socketContents9, socketContents10};


    /**
     * getRequestMethodメソッドをテストするメソッド
     */
    @Test
    public void getRequestMethod() {
        for (int i = 0; i < socketContentsArray.length; i++) {
            InputStream inputStream = new ByteArrayInputStream(socketContentsArray[i].getBytes());
            HTTPRequest httpRequest = new HTTPRequest(inputStream);

            httpRequest.getRequestMethod();

            if (i != 9) {
                assertThat(httpRequest.getRequestMethod(), is("GET"));
            } else {
                assertThat(httpRequest.getRequestMethod(), is("POST"));
            }
        }

    }


    /**
     * getRequestURIメソッドをテストするメソッド
     */
    @Test
    public void getRequestURI() {

        String expRequestURI1 = "/../../test/resources/test.html";
        String expRequestURI2 = "/../../test/resources/sample/test.html";
        String expRequestURI3 = "/../../test/resources/test.html";
        String expRequestURI4 = "/../../test/resources/sample/test.html";
        String expRequestURI5 = "/../../test/resources/test.html";
        String expRequestURI6 = "/../../test/resources/sample/test.html";
        String expRequestURI7 = "/../../test/resources/.sample/test.html";
        String expRequestURI8 = "/../../test/resources/.sample/test.html";
        String expRequestURI9 = "/../../test/resources/.sample/test.html";
        String expRequestURI10 = "/../../test/resources/test.html";

        String expRequestURIArray[] = {expRequestURI1, expRequestURI2, expRequestURI3, expRequestURI4, expRequestURI5, expRequestURI6, expRequestURI7, expRequestURI8, expRequestURI9, expRequestURI10};


        for (int i = 0; i < socketContentsArray.length; i++) {
            InputStream inputStream = new ByteArrayInputStream(socketContentsArray[i].getBytes());
            HTTPRequest httpRequest = new HTTPRequest(inputStream);

            try {
                assertThat(httpRequest.getRequestURI(), is(expRequestURIArray[i]));
            } catch (UnsupportedEncodingException e) {
                System.err.println("エラー:" + e.getMessage());
                e.getCause();
                e.printStackTrace();
            }


        }
    }

    @Test
    public void getQueryString() {


        String requestContents1 = "GET /../../test/resources/test.html?foo=bar HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";


        String requestContents2 = "GET /../../test/resources/test.html?foo=bar.com HTTP/1.1\n" + "Host: localhost:8080\n"
                + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";


        String requestContents3 = "POST /../../test/resources/test.html HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n"
                + "Content-Length: 20\n"
                + "\n"
                + "name=a&comment=a\n";

        String requestContentsArray[] = {requestContents1, requestContents2, requestContents3};

        String expQueryString1 = "foo=bar";
        String expQueryString2 = "foo=bar.com";
        String expQueryString3 = "name=a&comment=a";

        String expQueryStringArray[] = {expQueryString1, expQueryString2, expQueryString3};

        for (int i = 0; i < requestContentsArray.length; i++) {
            InputStream inputStream = new ByteArrayInputStream(requestContentsArray[i].getBytes());
            HTTPRequest httpRequest = new HTTPRequest(inputStream);

            assertThat(httpRequest.getQueryString(httpRequest.getRequestMethod()), is(expQueryStringArray[i]));

        }


    }


    /**
     * setRequestParameterメソッド及び、getRequestParameterメソッドをテストするメソッド
     */
    @Test
    public void getRequestParameter() {


        String requestContents1 = "GET /../../test/resources/test.html?name=a HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";


        String requestContents2 = "POST /../../test/resources/test.html HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n"
                + "Content-Length: 20\n"
                + "\n"
                + "name=b&comment=a\n";

        String requestContentsArray[] = {requestContents1, requestContents2};

        String expRequestParam1 = "a";
        String expRequestParam2 = "b";


        String expRequestParamArray[] = {expRequestParam1, expRequestParam2};

        for (int i = 0; i < requestContentsArray.length; i++) {
            InputStream inputStream = new ByteArrayInputStream(requestContentsArray[i].getBytes());
            HTTPRequest httpRequest = new HTTPRequest(inputStream);

            try {
                httpRequest.setRequestParameter(httpRequest.getQueryString(httpRequest.getRequestMethod()));
            } catch (IOException e) {
                System.err.println("エラー:" + e.getMessage());
                e.getCause();
                e.printStackTrace();
            }

            assertThat(httpRequest.getRequestParameter("name"), is(expRequestParamArray[i]));


        }


    }


    /**
     * getRequestResourceメソッドをテストするためのメソッド
     */
    @Test
    public void getRequestResource() {
        String requestContents = "GET /../../test/resources/test.html?name=a HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";
        InputStream inputStream = new ByteArrayInputStream(requestContents.getBytes());
        HTTPRequest httpRequest = new HTTPRequest(inputStream);

        String requestURI1 = "/test.html";
        String requestURI2 = "/sample/test.html";
        String requestURI3 = "/.sample/test.html";

        String requestURIArray[] = {requestURI1, requestURI2, requestURI3};
        for (String reqURI : requestURIArray) {
            assertThat(httpRequest.getRequestResource(reqURI), is("src/main/resources" + reqURI));

        }

        String requestURI4 = "/";
        String requestURI5 = "//";
        String requestURI6 = "/.sample/";
        String requestURIArray2[] = {requestURI4, requestURI5, requestURI6};
        for (String reqURI2 : requestURIArray2) {
            assertThat(httpRequest.getRequestResource(reqURI2), is("src/main/resources" + reqURI2 + "index.html"));

        }


    }

    /**
     * getRequestResourceExtensionメソッドをテストするメソッド
     */
    @Test
    public void getRequestResourceExtension() {
        String requestContents = "GET /../../test/resources/test.html?name=a HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";
        InputStream inputStream = new ByteArrayInputStream(requestContents.getBytes());
        HTTPRequest httpRequest = new HTTPRequest(inputStream);


        String requestResource1 = "/test.html";
        String requestResource2 = "/sample/test.html";
        String requestResource3 = "/.sample/test.html";

        String requestResourceArray[] = {requestResource1, requestResource2, requestResource3};
        for (String reqResource : requestResourceArray) {
            assertThat(httpRequest.getRequestResourceExtension(reqResource), is("html"));

        }


    }


}

