package jp.co.topgate.sekiguchi.kai.web.webserver;


import static org.hamcrest.CoreMatchers.*;

import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Stream;

import jp.co.topgate.sekiguchi.kai.web.webserver.HTTPRequest;
import org.junit.Test;

/**
 * HTTPRequestをテストするクラス
 *
 * @author sekiguchikai
 */
public class HTTPRequestTest {

    /**
<<<<<<< HEAD:src/test/java/jp/co/topgate/sekiguchi/kai/web/webserver/HTTPRequestTest.java
     * getRequestURIメソッドをテストする補助をするメソッド
     *
     * @param data     操作する際に用いるデータ
     * @param expected 期待する値
     */
    private void getRequestMethodHelper(String data, String expected) {
=======
     * getRequestMethodメソッドのテストを補佐するためのメソッド
     *
     * @param data     操作に使用するデータ
     * @param expected 期待する値
     */
    private void getRequestMethodHelper(String data, String expected) {
        Method getRequestMethod = null;
        HTTPRequest httpRequest = new HTTPRequest(new ByteArrayInputStream(data.getBytes()));
        try {
            getRequestMethod = httpRequest.getClass().getDeclaredMethod("getRequestMethod");
            getRequestMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            System.err.println("エラー:" + e.getMessage());
            e.printStackTrace();
        }

        try {
            assertThat(getRequestMethod.invoke(httpRequest), is(expected));
        } catch (IllegalAccessException iae) {
            System.err.println("エラー:" + iae.getMessage());
            iae.printStackTrace();
        } catch (InvocationTargetException ine) {
            System.err.println("エラー:" + ine.getMessage());
            ine.printStackTrace();
        }

    }
>>>>>>> develop:src/test/java/jp/co/topgate/sekiguchi/kai/web/webServer/HTTPRequestTest.java

        HTTPRequest httpRequest = new HTTPRequest(new ByteArrayInputStream(data.getBytes()));
        Method method = null;
        try {
            method = httpRequest.getClass().getDeclaredMethod("getRequestMethod");
        } catch (NoSuchMethodException e) {
            System.err.println("エラー:" + e.getMessage());
            e.printStackTrace();
        }

        method.setAccessible(true);
        String actual = null;
        try {
            actual = (String) method.invoke(httpRequest);
        } catch (IllegalAccessException iae) {
            System.err.println("エラー:" + iae.getMessage());
            iae.printStackTrace();
        } catch (InvocationTargetException ivt) {
            System.err.println("エラー:" + ivt.getMessage());
            ivt.printStackTrace();
        }

        assertThat(actual, is(expected));


    }

    /**
     * getRequestMethodメソッドをテストするメソッド
     */
    @Test
    public void getRequestMethod() {
        String getRequest = "GET /../../test/resources/test.html HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";


        String postRequest = "POST /../../test/resources/test.html HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n"
                + "\n"
                + "name=a&comment=a";

        this.getRequestMethodHelper(getRequest, "GET");
        this.getRequestMethodHelper(postRequest, "POST");
<<<<<<< HEAD:src/test/java/jp/co/topgate/sekiguchi/kai/web/webserver/HTTPRequestTest.java
=======

>>>>>>> develop:src/test/java/jp/co/topgate/sekiguchi/kai/web/webServer/HTTPRequestTest.java
    }


    /**
<<<<<<< HEAD:src/test/java/jp/co/topgate/sekiguchi/kai/web/webserver/HTTPRequestTest.java
     * getRequestURIメソッドをテストする補助をするメソッド
     *
     * @param data     操作する際に用いるデータ
     * @param expected 期待する値
     */
    private void getRequestURIHelper(String data, String expected) {
=======
     * getRequestURIメソッドのテストを補佐するためのメソッド
     *
     * @param data     操作に使用するデータ
     * @param expected 期待する値
     */
    private void getRequestURIHelper(String data, String expected) {

>>>>>>> develop:src/test/java/jp/co/topgate/sekiguchi/kai/web/webServer/HTTPRequestTest.java
        HTTPRequest httpRequest = new HTTPRequest(new ByteArrayInputStream(data.getBytes()));
        Method method = null;
        try {
            method = httpRequest.getClass().getDeclaredMethod("getRequestURI");
<<<<<<< HEAD:src/test/java/jp/co/topgate/sekiguchi/kai/web/webserver/HTTPRequestTest.java
=======
            method.setAccessible(true);
>>>>>>> develop:src/test/java/jp/co/topgate/sekiguchi/kai/web/webServer/HTTPRequestTest.java
        } catch (NoSuchMethodException e) {
            System.err.println("エラー:" + e.getMessage());
            e.printStackTrace();
        }

<<<<<<< HEAD:src/test/java/jp/co/topgate/sekiguchi/kai/web/webserver/HTTPRequestTest.java
        method.setAccessible(true);
        String actual = null;
        try {
            actual = (String) method.invoke(httpRequest);
        } catch (IllegalAccessException iae) {
            System.err.println("エラー:" + iae.getMessage());
            iae.printStackTrace();
        } catch (InvocationTargetException ivt) {
            System.err.println("エラー:" + ivt.getMessage());
            ivt.printStackTrace();
        }

        assertThat(actual, is(expected));

=======
        try {
            assertThat(method.invoke(httpRequest), is(expected));
        } catch (IllegalAccessException iae) {
            System.err.println("エラー:" + iae.getMessage());
            iae.printStackTrace();
        } catch (InvocationTargetException ine) {
            System.err.println("エラー:" + ine.getMessage());
            ine.printStackTrace();
        }

>>>>>>> develop:src/test/java/jp/co/topgate/sekiguchi/kai/web/webServer/HTTPRequestTest.java
    }


    /**
     * getRequestURIメソッドをテストするメソッド
     */
    @Test
    public void getRequestURI() {

        String requestData1 = "GET /../../test/resources/test.html HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

        String requestData2 = "GET /../../test/resources/sample/test.html HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

        String requestData3 = "GET /../../test/resources/test.html?foo=bar HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

        String requestData4 = "GET /../../test/resources/sample/test.html?foo=bar HTTP/1.1\n" + "Host: localhost:8080\n"
                + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

        String requestData5 = "GET /../../test/resources/test.html?foo=bar.com HTTP/1.1\n" + "Host: localhost:8080\n"
                + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

        String requestData6 = "GET /../../test/resources/sample/test.html?foo=bar.com HTTP/1.1\n" + "Host: localhost:8080\n"
                + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

        String requestData7 = "GET /../../test/resources/.sample/test.html HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

        String requestData8 = "GET /../../test/resources/.sample/test.html?foo=bar HTTP/1.1\n" + "Host: localhost:8080\n"
                + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

        String requestData9 = "GET /../../test/resources/.sample/test.html?foo=bar.com HTTP/1.1\n" + "Host: localhost:8080\n"
                + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";


        String requestData10 = "POST /../../test/resources/test.html HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n"
                + "\n"
                + "name=a&comment=a";

        String requestDataArray[] = {requestData1, requestData2, requestData3, requestData4, requestData5, requestData6, requestData7, requestData8, requestData9, requestData10};

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

        String expRequestURI[] = {expRequestURI1, expRequestURI2, expRequestURI3, expRequestURI4, expRequestURI5, expRequestURI6, expRequestURI7, expRequestURI8, expRequestURI9, expRequestURI10};

        for (int i = 0; i < requestDataArray.length; i++) {
            getRequestURIHelper(requestDataArray[i], expRequestURI[i]);
        }


    }

    /**
     * getQueryStringメソッドをテストするメソッド
     */
    @Test
    public void getQueryString() {
        String requestData1 = "GET /../../test/resources/test.html?foo=bar HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";


        String requestData2 = "GET /../../test/resources/test.html?foo=bar.com HTTP/1.1\n" + "Host: localhost:8080\n"
                + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";


        String requestData3 = "POST /../../test/resources/test.html HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n"
                + "Content-Length: 20\n"
                + "\n"
                + "name=a&comment=a\n";

        String requestDataArray[] = {requestData1, requestData2, requestData3};

        String expQueryString1 = "foo=bar";
        String expQueryString2 = "foo=bar.com";
        String expQueryString3 = "name=a&comment=a";

        String expQueryStringArray[] = {expQueryString1, expQueryString2, expQueryString3};
        Method getQueryString = null;
        try {
            getQueryString = HTTPRequest.class.getDeclaredMethod("getQueryString");
        } catch (NoSuchMethodException e) {
            System.err.println("エラー:" + e.getMessage());
            e.printStackTrace();
        }
        getQueryString.setAccessible(true);

        for (int i = 0; i < requestDataArray.length; i++) {
            try {
                assertThat(getQueryString.invoke(new HTTPRequest(new ByteArrayInputStream(requestDataArray[i].getBytes()))), is(expQueryStringArray[i]));
            } catch (InvocationTargetException ite) {
                System.err.println("エラー:" + ite.getMessage());
                ite.printStackTrace();
            } catch (IllegalAccessException iae) {
                System.err.println("エラー:" + iae.getMessage());
                iae.printStackTrace();
            }
        }
    }


    /**
     * setRequestParameterメソッド及び、getRequestParameterメソッドをテストするメソッド
     */
    @Test
    public void getRequestParameter() {
        String requestContents1 = "GET /../../test/resources/test.html?name=a&comment=b HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
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
                + "name=a&comment=b\n";

        String requestContentsArray[] = {requestContents1, requestContents2};


        String target1 = "name";
        String target2 = "comment";

        String targetArray[] = {target1, target2};


        String expRequestParam1 = "a";
        String expRequestParam2 = "b";

        String expRequestParamArray[] = {expRequestParam1, expRequestParam2};

        for (int i = 0; i < requestContentsArray.length; i++) {
            HTTPRequest httpRequest = new HTTPRequest(new ByteArrayInputStream(requestContentsArray[i].getBytes()));
<<<<<<< HEAD:src/test/java/jp/co/topgate/sekiguchi/kai/web/webserver/HTTPRequestTest.java


=======
>>>>>>> develop:src/test/java/jp/co/topgate/sekiguchi/kai/web/webServer/HTTPRequestTest.java
            assertThat(httpRequest.getRequestParameter(targetArray[i]), is(expRequestParamArray[i]));
        }


    }


    /**
     * getRequestResourceメソッドをテストするためのメソッド
     */
    @Test
    public void getRequestResource() {
        String requestContents1 = "GET /test.html?name=a HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

        String requestContents2 = "GET /sample/test.html?name=a HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";

        String requestContents3 = "GET /.sample/test.html?name=a HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";


        String reqContentsArray[] = {requestContents1, requestContents2, requestContents3};

        String resource1 = "src/main/resources/test.html";
        String resource2 = "src/main/resources/sample/test.html";
        String resource3 = "src/main/resources/.sample/test.html";

        String expectedResourceArray[] = {resource1, resource2, resource3};

        for (int i = 0; i < reqContentsArray.length; i++) {
            assertThat(new HTTPRequest(new ByteArrayInputStream(reqContentsArray[i].getBytes())).getRequestResource(), is(expectedResourceArray[i]));
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

        HTTPRequest httpRequest = new HTTPRequest(new ByteArrayInputStream(requestContents.getBytes()));


        String requestResource1 = "/test.html";
        String requestResource2 = "/sample/test.html";
        String requestResource3 = "/.sample/test.html";

        String requestResourceArray[] = {requestResource1, requestResource2, requestResource3};


        for (String reqResource : requestResourceArray) {
            assertThat(httpRequest.getRequestResourceExtension(reqResource), is("html"));
        }


    }


}