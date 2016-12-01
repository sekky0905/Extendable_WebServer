package jp.co.topgate.sekiguchi.kai.web;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class FilesTest {

    @Test
    public void FileReader() {
        String requestResource = ("src/test/resources/test.html");
        String requestResource2 = ("src/test/resources/sample/test.html");
        String requestResource3 = ("src/test/resources/.sample/test.html");

        String requestResourceArray[] = {requestResource, requestResource2, requestResource3};
        //
        for (String resource : requestResourceArray) {
            File file = new File(resource);
            Files files = new Files();
            String fileContents = null;
            try {
                fileContents = new String(files.readFile(file), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                System.err.println("エラー:" + e.getMessage());
                e.printStackTrace();
            }

            System.out.println(fileContents);

            assertEquals("指定したファイルを適切に読み込めるかのテスト", "やる気", fileContents);

        }

    }

}
