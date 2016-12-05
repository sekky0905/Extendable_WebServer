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
     * SetResponseHeaderメソッドをテストするクラス¥
     */
    @Test
    public void testSendResponse() {
        OutputStream outputStream = new ByteArrayOutputStream();
        HTTPResponse httpResponse = new HTTPResponse(outputStream);

        httpResponse.sendResponse("HTTP/1.1 200 OK", "Content-Type: text/html", "テスト".getBytes());

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



