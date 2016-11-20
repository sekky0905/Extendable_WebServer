package jp.co.topgate.sekiguchi.kai;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Files {

	/**
	 * リクエストURIを受け取って、
	 * 
	 * @return
	 */
	public byte[] readFile(String requestURI) {

		File file = new File("src/main/resources" + requestURI);

		byte[] byteContents = null;

		System.out.println("ファイルの読み込みを始めます");

		if (file.exists()) {
			try {
				System.out.println(file + "ファイルを探します");
				InputStream inputStream = new FileInputStream(file);
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				// 入力ストリームからの読み込み（ファイルの読み込み）
				int len;
				while ((len = inputStream.read()) != -1) {
					byteArrayOutputStream.write(len);
				}
				if (byteArrayOutputStream != null) {
					byteArrayOutputStream.flush();
					byteArrayOutputStream.close();
				}
				byteContents = byteArrayOutputStream.toByteArray();
				inputStream.close();

			} catch (IOException e) {
				System.err.println("エラー" + e.getMessage());
				e.printStackTrace();
			}
		} else {
			byteContents = "404 Not Found".getBytes();
		}
		System.out.println("レスポンスボディは" + byteContents);

		return byteContents;

	}

}
