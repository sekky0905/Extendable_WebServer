package jp.co.topgate.sekiguchi.kai.web.webServer;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

/**
 * StaticFileHandlerクラスをテストするためのクラス
 * Created by sekiguchikai on 2016/12/05.
 */
public class StaticFileHandlerTest {

    /**
     * readFileメソッドをテストするためのメソッド
     */
    @Test
    public void fileReader() {
        String requestResource = ("src/test/resources/test.html");
        String requestResource2 = ("src/test/resources/sample/test.html");
        String requestResource3 = ("src/test/resources/.sample/test.html");

        String requestResourceArray[] = {requestResource, requestResource2, requestResource3};
        //
        for (String resource : requestResourceArray) {
            File file = new File(resource);
            StaticFileHandler staticFileHandler = new StaticFileHandler();
            String fileContents = null;
            try {
                fileContents = new String(staticFileHandler.readFile(file), "UTF-8");
            } catch (UnsupportedEncodingException uee) {
                System.err.println("エラー:" + uee.getMessage());
                uee.getCause();
                uee.printStackTrace();
            } catch (IOException ie) {
                System.err.println("エラー:" + ie.getMessage());
                ie.getCause();
                ie.printStackTrace();
            }

            System.out.println(fileContents);

            assertEquals("指定したファイルを適切に読み込めるかのテスト", "やる気", fileContents);

        }

    }
}