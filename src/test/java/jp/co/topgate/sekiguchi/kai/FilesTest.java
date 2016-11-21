package jp.co.topgate.sekiguchi.kai;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class FilesTest {

	@Test
	public void testFileReader() {
		String requestResource = ("src/main/resources/next.html");
		String requestResource2 = ("src/main/resources/sample/next.html");
		String requestResource3 = ("src/main/resources/.sample/next.html");

		String requestResourceArray[] = { requestResource, requestResource2, requestResource3 };
		//
		for (int i = 0; i < requestResourceArray.length; i++) {
			File file = new File(requestResourceArray[i]);
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
