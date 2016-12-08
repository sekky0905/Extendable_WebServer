package jp.co.topgate.sekiguchi.kai.web.handler;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by sekiguchikai on 2016/12/05.
 */
public class IndexHandlerTest {

    @Test
    public void handleGet() {

        String socketContents = "GET /program/board HTTP/1.1\n" + "Host: localhost:8080\n" + "Connection: keep-alive\n"
                + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\n"
                + "Accept: */*\n" + "Referer: http://localhost:8080/\n" + "Accept-Encoding: gzip, deflate, sdch, br\n"
                + "Accept-Language: ja,en-US;q=0.8,en;q=0.6\n"
                + "Cookie: Webstorm-eca4e053=a87c22f1-3e1b-475c-85ed-9543ae29fce9\n";


    }

}