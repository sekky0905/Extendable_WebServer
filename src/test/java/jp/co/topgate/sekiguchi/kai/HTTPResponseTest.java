package jp.co.topgate.sekiguchi.kai;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

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
            httpResponse.setResponseHeader(ext);
            assertThat(httpResponse.getResponseHeader(), is("Content-Type: text/" + ext));
        }

        for (String ext : extImageArray) {
            OutputStream outputStream = new ByteArrayOutputStream();
            HTTPResponse httpResponse = new HTTPResponse(outputStream);
            httpResponse.setResponseHeader(ext);
            assertThat(httpResponse.getResponseHeader(), is("Content-Type: image/" + ext));
        }

    }


}
