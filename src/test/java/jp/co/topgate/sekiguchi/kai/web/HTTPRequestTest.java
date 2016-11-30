package jp.co.topgate.sekiguchi.kai.web;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Test;

/**
 * HTTPRequestをテストするクラス
 *
 * @author sekiguchikai
 */
public class HTTPRequestTest {

    String socketContents1 = "GET /next.html HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
            + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
            + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
            + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
            + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

    String socketContents2 = "GET /sample/next.html HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
            + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
            + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
            + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
            + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

    String socketContents3 = "GET /next.html?foo=bar HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
            + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
            + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
            + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
            + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

    String socketContents4 = "GET /sample/next.html?foo=bar HTTP/1.1\n" + "Host: localhost:8080\n"
            + "Connection: keep-alive\n"
            + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
            + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
            + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
            + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

    String socketContents5 = "GET /next.html?foo=bar.com HTTP/1.1\n" + "Host: localhost:8080\n"
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

    String socketContents7 = "GET /.sample/next.html HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
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


    String socketContents10 = "POST /next.html HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
            + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
            + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
            + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
            + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n"
            + "\n"
            + "name=a&comment=a";


    String socketContentsArray[] = {socketContents1, socketContents2, socketContents3, socketContents4, socketContents5,
            socketContents6, socketContents7, socketContents8, socketContents9, socketContents10};


    /**
     * getRequestLineのテストをするメソッド
     */
    @Test
    public void getRequestLine() {
        String expRequestLine1 = "GET /next.html HTTP/1.1";
        String expRequestLine2 = "GET /sample/next.html HTTP/1.1";
        String expRequestLine3 = "GET /next.html?foo=bar HTTP/1.1";
        String expRequestLine4 = "GET /sample/next.html?foo=bar HTTP/1.1";
        String expRequestLine5 = "GET /next.html?foo=bar.com HTTP/1.1";
        String expRequestLine6 = "GET /sample/next.html?foo=bar.com HTTP/1.1";
        String expRequestLine7 = "GET /.sample/next.html HTTP/1.1";
        String expRequestLine8 = "GET /.sample/next.html?foo=bar HTTP/1.1";
        String expRequestLine9 = "GET /.sample/next.html?foo=bar.com HTTP/1.1";
        String expRequestLine10 = "POST /next.html HTTP/1.1";


        String expRequestLineArray[] = {expRequestLine1, expRequestLine2, expRequestLine3, expRequestLine4, expRequestLine5, expRequestLine6, expRequestLine7, expRequestLine8, expRequestLine9, expRequestLine10};

        for (int i = 0; i < socketContentsArray.length; i++) {
            InputStream inputStream = new ByteArrayInputStream(socketContentsArray[i].getBytes());
            HTTPRequest httpRequest = new HTTPRequest(inputStream);

            assertThat(httpRequest.getRequestLine(), is(expRequestLineArray[i]));

        }
    }


    /**
     * getRequestMethodメソッドをテストするメソッド
     */
    @Test
    public void getRequestMethod() {
        for (int i = 0; i < socketContentsArray.length; i++) {
            InputStream inputStream = new ByteArrayInputStream(socketContentsArray[i].getBytes());
            HTTPRequest httpRequest = new HTTPRequest(inputStream);

            httpRequest.getRequestMethod(httpRequest.getRequestLine());

            if (i != 9) {
                assertThat(httpRequest.getRequestMethod(httpRequest.getRequestLine()), is("GET"));
            } else if (i == 9) {
                assertThat(httpRequest.getRequestMethod(httpRequest.getRequestLine()), is("POST"));
            }
        }

    }


    /**
     * getSecondSentenceメソッドをテストするメソッド
     */
    @Test
    public void getSecondSentence() {
        String expSecondSentence1 = "/next.html";
        String expSecondSentence2 = "/sample/next.html";
        String expSecondSentence3 = "/next.html?foo=bar";
        String expSecondSentence4 = "/sample/next.html?foo=bar";
        String expSecondSentence5 = "/next.html?foo=bar.com";
        String expSecondSentence6 = "/sample/next.html?foo=bar.com";
        String expSecondSentence7 = "/.sample/next.html";
        String expSecondSentence8 = "/.sample/next.html?foo=bar";
        String expSecondSentence9 = "/.sample/next.html?foo=bar.com";
        String expSecondSentence10 = "/next.html";

        String expSecondSentenceLineArray[] = {expSecondSentence1, expSecondSentence2, expSecondSentence3, expSecondSentence4, expSecondSentence5, expSecondSentence6, expSecondSentence7, expSecondSentence8, expSecondSentence9, expSecondSentence10};


        for (int i = 0; i < socketContentsArray.length; i++) {
            InputStream inputStream = new ByteArrayInputStream(socketContentsArray[i].getBytes());
            HTTPRequest httpRequest = new HTTPRequest(inputStream);

            assertThat(httpRequest.getSecondSentence(httpRequest.getRequestLine()), is(expSecondSentenceLineArray[i]));

        }

    }


    /**
     * getRequestURIメソッドをテストするメソッド
     */
    @Test
    public void getRequestURI() {

        String expRequestURI1 = "/next.html";
        String expRequestURI2 = "/sample/next.html";
        String expRequestURI3 = "/next.html";
        String expRequestURI4 = "/sample/next.html";
        String expRequestURI5 = "/next.html";
        String expRequestURI6 = "/sample/next.html";
        String expRequestURI7 = "/.sample/next.html";
        String expRequestURI8 = "/.sample/next.html";
        String expRequestURI9 = "/.sample/next.html";
        String expRequestURI10 = "/next.html";

        String expRequestURIArray[] = {expRequestURI1, expRequestURI2, expRequestURI3, expRequestURI4, expRequestURI5, expRequestURI6, expRequestURI7, expRequestURI8, expRequestURI9, expRequestURI10};


        for (int i = 0; i < socketContentsArray.length; i++) {
            InputStream inputStream = new ByteArrayInputStream(socketContentsArray[i].getBytes());
            HTTPRequest httpRequest = new HTTPRequest(inputStream);


            assertThat(httpRequest.getRequestURI(httpRequest.getSecondSentence(httpRequest.getRequestLine())), is(expRequestURIArray[i]));

        }
    }

    @Test
    public void getQueryString() {


        String requestContents1 = "GET /next.html?foo=bar HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";


        String requestContents2 = "GET /next.html?foo=bar.com HTTP/1.1\n" + "Host: localhost:8080\n"
                + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";


        String srequestContents3 = "POST /next.html HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n"
                + "Content-Length: 20\n"
                + "\n"
                + "name=a&comment=a\n";

        String requestContentsArray[] = {requestContents1, requestContents2, srequestContents3};

        String expQueryString1 = "foo=bar";
        String expQueryString2 = "foo=bar.com";
        String expQueryString3 = "name=a&comment=a";

        String expQueryStringArray[] = {expQueryString1, expQueryString2, expQueryString3};

        for (int i = 0; i < requestContentsArray.length; i++) {
            InputStream inputStream = new ByteArrayInputStream(requestContentsArray[i].getBytes());
            HTTPRequest httpRequest = new HTTPRequest(inputStream);

            String requestLine = httpRequest.getRequestLine();
            assertThat(httpRequest.getQueryString(httpRequest.getRequestMethod(requestLine), httpRequest.getSecondSentence(requestLine)), is(expQueryStringArray[i]));

        }


    }


    /**
     * setRequestParameterメソッド及び、getRequestParameterメソッドをテストするメソッド
     */
    @Test
    public void getRequestParameter() {


        String requestContents1 = "GET /next.html?name=a HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";


        String requestContents2 = "POST /next.html HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
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

            String requestLine = httpRequest.getRequestLine();
            httpRequest.setRequestParameter(httpRequest.getQueryString(httpRequest.getRequestMethod(requestLine), httpRequest.getSecondSentence(requestLine)));

            assertThat(httpRequest.getRequestParameter("name"), is(expRequestParamArray[i]));


        }


    }


}

