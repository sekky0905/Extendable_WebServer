package jp.co.topgate.sekiguchi.kai.web.http;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

/**
 * HTTPResponseをテストするクラス
 *
 * @author sekiguchikai
 */
public class HTTPResponseTest {

    private OutputStream outputStream = new ByteArrayOutputStream();
    private HTTPResponse httpResponse = new HTTPResponse(outputStream);

    /**
     * setStatusLineメソッドとgetStatusLineメソッドをテストするメソッド
     */
    @Test
    public void setStatusLine() {
        httpResponse.setStatusLine(HTTPResponse.SC_OK);
        assertThat(HTTPResponse.getStatusLine(), is("HTTP/1.1 200 OK"));
    }


    /**
     * makeContentTypeメソッドをテストするメソッド
     */
    @Test
    public void makeContentType() {
        String fileExt[] = {"html", "css", "js", "jpeg", "png", "gif"};
        String expectedContents[] = {"Content-Type: text/html\n", "Content-Type: text/css\n", "Content-Type: text/js\n", "Content-Type: image/jpeg\n", "Content-Type: image/png\n", "Content-Type: image/gif\n"};
        Method makeContentType = null;

        try {
            makeContentType = HTTPResponse.class.getDeclaredMethod("makeContentType", String.class);
            makeContentType.setAccessible(true);
        } catch (NoSuchMethodException e) {
            System.err.println("エラー:" + e.getMessage());
            e.printStackTrace();
        }


        for (int i = 0; i < fileExt.length; i++) {
            try {
                assertThat(makeContentType.invoke(httpResponse, fileExt[i]), is(expectedContents[i]));
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
     * ResponseHeaderメソッドをテストするメソッド
     */
    @Test
    public void sendResponse() {
        httpResponse.setStatusLine(HTTPResponse.SC_OK);
        try {
            httpResponse.setStaticBody(new File("src/test/resources/test.html"));
            httpResponse.sendResponse("html");
        } catch (IOException e) {
            System.err.println("エラー:" + e.getMessage());
            e.printStackTrace();

        }


        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                ((ByteArrayOutputStream) outputStream).toByteArray());

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(byteArrayInputStream));

        StringBuilder stringBuilder = new StringBuilder();
        String result;

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

        assertThat((new String(stringBuilder)), is("HTTP/1.1 200 OKContent-Type: text/htmlテスト"));

    }


}
